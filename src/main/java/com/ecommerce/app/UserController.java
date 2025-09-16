package com.ecommerce.app;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class UserController {

    private List<User> userList = new ArrayList<>();

    @GetMapping("/api/users")
    public List<User> getAllUsers(){


        return userList;
    }

    @PostMapping("/api/users")
    public List<User> createUser(@RequestBody User user){

        userList.add(user);

        return userList;
    }

}
