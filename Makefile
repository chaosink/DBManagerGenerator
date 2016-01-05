LIB  = -Djava.ext.dirs=lib
TARGET = DBManagerGenerator.class ZJUCourse.class ZJUCourseTest.class ZJUTeacher.class ZJUTeacherTest.class

all: $(TARGET)

DBManagerGenerator.class: DBManagerGenerator.java
	@ javac DBManagerGenerator.java

ZJUCourse.class: ZJUCourse.java
	@ javac ZJUCourse.java

ZJUCourseTest.class: ZJUCourseTest.java
	@ javac ZJUCourseTest.java

ZJUTeacher.class: ZJUTeacher.java
	@ javac ZJUTeacher.java

ZJUTeacherTest.class: ZJUTeacherTest.java
	@ javac ZJUTeacherTest.java

course:
	@ java $(LIB) ZJUCourseTest

teacher:
	@ java $(LIB) ZJUTeacherTest

gen:
	@ java $(LIB) DBManagerGenerator ZJU Course
	@ java $(LIB) DBManagerGenerator ZJU Teacher

clean:
	@ rm -f *.class
