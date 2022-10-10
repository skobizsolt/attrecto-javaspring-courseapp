package com.attrecto.academy.java.courseapp.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.NotBlank;
import java.util.HashSet;
import java.util.Set;

@Schema
public class CourseDto extends MinimalCourseDto {
	@NotBlank
	@Schema(description = "Id of the course author", example = "1")	
	private Integer authorId;
	@Schema(description = "List of the course users", example = "user, admin")	
	private Set<MinimalUserDto> students = new HashSet<>();

	public Integer getAuthorId() {
		return authorId;
	}
	public void setAuthorId(Integer authorId) {
		this.authorId = authorId;
	}
	public Set<MinimalUserDto> getStudents() {
		return students;
	}
	public void setStudents(Set<MinimalUserDto> students) {
		this.students = students;
	}
}
