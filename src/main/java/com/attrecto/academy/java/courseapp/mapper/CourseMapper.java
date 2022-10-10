package com.attrecto.academy.java.courseapp.mapper;

import com.attrecto.academy.java.courseapp.model.Course;
import com.attrecto.academy.java.courseapp.model.dto.CourseDto;
import com.attrecto.academy.java.courseapp.model.dto.CreateCourseDto;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper(uses = UserMapper.class)
public interface CourseMapper {
    CourseDto courseToCourseDto(Course course);

    List<CourseDto> coursesToCourseDtoList(List<Course> courses);

    Course createCourseDtoToCourseModel(CreateCourseDto createCourseDto);
}
