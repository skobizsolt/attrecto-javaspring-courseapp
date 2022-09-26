package com.attrecto.academy.java.courseapp.service;

import com.attrecto.academy.java.courseapp.model.dto.UserDto;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;

@Service
public class UserService {

    List<UserDto> users;

    public UserService() {
        //first user
        final UserDto firstUser = new UserDto();
        firstUser.setId(1);
        firstUser.setName("firstUser");
        firstUser.setEmail("firstUser@attrecto.com");
        firstUser.setFirstName("first");
        firstUser.setLastName("User");
        firstUser.setBirthDate(LocalDate.of(1980, 11, 13));

        //second user
        final UserDto secondUser = new UserDto();
        secondUser.setId(2);
        secondUser.setName("secondUser");
        secondUser.setEmail("secondUser@attrecto.com");
        secondUser.setFirstName("second");
        secondUser.setLastName("User");
        secondUser.setBirthDate(LocalDate.of(1999, 11, 30));

        this.users = List.of(firstUser, secondUser);
    }

    public List<UserDto> getAllUsers() {
        return users;
    }

    public UserDto getUserById(final Integer userId) {
        return users.get(0);
    }

    public UserDto createUser(@Valid final UserDto userDto) {
        return mockUser();
    }

    public UserDto updateUser(final Integer userId, @Valid final UserDto userDto) {
        final UserDto updatedUser = new UserDto();
        updatedUser.setName(users.get(0).getName());
        updatedUser.setBirthDate(users.get(0).getBirthDate());
        return updatedUser;
    }

    public void deleteUser(final Integer userId) {

    }

    private UserDto mockUser() {
        final UserDto newUser = new UserDto();
        newUser.setId(3);
        newUser.setName("newUser");
        newUser.setEmail("new.email@attrecto.com");
        newUser.setFirstName("new");
        newUser.setLastName("User");
        newUser.setBirthDate(LocalDate.of(1971, 5, 11));
        return newUser;
    }
}
