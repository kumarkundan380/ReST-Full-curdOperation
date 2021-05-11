package com.kundan.util;

import org.springframework.stereotype.Component;

import com.kundan.model.Student;

@Component
public class StudentUtil {

	public void mapToActualObject(Student actualStudent, Student student) {
		if(student.getStudentName()!= null) {
			actualStudent.setStudentName(student.getStudentName());
		}	
		actualStudent.setStudentFee(student.getStudentFee());
		actualStudent.setStudentCourse(student.getStudentCourse());
		if(student.getStudentEmail()!= null) {
			actualStudent.setStudentEmail(student.getStudentEmail());
		}	
		actualStudent.setStudentAddress(student.getStudentAddress());
	}	
}
