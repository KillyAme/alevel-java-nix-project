package com.alevel.nix.java.project.onlinestore.service;

import com.alevel.nix.java.project.onlinestore.entity.Basket;
import com.alevel.nix.java.project.onlinestore.entity.User;
import com.alevel.nix.java.project.onlinestore.entity.enums.Role;
import com.alevel.nix.java.project.onlinestore.entity.request.UserIdRequest;
import com.alevel.nix.java.project.onlinestore.entity.request.UserRequest;
import com.alevel.nix.java.project.onlinestore.entity.response.UserResponse;
import com.alevel.nix.java.project.onlinestore.exception.EmailAlreadyTakenException;
import com.alevel.nix.java.project.onlinestore.exception.NotAuthorizedException;
import com.alevel.nix.java.project.onlinestore.exception.UserNameAlreadyTakenException;
import com.alevel.nix.java.project.onlinestore.exception.UserNotFoundException;
import com.alevel.nix.java.project.onlinestore.repository.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class UserService implements UserOperations {

    private final UserRepository userRepository;


    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserResponse createUser(UserRequest userRequest) {
        String userName = userRequest.getName();
        String email = userRequest.getEmail();
        if (userRepository.existsByName(userName)) {
            throw new UserNameAlreadyTakenException(userName);
        }
        if (userRepository.existsByEmail(email)) {
            throw new EmailAlreadyTakenException(email);
        }
        User user = new User();
        user.setName(userName);
        user.setEmail(email);
        user.setRole(Role.USER);
        user.setPhone(userRequest.getPhone());
        user.setPassword(passwordEncoder.encode(userRequest.getPassword()));
        Basket basket = new Basket();
        basket.setBasketUser(user);
        user.setUserBasket(basket);

        return new UserResponse(userRepository.save(user));
    }

    @Override
    public UserResponse getUserById(Long id) {
        UserResponse response = new UserResponse();
        User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));

        String nameAuthorized = SecurityContextHolder.getContext().getAuthentication().getName();
        if (!nameAuthorized.equals(user.getName())) {
            throw new NotAuthorizedException("You cannot view someone else’s profile");
        }
        response.setId(user.getId());
        response.setUserName(user.getName());
        response.setEmail(user.getEmail());
        response.setPhone(user.getPhone());
        response.setRole(user.getRole());
        return response;
    }

    @Override
    public void updateUser(Long id, UserRequest userRequest) {
        User changedUser = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
        String nameAuthorized = SecurityContextHolder.getContext().getAuthentication().getName();
        if (!nameAuthorized.equals(changedUser.getName())) {
            throw new NotAuthorizedException("You can’t change someone else’s profile");
        }

        String userName = userRequest.getName();
        String email = userRequest.getEmail();
        String phone = userRequest.getPhone();
        String password = userRequest.getPassword();

        if (userName != null) {
            if (userRepository.existsByName(userName)) {
                throw new UserNameAlreadyTakenException(userName);
            }
            changedUser.setName(userName);
        }
        if (email != null) {
            if (userRepository.existsByEmail(email)) {
                throw new EmailAlreadyTakenException(email);
            }
            changedUser.setEmail(email);
        }
        if (phone != null) {
            changedUser.setPhone(phone);
        }
        if (password != null) {
            changedUser.setPassword(passwordEncoder.encode(password));
        }
        userRepository.save(changedUser);
    }

    @Override
    public void giveAdminRights(UserIdRequest request) {
        User user = userRepository.findById(request.getId()).orElseThrow(() -> new UserNotFoundException(request.getId()));
        user.setRole(Role.ADMIN);
        userRepository.save(user);
    }

    @Override
    public List<UserResponse> getAdmins() {
        List<User> allAdmins = userRepository.findAllByRole(Role.ADMIN);
        List<UserResponse> responseAdmins = new ArrayList<>();
        for (User admin : allAdmins) {
            responseAdmins.add(new UserResponse(admin));
        }
        return responseAdmins;
    }

}
