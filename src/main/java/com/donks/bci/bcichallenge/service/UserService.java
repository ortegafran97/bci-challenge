package com.donks.bci.bcichallenge.service;

import com.donks.bci.bcichallenge.entity.UserEntity;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserService {
    //CREATE
    UserEntity createUser(UserEntity user);

    //READ
    Optional<UserEntity> findById(UUID id);

    List<UserEntity> findAll();

    //DELETE
    boolean deleteById(UUID id);
}
