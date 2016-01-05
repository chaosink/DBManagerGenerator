import java.util.*;

public class ZJUCourseTest {
	public static void main(String[] args) {
		ZJUCourse zjuc = new ZJUCourse();
		zjuc.init("root", "");

		zjuc.insert("1, '1', 1, 1, 1, '1', '1', '1'");
		zjuc.insert("2, '2', 2, 2, 2, '2', '2', '2'");
		zjuc.insert("3, '3', 3, 3, 3, '3', '3', '3'");
		zjuc.insert("4, '1', 1, 1, 1, '1', '1', '1'");
		zjuc.insert("5, '1', 1, 1, 1, '1', '1', '1'");
		zjuc.insert("6, '1', 1, 1, 1, '1', '1', '1'");
		zjuc.insert("7, '1', 1, 1, 1, '1', '1', '1'");
		zjuc.insert("8, '1', 1, 1, 1, '1', '1', '1'");
		zjuc.insert("9, '1', 1, 1, 1, '1', '1', '1'");

		SelectResult sr = zjuc.select("course_id, teacher_id, classroom_id, credit", "teacher_id <= 3 and teacher_id = 1");
		zjuc.display(sr);

		zjuc.delete("");

		zjuc.terminate();
	}
}
