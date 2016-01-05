public class ZJUTeacherTest {
	public static void main(String[] args) {
		ZJUTeacher zju = new ZJUTeacher();
		zju.init("root", "");

		zju.insert(new TeacherRecord(1, "1", "F", "1", "1"));
		zju.insert(new TeacherRecord(2, "2", "M", "2", "2"));
		zju.insert(new TeacherRecord(3, "3", "F", "3", "3"));
		zju.insert(new TeacherRecord(4, "4", "M", "4", "4"));
		zju.insert(new TeacherRecord(5, "5", "F", "5", "5"));
		zju.insert(new TeacherRecord(6, "6", "M", "6", "6"));
		zju.insert(new TeacherRecord(7, "7", "F", "7", "7"));
		zju.insert(new TeacherRecord(8, "8", "M", "8", "8"));
		zju.insert(new TeacherRecord(9, "9", "F", "9", "9"));

		SelectResult sr = zju.select(new String[]{"teacher_id", "name", "education"}, "teacher_id >= 3 and gender = 'F'");
		zju.display(sr);

		zju.delete("");

		zju.terminate();
	}
}
