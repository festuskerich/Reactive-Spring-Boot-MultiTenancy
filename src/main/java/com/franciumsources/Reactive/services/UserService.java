package com.franciumsources.Reactive.services;


import com.franciumsources.Reactive.model.UserModel;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface UserService {
    Mono<UserModel> add(UserModel userModel);
    Flux<UserModel> getUsers();
}
