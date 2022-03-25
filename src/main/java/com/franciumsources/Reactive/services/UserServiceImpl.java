package com.franciumsources.Reactive.services;

import com.franciumsources.Reactive.dto.CustomResponse;
import com.franciumsources.Reactive.model.UserModel;
import com.franciumsources.Reactive.repository.UserRepository;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class UserServiceImpl implements UserService {

    public final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Mono<UserModel> addUser(UserModel userModel) {
        return userRepository.save(userModel);
    }

    @Override
    public Flux<UserModel> getUsers() {
        return userRepository.findAll();
    }

    @Override
    public Mono<UserModel> getUser(Long id) {
        return userRepository.findById(id);
    }

}