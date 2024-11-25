package com.github.ildus_akhmadiev.learning.repository;

import com.github.ildus_akhmadiev.learning.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

}