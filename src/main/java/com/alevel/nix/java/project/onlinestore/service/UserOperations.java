package com.alevel.nix.java.project.onlinestore.service;

import com.alevel.nix.java.project.onlinestore.entity.User;
import com.alevel.nix.java.project.onlinestore.entity.request.UserIdRequest;
import com.alevel.nix.java.project.onlinestore.entity.request.UserRequest;
import com.alevel.nix.java.project.onlinestore.entity.response.UserResponse;

import java.util.List;

public interface UserOperations {

    UserResponse createUser(UserRequest userRequest);

    UserResponse getUserById(Long id);

    void updateUser(Long id, UserRequest userRequest);


    void giveAdminRights(UserIdRequest request);

    List<UserResponse> getAdmins();


}
