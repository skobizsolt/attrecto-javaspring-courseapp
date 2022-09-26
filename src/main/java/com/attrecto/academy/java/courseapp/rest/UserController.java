package com.attrecto.academy.java.courseapp.rest;

import com.attrecto.academy.java.courseapp.model.dto.UserDto;
import com.attrecto.academy.java.courseapp.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(name = "/api/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<UserDto> getUsers() {
        return userService.getAllUsers();
    }

    @GetMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UserDto getUserById(@PathVariable final Integer userId) {
        return userService.getUserById(userId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public UserDto createUser(@Valid @RequestBody final UserDto userDto) {
        return userService.createUser(userDto);
    }

    @PutMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UserDto modifyUser(@PathVariable final Integer userId, @Valid @RequestBody final UserDto userDto) {
        return userService.updateUser(userId, userDto);
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteUser(@PathVariable final Integer userId) {
        if (userId == null) {
            userService.deleteUser(userId);
        }
    }
}
