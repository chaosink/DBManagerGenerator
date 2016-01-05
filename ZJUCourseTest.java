import java.util.*;

public class ZJUCourseTest {
	public static void main(String[] args) {
		ZJUCourse zjuc = new ZJUCourse();
		zjuc.init("root", "");

		zjuc.insert("1, '1', 1, 1, 1, '1', '1', '1'");
		zjuc.insert("2, '2', 2, 2, 2, '2', '2', '2'");
		zjuc.insert("3, '3', 3, 3, 3, '3', '3', '3'");

		SelectResult sr = zjuc.select("course_id, teacher_id, classroom_id", "course_id = 1 and teacher_id = 1 and classroom_id = 1");
		zjuc.display(sr);
		zjuc.update("course_id = 11, teacher_id = 11, classroom_id = 11", "course_id = 1");
		sr = zjuc.select("course_id, teacher_id, classroom_id", "course_id = 11");
		zjuc.display(sr);

		zjuc.delete("course_id = 11");
		zjuc.delete("course_id = 2");
		zjuc.delete("course_id = 3");

		zjuc.terminate();
	}
}
