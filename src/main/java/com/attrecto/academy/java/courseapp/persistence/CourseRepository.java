package com.attrecto.academy.java.courseapp.persistence;

import com.attrecto.academy.java.courseapp.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Integer> {

}
