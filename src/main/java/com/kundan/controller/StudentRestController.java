package com.kundan.controller;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kundan.model.Student;
import com.kundan.service.StudentService;
import com.kundan.util.StudentUtil;

@RestController
@RequestMapping("/student")
public class StudentRestController {
	
private static final Logger LOGGER = LoggerFactory.getLogger(StudentRestController.class);
	
	@Autowired
	private StudentService studentService;
	
	@Autowired 
	private StudentUtil studentUtil;
	
	/**
	 * 1. Read JSON(Student) and convert to Object Format
	 * 	  Store data in Database. Return one Message.	
	 */
	@PostMapping("/save")
	public ResponseEntity<String> saveStudent(@RequestBody Student student) {
		LOGGER.info("Entered into method with Student data to save");
		ResponseEntity<String> response = null;
		try {
			LOGGER.info("About to call save operation");
			Integer studentId = studentService.saveStudent(student);
			LOGGER.debug("Student saved with id:"+studentId);
			String body = "Student '"+studentId+"' created";
			response = new ResponseEntity<String>(body,HttpStatus.CREATED);
			LOGGER.info("Success Response Constructed");
		} catch (Exception exception) {
			LOGGER.error("Unable to Save student: Probleam is: "+exception.getMessage());
			response = new ResponseEntity<String>("Unable to Create Student",HttpStatus.INTERNAL_SERVER_ERROR);
			exception.printStackTrace();
		}
		LOGGER.info("About to exit save method with Response ");
		return response;
	}
	
	/**
	 *  Fetch all rows from database using Service
	 *  Sort data using name, return as JSON
	 *  else String message no data found
	 */
	@GetMapping("/findAll")
	public ResponseEntity<?> getAllStudents(){
		LOGGER.info("Entered into method to fetch Student data");
		ResponseEntity<?> response = null;
		try {
			LOGGER.info("About to call fetch StudentService");
			List<Student> studentList = studentService.getAllStudents();
			if(studentList!=null && !studentList.isEmpty()) {
				LOGGER.info("Data is not Empty=>"+studentList.size());
				studentList.sort((student1,student2)->student1.getStudentName().compareTo(student2.getStudentName()));
				response = new ResponseEntity<List<Student>>(studentList,HttpStatus.OK);
			} else {
				LOGGER.info("No Student exist: size "+studentList.size());
				response = new ResponseEntity<String>("No Student Found",HttpStatus.OK);
			}
		} catch(Exception e) {
			LOGGER.error("Unable to fetch students: Probleam is: "+e.getMessage());
			response = new ResponseEntity<String>("Unable to fetch student",HttpStatus.INTERNAL_SERVER_ERROR);
			e.printStackTrace();
		}
		LOGGER.info("About to exit fetch all method with Response");
		return response;
	}
	
	/**
	 * 3. Get one student based on ID(PathVariable)
	 * 	If Object exist then return Student Object
	 * else provide message(String)
	 */
	@GetMapping("/one/{studentId}")
	public ResponseEntity<?> getOneStudent(@PathVariable Integer studentId) {
		LOGGER.info("Entered into getOneStudent method");
		ResponseEntity<?> response = null;
		try {
			LOGGER.info("About to make service call to fetch one record");
			Optional<Student> optionalStudent = studentService.getOneStudent(studentId);
			if(optionalStudent.isPresent()) {
				LOGGER.info("Student exist=>"+studentId);
				//response = new ResponseEntity<Student>(optionalStudent.get(),HttpStatus.OK);
				response = ResponseEntity.ok(optionalStudent.get());
			} else {
				LOGGER.warn("Given student id not exist=>"+studentId);
				response = new ResponseEntity<String>("Student '"+studentId+"' not exist",HttpStatus.BAD_REQUEST);
			}
		} catch(Exception e) {
			LOGGER.error("Unable to process request fetch:"+e.getMessage());
			response = new ResponseEntity<String>("Unable to process student fetch",HttpStatus.INTERNAL_SERVER_ERROR);
			e.printStackTrace();
		}
		LOGGER.info("About to exit getOneStudent method with Response");
		return response;
	}
	
	/**
	 * 4. Delete one Student based on ID(PathVariable)
	 * If ID exist then delete the student record provide message
	 * else provide message "No Student Exist"
	 */
	@DeleteMapping("/remove/{studentId}")
	public ResponseEntity<String> removeStudent(@PathVariable Integer studentId) {
		LOGGER.info("Enterd in removeStudent method");
		ResponseEntity<String> response = null;
		try {
			LOGGER.info("About to make service call for data check");
			if(studentService.isStudentExist(studentId)) {
				studentService.deleteStudent(studentId);
				LOGGER.info("Student exist with given id and deleted=>"+studentId);
				response = new ResponseEntity<String>("Student '"+studentId+"' Deleted",HttpStatus.OK);
			} else {
				LOGGER.warn("Given Student id not exist=>"+studentId);
				response = new ResponseEntity<String>("Student '"+studentId+"' not exixt",HttpStatus.BAD_REQUEST);
			}
		} catch(Exception e) {
			LOGGER.error("Unable to perform delete Operation =>"+e.getMessage());
			response = new ResponseEntity<String>("Unable to delete",HttpStatus.INTERNAL_SERVER_ERROR);
			e.printStackTrace();
		}
		LOGGER.info("About to exit remove student method with Response");
		return response;
	}
	
	/**
	 * 5. Update on Student based on ID(PathVariable)
	 * If ID exist then update the student record and provide message
	 * else provide message "No Student Exist"
	 */
	@PutMapping("/update/{studentId}")
	public ResponseEntity<String> updateStudent(@PathVariable Integer studentId, @RequestBody Student student) {
		LOGGER.info("Enterd in updateStudent method");
		ResponseEntity<String> response = null;
		try {
			LOGGER.info("About to make service call for data check");
			Optional<Student> optStudent = studentService.getOneStudent(studentId);
			if(optStudent.isPresent()) {
				Student actualStudent = optStudent.get();
				studentUtil.mapToActualObject(actualStudent,student);
				studentService.updateStudent(actualStudent);
				LOGGER.info("Student exist with given id and updated=>"+studentId);
				response = new ResponseEntity<String>("Student '"+studentId+"' Updated",HttpStatus.RESET_CONTENT);
			} else {
				LOGGER.warn("Given Student id not exist=>"+studentId);
				response = new ResponseEntity<String>("Student '"+studentId+"' not exist",HttpStatus.BAD_REQUEST);
			}
		} catch (Exception e) {
			LOGGER.error("Unable to perform update Operation =>"+e.getMessage());
			response = new ResponseEntity<String>("Unable to update",HttpStatus.INTERNAL_SERVER_ERROR);
			e.printStackTrace();
		}
		LOGGER.info("About to exit updateStudent method with Response");
		return response;
	}

}
