package com.kundan.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.kundan.exception.ResourceNotFoundException;
import com.kundan.model.Student;
import com.kundan.repository.StudentRepository;
import com.kundan.service.StudentService;
import com.kundan.util.StudentUtil;

@Service
public class StudentServiceImpl implements StudentService {

	@Autowired
	private StudentRepository studentRepository;
	
	@Autowired 
	private StudentUtil studentUtil;
	
	@Override
	public Integer saveStudent(Student student) {
		return studentRepository.save(student).getStudentId();
	}

	@Override
	@CachePut(value = "student", key ="#studentId")
	public void updateStudent(Integer studentId,Student student) {
		Student actualStudent = studentRepository.findById(studentId)
				.orElseThrow(()-> new ResourceNotFoundException("Student Not Exist"));
		studentUtil.mapToActualObject(actualStudent,student);
		studentRepository.save(actualStudent);
	}

	@Override
	@CacheEvict(value = "student", key ="#studentId")
	public void deleteStudent(Integer studentId) {
		Student student = studentRepository.findById(studentId)
				.orElseThrow(()-> new ResourceNotFoundException("Student Not Exist"));
		studentRepository.delete(student);
	}

	@Override
	@Cacheable(value = "student", key ="#studentId")
	public Student getOneStudent(Integer studentId) {
		Student student = studentRepository.findById(studentId)
				.orElseThrow(()-> new ResourceNotFoundException("Student Not Exist"));
		return student ;
	}

	@Override
	public List<Student> getAllStudents() {
		return studentRepository.findAll();
	}

}
