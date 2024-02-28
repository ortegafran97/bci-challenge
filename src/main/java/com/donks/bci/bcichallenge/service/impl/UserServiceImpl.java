package com.donks.bci.bcichallenge.service.impl;

import com.donks.bci.bcichallenge.entity.UserEntity;
import com.donks.bci.bcichallenge.exceptions.NotFoundException;
import com.donks.bci.bcichallenge.exceptions.NotValidException;
import com.donks.bci.bcichallenge.repository.UserRepository;
import com.donks.bci.bcichallenge.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Value("${users.email.regex}")
    private Pattern emailRegex;

    public UserEntity createUser(UserEntity user) {
//        throw new NotValidException("TESTING");
        if (user.getId() != null && userRepository.existsById(user.getId()))
            throw new NotValidException("User already exists");

        if (user.getEmail() == null) throw new NotValidException("Empty email");

        if (!emailRegex.matcher(user.getEmail()).matches()) throw new NotValidException("Invalid email");

        if (userRepository.findByEmail(user.getEmail()).isPresent())
            throw new NotValidException("Email already in use");

        return userRepository.save(user);

    }

    @Override
    public Optional<UserEntity> findById(UUID id) {
        if (!userRepository.existsById(id)) throw new NotFoundException("User not found");

        return userRepository.findById(id);

    }

    @Override
    public List<UserEntity> findAll() {
        return userRepository.findAll();
    }

    public boolean deleteById(UUID id) {
        if (!userRepository.existsById(id)) throw new NotFoundException("User not found");

        userRepository.deleteById(id);
        return !userRepository.existsById(id);
    }


}
