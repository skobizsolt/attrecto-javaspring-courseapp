package com.attrecto.academy.java.courseapp.persistence;

import com.attrecto.academy.java.courseapp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

	Optional<User> findByName(String name);

	Optional<List<User>> findAllByNameContainingIgnoreCaseOrderByNameAscId(String filter);
}
