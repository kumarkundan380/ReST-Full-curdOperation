package com.kundan.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kundan.model.Student;
import com.kundan.repository.StudentRepository;
import com.kundan.service.StudentService;

@Service
public class StudentServiceImpl implements StudentService {

	@Autowired
	private StudentRepository studentRepository;
	
	@Override
	public Integer saveStudent(Student student) {
		return studentRepository.save(student).getStudentId();
	}

	@Override
	public void updateStudent(Student student) {
		studentRepository.save(student);
	}

	@Override
	public void deleteStudent(Integer studentId) {
		studentRepository.deleteById(studentId);
	}

	@Override
	public Optional<Student> getOneStudent(Integer studentId) {
		return studentRepository.findById(studentId) ;
	}

	@Override
	public List<Student> getAllStudents() {
		return studentRepository.findAll();
	}

	@Override
	public boolean isStudentExist(Integer studentId) {
		return studentRepository.existsById(studentId);
	}
}
