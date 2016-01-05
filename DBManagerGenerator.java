// 编写Java程序，产生自动将数据库中一张表转换成类操作的Java程序。包括基本的数据库查询、显示与增、删、改操作。
// 输入指定数据库的表名，显示或产生Java程序文件。
// 报告中附上数据库结构图。

import java.sql.*;

public class DBManagerGenerator {
	Connection connection;
	PreparedStatement asdf;

	void init(String dbName, String tableName) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost/" + dbName, "root", "");
		} catch(Exception e) {
			System.out.println(e);
		}
	}

	void terminate() {
		try{
			connection.close();
		} catch(Exception e) {
			System.out.println(e);
		}
	}

	void select() {

	}

	void display() {

	}

	void insert() {

	}

	void delete() {

	}

	void update() {

	}

	public static void main(String[] args) {
		if(args.length < 2) {
			System.out.println("Error: Database name and table name needed!");
		}

		DBManagerGenerator dbmg = new DBManagerGenerator();
	}
}
