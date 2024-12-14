package com.github.ildus_akhmadiev.learning.service;

import com.github.ildus_akhmadiev.learning.model.User;
import com.github.ildus_akhmadiev.learning.repository.UserRepository;
import com.github.ildus_akhmadiev.learning.specification.UserSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public List<User> findUsersByCriteria(String username) {
        Specification<User> spec = UserSpecification.findByCriteria(username);
        return userRepository.findAll(spec);
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }
    public Page<User> findAll(Pageable pageable) {
       return userRepository.findAll(pageable);
    }
}