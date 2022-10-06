package com.attrecto.academy.java.courseapp.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.HashSet;
import java.util.Set;

public class UpdateUserDto extends CreateUserDto {
	@Schema(description = "Id's of the user courses")
	private Set<Integer> courses = new HashSet<>();
	public Set<Integer> getCourses() {
		return courses;
	}
	public void setCourses(Set<Integer> courses) {
		this.courses = courses;
	}
}
