import java.sql.*;
import java.util.*;

class SelectResult {
	String[] attribute;
	ArrayList<ArrayList<String>> record;

	SelectResult(String attribute) {
		this.attribute = attribute.split(" ");
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
				ArrayList<String> row = new ArrayList<String>();
				row.add(rs.getInt("course_id") + "");
				row.add(rs.getInt("teacher_id") + "");
				row.add(rs.getInt("classroom_id") + "");
				sr.record.add(row);
			}
			return sr;
		} catch(Exception e) {
			System.out.println(e);
			return null;
		}
	}

	public void display(SelectResult sr) {
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
//			statementInsert.setInt(1, record.course_id);
//			statementInsert.setString(2, record.name);
//			statementInsert.setInt(3, record.credit);
//			statementInsert.setInt(4, record.teacher_id);
//			statementInsert.setInt(5, record.classroom_id);
//			statementInsert.setString(6, record.time);
//			statementInsert.setString(7, record.semester);
//			statementInsert.setString(8, record.introduction);
//			statementInsert.executeUpdate();
		} catch(Exception e) {
			System.out.println(e);
		}
	}

	public void delete(String where) {
		try {
			statement.executeUpdate("delete from " + table + " where " + where);
		} catch(Exception e) {
			System.out.println(e);
		}
	}

	public void update(String set, String where) {
		String query = "update " + table + " set " + set + " where " + where;
		try {
			statement.executeUpdate(query);
		} catch(Exception e) {
			System.out.println(e);
		}
	}
}
