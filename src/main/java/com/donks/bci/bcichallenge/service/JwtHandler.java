package com.donks.bci.bcichallenge.service;

import com.donks.bci.bcichallenge.entity.UserEntity;

public interface JwtHandler {

    String createToken(UserEntity user);
    boolean validToken(String jwt);
    UserEntity extractFromJwt(String jwt);
}
