package com.ecommerce.service;


import com.ecommerce.dto.AddressDTO;
import com.ecommerce.dto.UserRequest;
import com.ecommerce.dto.UserResponse;
import com.ecommerce.model.Address;
import com.ecommerce.model.User;
import com.ecommerce.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    //private Long nextId = 1L;

    //private List<User> userList = new ArrayList<>();

    public List<UserResponse> fetchAllUsers(){

        return userRepository.findAll().stream()
                .map(this :: mapToUserResponse)
                .collect(Collectors.toList());
    }

    public void addUser(UserRequest userRequest){

        User user = new User();
        updateUserFromRequest(user, userRequest);
        userRepository.save(user);

    }



    public Optional<UserResponse> fetchUser(Long id) {


        return userRepository.findById(id).map(this::mapToUserResponse);
                //.stream().filter(user ->
                //user.getId().equals(id)).findFirst();
    }

    public boolean updateUser(UserRequest updatedUserRequest, Long id) {

       return userRepository.findById(id).map(existingUser -> {
          updateUserFromRequest(existingUser, updatedUserRequest);
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

    private UserResponse mapToUserResponse(User user){

        UserResponse response = new UserResponse();
        response.setId(String.valueOf(user.getId()));
        response.setFirstName(user.getFirstName());
        response.setLastName(user.getLastName());
        response.setEmail(user.getEmail());
        response.setPhone(user.getPhone());
        response.setRole(user.getRole());

        if(user.getAddress() != null) {
            AddressDTO addressDTO = new AddressDTO();
            addressDTO.setStreet(user.getAddress().getStreet());
            addressDTO.setCity(user.getAddress().getCity());
            addressDTO.setState(user.getAddress().getState());
            addressDTO.setCountry(user.getAddress().getCountry());
            addressDTO.setZipcode(user.getAddress().getZipcode());
            response.setAddress(addressDTO);
        }

        return response;

    }

    private void updateUserFromRequest(User user, UserRequest userRequest) {

        user.setFirstName(userRequest.getFirstName());
        user.setLastName(userRequest.getLastName());
        user.setEmail(userRequest.getEmail());
        user.setPhone(userRequest.getPhone());

        if(userRequest.getAddress() != null) {
            Address address = new Address();
            address.setStreet(userRequest.getAddress().getStreet());
            address.setState(userRequest.getAddress().getState());
            address.setZipcode(userRequest.getAddress().getZipcode());
            address.setCity(userRequest.getAddress().getCity());
            address.setCountry(userRequest.getAddress().getCountry());

            user.setAddress(address);
        }



    }

}
