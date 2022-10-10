package com.attrecto.academy.java.courseapp.service;

import com.attrecto.academy.java.courseapp.mapper.UserMapper;
import com.attrecto.academy.java.courseapp.model.Course;
import com.attrecto.academy.java.courseapp.model.User;
import com.attrecto.academy.java.courseapp.model.dto.CreateUserDto;
import com.attrecto.academy.java.courseapp.model.dto.MinimalCourseDto;
import com.attrecto.academy.java.courseapp.model.dto.UpdateUserDto;
import com.attrecto.academy.java.courseapp.model.dto.UserDto;
import com.attrecto.academy.java.courseapp.persistence.UserRepository;
import com.attrecto.academy.java.courseapp.service.util.ServiceUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class UserService {
    private UserRepository userRepository;
    private ServiceUtil serviceUtil;

    private UserMapper userMapper;

    @Autowired
    public UserService(UserRepository userRepository, ServiceUtil serviceUtil, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.serviceUtil = serviceUtil;
        this.userMapper = userMapper;
    }

    public List<UserDto> listUsers() {
        return userMapper.usersToUserDtoList(userRepository.findAll());
    }

    public UserDto getUserById(final int id) {
        final User user = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("User cannot be found with id: %s", id)));
        return userMapper.userToUserDto(user);
    }

    //TODO: mapping issues
    public UserDto updateUser(int id, UpdateUserDto updateUserDto) {
        List<Course> courses = updateUserDto.getCourses().stream()
                .map(courseId -> serviceUtil.findCourseById(courseId)).collect(Collectors.toList());

        User user = serviceUtil.findUserById(id);
        user.setName(updateUserDto.getName());
        user.setEmail(updateUserDto.getEmail());
        user.setPassword(updateUserDto.getPassword());
        user.setRole(updateUserDto.getRole());
        user.setCourses(courses);
        userRepository.save(user);

        UserDto userDto = new UserDto();
        userDto.setId(id);
        userDto.setName(updateUserDto.getName());
        userDto.setEmail(updateUserDto.getEmail());
        userDto.setCourses(user.getCourses().stream().map(course -> {
            MinimalCourseDto minimalCourseDto = new MinimalCourseDto();
            minimalCourseDto.setId(course.getId());
            minimalCourseDto.setTitle(course.getTitle());
            minimalCourseDto.setUrl(course.getUrl());
            minimalCourseDto.setDescription(course.getDescription());
            return minimalCourseDto;
        }).collect(Collectors.toList()));

        return userDto;
//        List<Course> courses = userMapper.valuesToCourses(updateUserDto.getCourses());
//        User user = userMapper.updateUserDtoToUserModel(updateUserDto, courses);
//        user = userRepository.save(user);
//        return userMapper.userToUserDto(user);
    }

    public UserDto createUser(CreateUserDto createUserDto) {
        User user = userMapper.createUserDtoToUserModel(createUserDto);
        user = userRepository.save(user);

        return userMapper.userToUserDto(user);
    }

    public void deleteUser(final int id) {
        serviceUtil.findUserById(id);

        userRepository.deleteById(id);
    }

    public List<UserDto> filterUser(final String filter) {
        List<User> users = userRepository.findAllByNameContainingIgnoreCaseOrderByNameAscId(filter)
                .orElseThrow(() -> new NotFoundException(
                        String.format("User cannot be found with the given name: %s", filter)));
        return userMapper.usersToUserDtoList(users);
    }
}
