package ua.com.alevel.service;

import ua.com.alevel.entity.User;
import ua.com.alevel.repository.UserRepository;

import java.util.List;

public class UserService {

    private final UserRepository userRepository;

    public UserService() {
        this.userRepository = new UserRepository();
    }

    public User getUserById(Long id) {
        if (!isValidId(id)) {
            return null;
        }
        return userRepository.getUserById(id);
    }

    public List<User> getAllUsers() {
        return userRepository.getAllUsers();
    }

    public void addUser(User user) {
        userRepository.addUser(user);
    }

    public void updateUser(User user) {
        userRepository.updateUser(user);
    }

    public void deleteUserById(Long id) {
        if (!isValidId(id)) {
            return;
        }
        userRepository.deleteUserById(id);
    }

    private boolean isValidId(Long id) {
        return id > 0 && id <= getAllUsers().size();
    }
}
