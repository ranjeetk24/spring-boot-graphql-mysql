package com.example.graphql.controller;


import com.example.graphql.entity.UserEntity;
import com.example.graphql.model.User;
import com.example.graphql.model.UserResponse;
import com.example.graphql.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

@Controller
public class UserController {

    @Autowired
    private UserServiceImpl userService;


    @QueryMapping
    public UserResponse users() {
        return userService.findAll();
    }

    @QueryMapping
    public UserResponse user(@Argument Long id) {
        return userService.findById(id);
    }

    @MutationMapping
    public UserResponse createUser(@Argument String firstName,@Argument String lastName, @Argument String email,@Argument String mobileNumber) {
        User user = new User(firstName,lastName, email,mobileNumber);
        return userService.save(user);
    }

    @MutationMapping
    public UserResponse updateUser(@Argument Long id, @Argument String firstName,@Argument String lastName, @Argument String email,@Argument String mobileNumber) {
        UserEntity user = new UserEntity(firstName,lastName, email, mobileNumber);
        return userService.update(id, user);
    }

    @MutationMapping
    public String deleteUser(@Argument Long id) {
        return userService.delete(id);
    }

}
