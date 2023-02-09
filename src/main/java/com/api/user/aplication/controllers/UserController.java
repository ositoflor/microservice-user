package com.api.user.aplication.controllers;

import com.api.user.domain.User;
import com.api.user.domain.dtos.UserDto;
import com.api.user.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<User> save(@RequestBody @Valid UserDto newUser) {
        User user = userService.save(newUser);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(user.getId())
                .toUri();
        return ResponseEntity.created(uri).build();
    }

    @GetMapping
    public ResponseEntity<Page<User>> getAll(Pageable pageable){
        Page<User> users = userService.findAll(pageable);
        return ResponseEntity.ok().body(users);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Optional<User>> getUser(@PathVariable(value = "id")Long id) {
        Optional<User> user = userService.getUser(id);
        return ResponseEntity.ok().body(user);
    }

    @GetMapping(value = "/name/{name}")
    public ResponseEntity<Page<User>> getByName(@PathVariable(value = "name")String name, Pageable pageable) {
        Page<User> user = userService.findByName(name,pageable);
        return ResponseEntity.ok().body(user);
    }

    @GetMapping(value = "/state/{state}")
    public ResponseEntity<Page<User>> getByState(@PathVariable(value = "state")String state, Pageable pageable) {
        Page<User> user = userService.findByState(state, pageable);
        return ResponseEntity.ok().body(user);
    }
    @GetMapping(value = "/status/{status}")
    public ResponseEntity<Page<User>> findByIsActive(@PathVariable(value = "status")Boolean status, Pageable pageable) {
        Page<User> user = userService.findByIsActive(status, pageable);
        return ResponseEntity.ok().body(user);
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity deleteUser(@PathVariable(value = "id")Long id) {
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping(value = "/change-status/{id}")
    public ResponseEntity<User> activateUser(@PathVariable(value = "id") Long id) {
        User user = userService.changeStatus(id);
        return ResponseEntity.ok().body(user);
    }

}
