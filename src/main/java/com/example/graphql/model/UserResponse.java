package com.example.graphql.model;

import lombok.*;

import java.util.List;


@Builder
@Data
@Getter
@Setter
public class UserResponse {

    private String message;
    private User user;
    private List<User> userList;



}
