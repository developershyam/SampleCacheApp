package com.sample.controllers;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.sample.model.Student;
import com.sample.service.CacheService;
import com.sample.service.CacheServiceWithoutRepo;

@RestController
public class CacheController {

	@Autowired
	private CacheServiceWithoutRepo cacheServiceOld;
	@Autowired
	private CacheService cacheService;

	@GetMapping(path = "/addStudent/{id}/{name}/{grade}")
	public Student addStudent(@PathVariable String id, @PathVariable String name, @PathVariable int grade) {

		Student student = new Student(id, name, Student.Gender.MALE, grade);
		return (Student)cacheService.addModel(student);
	}

	@GetMapping(path = "/getStudent/{id}")
	public Student getStudent(@PathVariable String id) {

		return (Student)cacheService.getModel(id);
	}

	@GetMapping(path = "/getStudents")
	public List<Student> getStudents() {

		Object[] objectArray = cacheService.getAll().toArray();
		Student[] stArray = Arrays.copyOf(objectArray, objectArray.length, Student[].class);
		return Arrays.asList(stArray);
	}

	@GetMapping(path = "/deleteStudent/{id}")
	public String deleteStudents(@PathVariable String id) {

		return cacheService.delete(id);
	}

	@GetMapping(path = "/deleteStudents")
	public String deleteStudents() {

		return cacheService.delete();
	}

	@GetMapping(path = "/cacheableSquare/{number}")
	public BigDecimal cacheSquareCalculated(@PathVariable String number) {

		return cacheServiceOld.cacheableSquare(number);
	}

	@GetMapping(path = "/cachePutSquare/{number}")
	public BigDecimal cachePutSquare(@PathVariable String number) {

		return cacheServiceOld.cachePutSquare(number);
	}
	
	@GetMapping(path = "/cacheEvictSquare/{number}")
	public String cacheEvictSquare(@PathVariable String number) {

		return cacheServiceOld.cacheEvictSquare(number);
	}
	
	@GetMapping(path = "/cacheEvictSquare")
	public String cacheEvictSquare() {

		return cacheServiceOld.cacheEvictSquare();
	}

}
