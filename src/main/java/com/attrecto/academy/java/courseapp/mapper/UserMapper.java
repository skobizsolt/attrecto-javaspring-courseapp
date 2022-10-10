package com.attrecto.academy.java.courseapp.mapper;

import com.attrecto.academy.java.courseapp.model.Course;
import com.attrecto.academy.java.courseapp.model.User;
import com.attrecto.academy.java.courseapp.model.dto.CreateUserDto;
import com.attrecto.academy.java.courseapp.model.dto.UpdateUserDto;
import com.attrecto.academy.java.courseapp.model.dto.UserDto;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper(uses = CourseMapper.class)
public interface UserMapper {
    UserDto userToUserDto(User user);

    List<UserDto> usersToUserDtoList(List<User> users);

    User createUserDtoToUserModel(CreateUserDto userDto);

    User updateUserDtoToUserModel(UpdateUserDto updateUserDto, List<Course> courses);

    //Mapstruct helps if you messed up something
    Course valueToCourse(Integer value);
    List<Course> valuesToCourses(List<Integer> values);
}
