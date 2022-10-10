package com.attrecto.academy.java.courseapp.service;

import com.attrecto.academy.java.courseapp.mapper.CourseMapper;
import com.attrecto.academy.java.courseapp.model.Course;
import com.attrecto.academy.java.courseapp.model.dto.CourseDto;
import com.attrecto.academy.java.courseapp.model.dto.CreateCourseDto;
import com.attrecto.academy.java.courseapp.model.dto.MinimalUserDto;
import com.attrecto.academy.java.courseapp.persistence.CourseRepository;
import com.attrecto.academy.java.courseapp.service.util.ServiceUtil;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.util.List;
import java.util.stream.Collectors;

//TODO: haven't rewritten yet
@Service
public class CourseService {
	private CourseRepository courseRepository;
	private ServiceUtil serviceUtil;

	private CourseMapper courseMapper;

	public CourseService(CourseRepository courseRepository, ServiceUtil serviceUtil, CourseMapper courseMapper) {
		this.courseRepository = courseRepository;
		this.serviceUtil = serviceUtil;
		this.courseMapper = courseMapper;
	}

	public List<CourseDto> listAllCourses() {
		return courseMapper.coursesToCourseDtoList(courseRepository.findAll());
	}

	public CourseDto getCourseById(int id) {
		Course course = courseRepository.findById(id)
				.orElseThrow(() -> new NotFoundException(String.format("A kurzus nem található a megadott id-val %s", id)));
		return courseMapper.courseToCourseDto(course);
	}

	public CourseDto createCourse(CreateCourseDto createCourseDto) {
		Course course = new Course();
		course.setTitle(createCourseDto.getTitle());
		course.setDescription(createCourseDto.getDescription());
		course.setUrl(createCourseDto.getUrl());
		course.setAuthorId(serviceUtil.findUserById(createCourseDto.getAuthorId()).getId());
		course.setStudents(createCourseDto.getStudentIds().stream()
				.map(studentId -> serviceUtil.findUserById(studentId)).collect(Collectors.toList()));

		course = courseRepository.save(course);
		
		CourseDto courseDto = new CourseDto();
		courseDto.setId(course.getId());
		courseDto.setTitle(course.getTitle());
		courseDto.setDescription(course.getDescription());
		courseDto.setUrl(course.getUrl());
		courseDto.setAuthorId(course.getAuthorId());
		courseDto.setStudents(course.getStudents().stream().map(student -> {
			MinimalUserDto minimalUserDto = new MinimalUserDto();
			minimalUserDto.setId(student.getId());
			minimalUserDto.setName(student.getName());
			minimalUserDto.setEmail(student.getEmail());
			return minimalUserDto;
		}).collect(Collectors.toList()));
		
		return courseDto;
	}

	//TODO: mapping issues
	public CourseDto updateCourse(final int id, CreateCourseDto updateCourseDto) {
		Course course = courseRepository.findById(id)
				.orElseThrow(() -> new NotFoundException(String.format("A kurzus nem található a megadott id-val %s", id)));
		course = courseMapper.createCourseDtoToCourseModel(updateCourseDto);
		courseRepository.save(course);
		return courseMapper.courseToCourseDto(course);
	}

	public void deleteCourse(int id) {
		serviceUtil.findCourseById(id);

		courseRepository.deleteById(id);
	}
}
