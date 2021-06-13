package ua.com.alevel.controller;

import ua.com.alevel.entity.User;
import ua.com.alevel.service.UserService;

import java.util.List;

public class UserController {

    private final UserService userService;

    public UserController() {
        userService = new UserService();
    }

    public User getUserById(Long id) {
        return userService.getUserById(id);
    }

    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    public void addUser(User user) {
        userService.addUser(user);
    }

    public void updateUser(User user) {
        userService.updateUser(user);
    }

    public void deleteUserById(Long id) {
        userService.deleteUserById(id);
    }
}
