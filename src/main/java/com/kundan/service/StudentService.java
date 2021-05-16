package com.kundan.service;

import java.util.List;
import com.kundan.model.Student;

public interface StudentService {

	Integer saveStudent(Student student);
	
	void updateStudent(Integer studentId,Student student);
	
	void deleteStudent(Integer studentId);
	
	Student getOneStudent(Integer studentId);
	
	List<Student> getAllStudents();
	
}
