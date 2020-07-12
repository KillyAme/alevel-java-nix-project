package com.alevel.nix.java.project.onlinestore.controller;

import com.alevel.nix.java.project.onlinestore.entity.request.UserIdRequest;
import com.alevel.nix.java.project.onlinestore.entity.response.UserResponse;
import com.alevel.nix.java.project.onlinestore.service.UserOperations;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admins")
public class AdminController {

    private final UserOperations userOperations;

    public AdminController(UserOperations userOperations) {
        this.userOperations = userOperations;
    }

    @PutMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void giveAdminRights(@RequestBody UserIdRequest request) {
        userOperations.giveAdminRights(request);
    }

    @GetMapping
    public List<UserResponse> getAdmins() {
        return userOperations.getAdmins();
    }

}
