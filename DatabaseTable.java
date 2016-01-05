import java.sql.*;
import java.util.*;

class SelectResult {
	String[] attribute;
	ArrayList<String[]> record;

	SelectResult(String attribute) {
		this.attribute = attribute.trim().replaceAll(",", " ").split(" +");
		this.record = new ArrayList<String[]>();
	}
}

public class #database#table {
	private String database;
	private String table;
	private Connection connection;
	private Statement statement;

	public void init(String user, String password) {
		try {
			database = "#database";
			table = "#table";
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost/" + database, user, password);
			statement = connection.createStatement();
		} catch(Exception e) {
			System.out.println(e);
		}
	}

	public void terminate() {
		try {
			statement.close();
			connection.close();
		} catch(Exception e) {
			System.out.println(e);
		}
	}

	public SelectResult select(String attribute, String where) {
		try {
			SelectResult sr = new SelectResult(attribute);
			String query = "select " + attribute + " from " + table;
			if(!where.isEmpty()) query += " where " + where;
			ResultSet rs = statement.executeQuery(query);
			while(rs.next()) {
				String[] record = new String[sr.attribute.length];
				for(int i = 0; i < sr.attribute.length; i++)
					record[i] = rs.getObject(i + 1).toString();
				sr.record.add(record);
			}
			return sr;
		} catch(Exception e) {
			System.out.println(e);
			return null;
		}
	}

	public void printSeperator(int[] width) {
		for(int i = 0; i < width.length; i++) {
			System.out.print("+");
			for(int j = 0; j < width[i] + 2; j++)
				System.out.print("-");
		}
		System.out.println("+");
	}

	public void printRecord(String[] record, int[] width) {
		for(int i = 0; i < width.length; i++) {
			System.out.print("|");
			String format = "%" + (width[i] + 1) + "s ";
			System.out.printf(format, record[i]);
		}
		System.out.println("|");
	}

	public void display(SelectResult sr) {
		int[] width = new int[sr.attribute.length];
		for(int i = 0; i < sr.attribute.length; i++)
			width[i] = sr.attribute[i].length();
		for(int i = 0; i < sr.attribute.length; i++)
			for(int j = 0; j < sr.record.size(); j++)
				if(sr.record.get(j)[i].length() > width[i])
					width[i] = sr.record.get(j)[i].length();

		printSeperator(width);
		printRecord(sr.attribute, width);
		printSeperator(width);
		for(int i = 0; i < sr.record.size(); i++) {
			printRecord(sr.record.get(i), width);
		}
		printSeperator(width);
	}

	public void insert(String record) {
		try {
			statement.executeUpdate("insert into " + table + " values (" + record + ")");
		} catch(Exception e) {
			System.out.println(e);
		}
	}

	public void delete(String where) {
		try {
			String query = "delete from " + table;
			if(!where.isEmpty()) query += " where " + where;
			statement.executeUpdate(query);
		} catch(Exception e) {
			System.out.println(e);
		}
	}

	public void update(String set, String where) {
		try {
			statement.executeUpdate("update " + table + " set " + set + " where " + where);
		} catch(Exception e) {
			System.out.println(e);
		}
	}
}
