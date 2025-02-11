package com.example.graphql.service;


import com.example.graphql.entity.UserEntity;
import com.example.graphql.model.User;
import com.example.graphql.model.UserResponse;
import com.example.graphql.repository.UserRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserResponse findAll() {
        List<UserEntity> userEntity = userRepository.findAll();

        List<User> userList = objectMapper.convertValue(userEntity, new TypeReference<>() {
        });
        UserResponse userResponse = UserResponse.builder()
                .userList(userList)
                .message("Successfully fetched the user list")
                .build();
        return userResponse;
    }

    @Override
    public UserResponse findById(Long id) {
        Optional<UserEntity> userEntity = userRepository.findById(id);
        if (userEntity.isPresent()) {
            User user = objectMapper.convertValue(userEntity.get(), User.class);
            UserResponse userResponse = UserResponse.builder()
                    .user(user)
                    .message("Fetched user successfully for id - " + id)
                    .build();
            return userResponse;
        } else {
            return UserResponse.builder()
                    .message("User not found for id - " + id)
                    .build();
        }
    }

    @Override
    public UserResponse save(User user) {
        try {
            UserEntity userEntity = new UserEntity(user.getFirstName(),user.getLastName(), user.getEmail(), user.getMobileNumber());
            userRepository.save(userEntity);
            return UserResponse.builder()
                    .message("User Saved Successfully")
                    .build();
        } catch (Exception e) {
            throw new RuntimeException("save user operation failed");
        }
    }

    @Override
    public String delete(Long id) {
        try {
            userRepository.deleteById(id);
            return "User deleted successfully";
        } catch (Exception e) {
            throw new RuntimeException("delete user operation failed");
        }
    }

    @Override
    public UserResponse update(Long id, UserEntity updatedUserEntity) {
        try {

            Optional<UserEntity> existingUser = userRepository.findById(id);

            if (existingUser.isPresent()) {
                UserEntity userEntity = existingUser.get();


                if (updatedUserEntity.getFirstName() != null) {
                    userEntity.setFirstName(updatedUserEntity.getFirstName());
                }
                if (updatedUserEntity.getLastName() != null) {
                    userEntity.setLastName(updatedUserEntity.getLastName());
                }
                if (updatedUserEntity.getEmail() != null) {
                    userEntity.setEmail(updatedUserEntity.getEmail());
                }
                if (updatedUserEntity.getMobileNumber() != null) {
                    userEntity.setEmail(updatedUserEntity.getMobileNumber());
                }
                userRepository.save(userEntity);
                User updatedUser = objectMapper.convertValue(userEntity, User.class);

                return UserResponse.builder()
                        .user(updatedUser)
                        .message("User updated successfully for id - " + id)
                        .build();
            } else {
                return UserResponse.builder()
                        .message("User not found for id - " + id)
                        .build();
            }
        } catch (Exception e) {
            throw new RuntimeException("update user operation failed", e);
        }
    }

}
