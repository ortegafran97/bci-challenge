package com.donks.bci.bcichallenge.controller;

import com.donks.bci.bcichallenge.dao.UserResponse;
import com.donks.bci.bcichallenge.entity.UserEntity;
import com.donks.bci.bcichallenge.exceptions.NotValidException;
import com.donks.bci.bcichallenge.service.JwtHandler;
import com.donks.bci.bcichallenge.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final JwtHandler jwtHandler;

    @GetMapping("/{id}")
    public ResponseEntity<Optional<UserEntity>> findById(@PathVariable("id") UUID id) {
        return ResponseEntity.ok(userService.findById(id));
    }

    @PostMapping
    public ResponseEntity<UserResponse> createUser(@RequestBody UserEntity user) {
        try {
            UserEntity createdUser = userService.createUser(user);

            String jwt = jwtHandler.createToken(createdUser);

            return ResponseEntity.ok(UserResponse.builder()
                    .id(user.getId())
                    .createdAt(user.getCreatedAt().toLocalDateTime())
                    .updatedAt(user.getUpdatedAt().toLocalDateTime())
                    .token(jwt)
                    .isActive(jwtHandler.validToken(jwt))
                    .build());

        } catch (NotValidException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());

        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "");
        }
    }

    @GetMapping
    public ResponseEntity<List<UserEntity>> findAll() {
        return ResponseEntity.ok(userService.findAll());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteUser(@PathVariable("id") UUID id) {
        return ResponseEntity.ok(userService.deleteById(id));
    }

}
