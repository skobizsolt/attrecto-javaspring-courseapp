package com.attrecto.academy.java.courseapp.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CourseDto {
	private Integer id;
	private String title;
	private String description;
	private String url;
	private Integer authorId;
	private List<MinimalUserDto> students;
}
