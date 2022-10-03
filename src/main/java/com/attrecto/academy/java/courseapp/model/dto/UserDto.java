package com.attrecto.academy.java.courseapp.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UserDto {
	private int id;
	private String email;
	private String name;
	private List<MinimalCourseDto> courses;
}
