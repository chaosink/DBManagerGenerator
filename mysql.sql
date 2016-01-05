drop database ZJU;

create database ZJU;

use ZJU;

create table Course (
	course_id int,
	name char(32),
	credit int,
	teacher_id int,
	classroom_id int,
	time varchar(31),
	semester char(2),
	introduction varchar(1023),
	primary key (course_id)
);

create table Teacher (
	teacher_id int,
	name char(16),
	gender char,
	education char(32),
	introduction varchar(1023),
	primary key (teacher_id)
);

create table Classroom (
	classroom_id int,
	location char(31),
	primary key (classroom_id)
);

insert into Course values (1, "1", "M", "1", "1");
insert into Course values (2, "2", "M", "2", "2");
insert into Course values (3, "3", "M", "3", "3");
