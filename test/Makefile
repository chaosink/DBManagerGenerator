LIB  = -Djava.ext.dirs=../lib
TARGET = ZJUCourse.class ZJUCourseTest.class ZJUTeacher.class ZJUTeacherTest.class

all: $(TARGET)

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

clean:
	@ rm -f *.class
