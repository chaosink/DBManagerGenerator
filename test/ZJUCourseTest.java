public class ZJUCourseTest {
	public static void main(String[] args) {
		ZJUCourse zju = new ZJUCourse();
		zju.init("root", "");

		zju.insert(new CourseRecord(1, "1", 1, 1, 1, "1", "1", "1"));
		zju.insert(new CourseRecord(2, "2", 2, 2, 2, "2", "2", "2"));
		zju.insert(new CourseRecord(3, "3", 3, 3, 3, "3", "3", "3"));
		zju.insert(new CourseRecord(4, "4", 4, 4, 4, "4", "4", "4"));
		zju.insert(new CourseRecord(5, "5", 5, 5, 5, "5", "5", "5"));
		zju.insert(new CourseRecord(6, "6", 6, 6, 6, "6", "6", "6"));
		zju.insert(new CourseRecord(7, "7", 7, 7, 7, "7", "7", "7"));
		zju.insert(new CourseRecord(8, "8", 8, 8, 8, "8", "8", "8"));
		zju.insert(new CourseRecord(9, "9", 9, 9, 9, "9", "9", "9"));

		SelectResult sr = zju.select(new String[]{"course_id", "teacher_id", "classroom_id", "credit"}, "course_id <= 7 and teacher_id > 3");
		zju.display(sr);

		zju.delete("");

		zju.terminate();
	}
}
