package com.github.ildus_akhmadiev.learning.repository;

import com.github.ildus_akhmadiev.learning.enums.Role;
import com.github.ildus_akhmadiev.learning.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;


public interface UserRepository extends JpaRepository<User, Long>, JpaSpecificationExecutor<User> {
    User findByEmail(String email);
    User findByEmailAndRoles(String email, Role role);
    Page<User> findAll(Pageable pageable);

}