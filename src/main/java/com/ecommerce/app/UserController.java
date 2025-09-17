package com.ecommerce.app;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    //Another way request
    @RequestMapping( method = RequestMethod.GET)
    public ResponseEntity<List<User>> getAllUsers(){

        List<User> userList = userService.fetchAllUsers();



        return new ResponseEntity<>(userList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@PathVariable Long id){


        return  userService.fetchUser(id)
                .map(ResponseEntity :: ok)
                .orElseGet(() ->  new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<String> createUser(@RequestBody User user){

         userService.addUser(user);

        return new ResponseEntity<>("User added Successfully", HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateUser(@RequestBody User user, @PathVariable Long id){

        boolean updated = userService.updateUser(user,id);
        if(updated){
            return new ResponseEntity<>("Updated Successfully!", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Not Updated", HttpStatus.NOT_FOUND);
        }



    }

}
