package com.franciumsources.Reactive.controller;

import com.franciumsources.Reactive.dto.CustomResponse;
import com.franciumsources.Reactive.model.UserModel;
import com.franciumsources.Reactive.services.UserService;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/users")
@Slf4j
public class UserController {
    final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    Flux<UserModel> getUsers() {
        return userService.getUsers();
    }

    @GetMapping("/{id}")
    Mono<UserModel> getUser(@PathVariable Long id) {
        return userService.getUser(id);
    }

    @PostMapping("/")
    Mono<UserModel> addUser(@RequestBody UserModel userModel) {
        log.info("new request receved====>");
        return userService.addUser(userModel);
    }

    @PutMapping("/{id}")
    Mono<ResponseEntity<String>> updateUser() {
        return Mono.just(new ResponseEntity<>("Hello world", HttpStatus.ACCEPTED));
    }

    @DeleteMapping("/")
    Mono<ResponseEntity<String>> deleteUser() {
        return Mono.just(new ResponseEntity<>("Hello world", HttpStatus.ACCEPTED));
    }
}
