package com.attrecto.academy.java.courseapp.persistence;

import com.attrecto.academy.java.courseapp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

	public Optional<User> findByName(String name);
}
