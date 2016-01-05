import java.io.*;
import java.sql.*;

public class DBManagerGenerator {
	public static void main(String[] args) {
		if(args.length < 2) {
			System.out.println("Error: Database name and table name needed!");
		}
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/" + args[0], "root", "");
			Statement 	statement = connection.createStatement();
			ResultSet rs = statement.executeQuery("select * from " + args[1]);
			ResultSetMetaData rsmd = rs.getMetaData();

			int columnCount = rsmd.getColumnCount();
			String recordArgument = "";
			String recordMember = "";
			String recordConstructor = "";
			String insertSet = "";
			String insertMark = "";
			for (int i = 1; i <= columnCount ; i++) {
				String argumentType = "";
				switch(rsmd.getColumnTypeName(i)) {
					case "TINYINT":
						argumentType = "byte";
						break;
					case "SMALLINT":
						argumentType = "short";
						break;
					case "INT":
					case "INTEGER":
						argumentType = "int";
						break;
					case "BIGINT":
						argumentType = "long";
						break;
					case "FLOAT":
						argumentType = "float";
						break;
					case "DOUBLE":
						argumentType = "double";
						break;
					case "CHAR":
					case "VARCHAR":
					case "TINYBLOB":
					case "TINYTEXT":
					case "BLOB":
					case "TEXT":
					case "MEDIUMBLOB":
					case "MEDIUMTEXT":
					case "LOGNGBLOB":
					case "LONGTEXT":
						argumentType = "String";
						break;
				}
				if(i != columnCount) {
					recordArgument += argumentType + " " + rsmd.getColumnName(i) + ", ";
					recordMember += argumentType + " " + rsmd.getColumnName(i) + ";\n	";
					recordConstructor += "this." + rsmd.getColumnName(i) + " = " + rsmd.getColumnName(i) + ";\n		";
					insertSet += "statementInsert.setObject(" + i + ", record." + rsmd.getColumnName(i) + ");\n			";
					insertMark += "?, ";
				} else {
					recordArgument += argumentType + " " + rsmd.getColumnName(i);
					recordMember += argumentType + " " + rsmd.getColumnName(i) + ";";
					recordConstructor += "this." + rsmd.getColumnName(i) + " = " + rsmd.getColumnName(i) + ";";
					insertSet += "statementInsert.setObject(" + i + ", record." + rsmd.getColumnName(i) + ");";
					insertMark += "?";
				}
			}

			File file = new File("DatabaseTable.java");
			byte[] content = new byte[(int)file.length()];
			FileInputStream in = new FileInputStream(file);
			in.read(content);
			in.close();

			String source = new String(content);
			source = source.replaceAll("#database", args[0]);
			source = source.replaceAll("#table", args[1]);
			source = source.replaceAll("#recordArgument", recordArgument);
			source = source.replaceAll("#insertSet", insertSet);
			source = source.replaceAll("#insertMark", insertMark);
			source = source.replaceAll("#recordMember", recordMember);
			source = source.replaceAll("#recordArgument", recordArgument);
			source = source.replaceAll("#recordConstructor", recordConstructor);

			file = new File(args[0] + args[1] + ".java");
			FileOutputStream out = new FileOutputStream(file);
			out.write(source.getBytes());
			out.close();
		} catch(Exception e) {
			System.out.println(e);
		}
	}
}
