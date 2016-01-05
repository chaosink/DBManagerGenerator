LIB  = -Djava.ext.dirs=lib
TARGET = DBManagerGenerator.class

all: $(TARGET)

DBManagerGenerator.class: DBManagerGenerator.java
	@ javac DBManagerGenerator.java

gen:
	@ java $(LIB) DBManagerGenerator ZJU Course
	@ java $(LIB) DBManagerGenerator ZJU Teacher

clean:
	@ rm -f *.class ZJUCourse.java ZJUTeacher.java
