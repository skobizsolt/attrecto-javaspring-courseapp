package com.attrecto.academy.java.courseapp.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MinimalCourseDto {
	private int id;
	private String title;
	private String description;
	private String url;
}
