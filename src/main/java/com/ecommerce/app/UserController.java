package com.ecommerce.app;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;


    @GetMapping("/api/users")
    public List<User> getAllUsers(){

        List<User> userList = userService.fetchAllUsers();
        return userList;
    }

    @GetMapping("/api/users/{id}")
    public User getUser(@PathVariable Long id){

        User user = userService.fetchUser(id);
        return user;
    }

    @PostMapping("/api/users")
    public String createUser(@RequestBody User user){

         userService.addUser(user);

        return "User added Successfully";
    }

}
