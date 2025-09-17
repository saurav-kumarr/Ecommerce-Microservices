package com.ecommerce.app;



import lombok.Data;



@Data
//@JsonPropertyOrder({ "id", "firstName", "lastName" })

public class User {


    private Long id;

    private String firstName;
    private String lastName;

}
