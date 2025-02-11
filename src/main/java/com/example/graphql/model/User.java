package com.example.graphql.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class User {

    private long id;
    private String firstName;
    private String lastName;
    private String email;
    private String mobileNumber;

    public User(String firstname, String lastname,String email, String mobileNumber) {
        this.firstName=firstname;
        this.lastName = lastname;
        this.email =email;
        this.mobileNumber = mobileNumber;
    }
}
