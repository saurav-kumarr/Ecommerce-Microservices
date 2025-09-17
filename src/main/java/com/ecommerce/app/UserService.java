package com.ecommerce.app;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private Long nextId = 1L;

    private List<User> userList = new ArrayList<>();

    public List<User> fetchAllUsers(){
        return userList;
    }

    public void addUser(User user){
        user.setId(nextId++);
        userList.add(user);

    }

    public Optional<User> fetchUser(Long id) {


        return userList.stream().filter(user ->
                user.getId().equals(id)).findFirst();
    }

    public boolean updateUser(User updatedUser, Long id) {

        return userList.stream().filter(user ->
                user.getId().equals(id)).findFirst()
                .map(existingUser -> {
                    existingUser.setFirstName(updatedUser.getFirstName());
                    existingUser.setLastName(updatedUser.getLastName());
                    return true;
                }).orElse(false);



    }
}
