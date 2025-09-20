package com.ecommerce.service;


import com.ecommerce.model.User;
import com.ecommerce.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    //private Long nextId = 1L;

    //private List<User> userList = new ArrayList<>();

    public List<User> fetchAllUsers(){

        return userRepository.findAll();
    }

    public void addUser(User user){

        userRepository.save(user);

    }

    public Optional<User> fetchUser(Long id) {


        return userRepository.findById(id);
                //.stream().filter(user ->
                //user.getId().equals(id)).findFirst();
    }

    public boolean updateUser(User updatedUser, Long id) {

       return userRepository.findById(id).map(existingUser -> {
           existingUser.setFirstName(updatedUser.getFirstName());
           existingUser.setLastName(updatedUser.getLastName());
           userRepository.save(existingUser);
           return true;
       }).orElse(false);


        /* return userList.stream().filter(user ->
                user.getId().equals(id)).findFirst()
                .map(existingUser -> {
                    existingUser.setFirstName(updatedUser.getFirstName());
                    existingUser.setLastName(updatedUser.getLastName());
                    return true;
                }).orElse(false);*/



    }
}
