import java.sql.*;
import java.util.*;

class SelectResult {
	String[] attribute;
	ArrayList<ArrayList<String>> record;

	SelectResult(String attribute) {
		this.attribute = attribute.replaceAll(",", " ").split(" +");
		this.record = new ArrayList<ArrayList<String>>();
	}
}

public class ZJUCourse {
	private String database;
	private String table;
	private Connection connection;
	private Statement statement;

	public void init(String user, String password) {
		try {
			database = "ZJU"; // #database
			table = "Course"; // #table
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
			if(!where.isEmpty())
				query += " where " + where;
			ResultSet rs = statement.executeQuery(query);
			while(rs.next()) {
				ArrayList<String> record = new ArrayList<String>();
				for(int i = 1; i <= sr.attribute.length; i++)
					record.add(rs.getObject(i).toString());
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
			System.out.println("+");
			for(int j = 0; j < width[i]; j++)
				System.out.print("-");
		}
		System.out.println("+");
	}

	public void printRecord(ArrayList<String> record, int[] width) {
		for(int i = 0; i < width.length; i++) {
			System.out.println("|");
			for(int j = 0; j < width[i]; j++) {
				String format = "%" + width[i] + "s";
				System.out.printf(format, record.get(i));
			}
		}
		System.out.println("+");
	}

	public void display(SelectResult sr) {
		int[] width = new int[sr.attribute.length];
		for(int i = 0; i < sr.attribute.length; i++)
			width[i] = sr.attribute[i].length();
		for(int i = 0; i < sr.attribute.length; i++)
			for(int j = 0; j < sr.record.size(); j++)
				if(sr.record.get(j).get(i).length() > width[i])
					width[i] = sr.record.get(j).get(i).length();

		for(int i = 0; i < sr.attribute.length; i++)
			System.out.print(sr.attribute[i] + " ");
		System.out.println();
		for(int i = 0; i < sr.record.size(); i++) {
			ArrayList<String> record = sr.record.get(i);
			for(int j = 0; j < record.size(); j++) {
				System.out.print(record.get(j) + " ");
			}
			System.out.println();
		}
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
			if(!where.isEmpty())
				query += " where " + where;
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
