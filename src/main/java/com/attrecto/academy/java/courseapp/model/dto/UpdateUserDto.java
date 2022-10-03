package com.attrecto.academy.java.courseapp.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UpdateUserDto extends CreateUserDto {
	private List<Integer> courses;
}
