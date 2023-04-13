package com.example.webapplication.user.service;

import com.example.webapplication.user.entity.User;
import com.example.webapplication.user.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Page<User> findAllUsers(Integer pageNr, Integer pageSize, String sortBy) {
        Pageable pageable = PageRequest.of(pageNr, pageSize, Sort.by(sortBy).ascending());
        return userRepository.findAll(pageable);
    }

    public void createUser(User user) {
        userRepository.save(user);
    }

    public User getSingleUser(Long id) throws Exception {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            return user.get();
        } else throw new Exception("User does not exist");

    }
    public User updateUser(Long id, User user) {
        Optional<User> user1 = userRepository.findById(id);
        try {
            if (user1.isPresent()) {
                if (user1.get().getRole().equals("SuperAdmin")) {
                    user1.get().setUsername(user.getUsername());
                    user1.get().setRole(user.getRole());
                    user1.get().setCreatedAt(user.getCreatedAt());
                } else if (user1.get().getRole().equals("Admin")) {
                    user1.get().setUsername(user.getUsername());
                    user1.get().setRole(user.getRole());
                    user1.get().setCreatedAt(user.getCreatedAt());
                } else {
                    user1.get().setUsername(user.getUsername());
                }
            }
            return userRepository.save(user1.get());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
