package com.franciumsources.Reactive.services;

import com.franciumsources.Reactive.model.UserModel;
import com.franciumsources.Reactive.repository.UserRepository;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{
    
    public final UserRepository userRepository=null;

    @Override
    public Mono<UserModel> add(UserModel userModel) {
        return userRepository.save(userModel);
    }

    @Override
    public Flux<UserModel> getUsers() {
        return userRepository.findAll();
    }

}