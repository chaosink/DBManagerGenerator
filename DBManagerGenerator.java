import java.io.*;

public class DBManagerGenerator {
	public static void main(String[] args) {
		if(args.length < 2) {
			System.out.println("Error: Database name and table name needed!");
		}

		try {
			File file = new File("DatabaseTable.java");
			byte[] content = new byte[(int)file.length()];
			FileInputStream in = new FileInputStream(file);
			in.read(content);
			in.close();

			String source = new String(content);
			file = new File(args[0] + args[1] + ".java");
			FileOutputStream out = new FileOutputStream(file);
			out.write(source.replaceAll("#database", args[0]).replaceAll("#table", args[1]).getBytes());
			out.close();
		} catch(Exception e) {
			System.out.println(e);
		}
	}
}
