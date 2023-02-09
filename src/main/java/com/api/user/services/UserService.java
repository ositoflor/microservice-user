package com.api.user.services;

import com.api.user.domain.User;
import com.api.user.domain.dtos.UserDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface UserService {
    User save(UserDto user);
    Page<User> findAll(Pageable pageable);
    Optional<User> getUser(Long id);
    Page<User> findByName(String name, Pageable pageable);
    Page<User> findByState(String state, Pageable pageable);
    Page<User> findByIsActive(Boolean status, Pageable pageable);
    void delete(Long id);
    User changeStatus(Long id);
}
