package com.attrecto.academy.java.courseapp.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.HashSet;
import java.util.Set;

public class UserDto extends MinimalUserDto {
	@Schema(description = "Id's of the user courses")
	private Set<MinimalCourseDto> courses = new HashSet<>();

	public Set<MinimalCourseDto> getCourses() {
		return courses;
	}
	public void setCourses(Set<MinimalCourseDto> courses) {
		this.courses = courses;
	}
}
