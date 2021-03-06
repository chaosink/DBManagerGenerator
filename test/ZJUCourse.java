import java.sql.*;
import java.util.*;

class SelectResult {
	String[] attribute;
	ArrayList<String[]> record;

	SelectResult(String[] attribute) {
		this.attribute = attribute;
		this.record = new ArrayList<String[]>();
	}
}

class CourseRecord {
	int course_id;
	String name;
	int credit;
	int teacher_id;
	int classroom_id;
	String time;
	String semester;
	String introduction;
	CourseRecord(int course_id, String name, int credit, int teacher_id, int classroom_id, String time, String semester, String introduction) {
		this.course_id = course_id;
		this.name = name;
		this.credit = credit;
		this.teacher_id = teacher_id;
		this.classroom_id = classroom_id;
		this.time = time;
		this.semester = semester;
		this.introduction = introduction;
	}
};

public class ZJUCourse {
	private String database;
	private String table;
	private Connection connection;
	private Statement statement;
	private PreparedStatement statementInsert;

	public void init(String user, String password) {
		try {
			database = "ZJU";
			table = "Course";
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost/" + database, user, password);
			statement = connection.createStatement();
			statementInsert = connection.prepareStatement("insert into " + table + " values (?, ?, ?, ?, ?, ?, ?, ?)");
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

	public SelectResult select(String[] attribute, String where) {
		try {
			SelectResult sr = new SelectResult(attribute);
			String query = "select " + attribute[0];
			for(int i = 1; i < attribute.length; i++)
				query += ", " + attribute[i];
			query += " from " + table;
			if(!where.isEmpty()) query += " where " + where;
			ResultSet rs = statement.executeQuery(query);
			while(rs.next()) {
				String[] record = new String[attribute.length];
				for(int i = 0; i < attribute.length; i++)
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

	public void insert(CourseRecord record) {
		try {
			statementInsert.setObject(1, record.course_id);
			statementInsert.setObject(2, record.name);
			statementInsert.setObject(3, record.credit);
			statementInsert.setObject(4, record.teacher_id);
			statementInsert.setObject(5, record.classroom_id);
			statementInsert.setObject(6, record.time);
			statementInsert.setObject(7, record.semester);
			statementInsert.setObject(8, record.introduction);
			statementInsert.executeUpdate();
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
