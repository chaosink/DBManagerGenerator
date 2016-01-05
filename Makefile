LIB  = -Djava.ext.dirs=lib

all: DBManagerGenerator.class ZJUCourse.class ZJUCourseTest.class

DBManagerGenerator.class: DBManagerGenerator.java
	@ javac DBManagerGenerator.java

ZJUCourse.class: ZJUCourse.java
	@ javac ZJUCourse.java

ZJUCourseTest.class: ZJUCourseTest.java
	@ javac ZJUCourseTest.java

run:
	@ java $(LIB) ZJUCourseTest
