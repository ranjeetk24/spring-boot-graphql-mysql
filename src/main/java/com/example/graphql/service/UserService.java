package com.example.graphql.service;

import com.example.graphql.entity.UserEntity;
import com.example.graphql.model.User;
import com.example.graphql.model.UserResponse;

public interface UserService {

    UserResponse findAll();
    UserResponse findById(Long id);
    UserResponse save(User user);
    String  delete(Long id);
    UserResponse update(Long id, UserEntity updatedUserEntity);
}
