package com.example.webapplication.user;

import com.example.webapplication.user.entity.User;
import com.example.webapplication.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

public class UserController {

    @Autowired
    UserService userService;
    @GetMapping("/users")
    public Page<User> getAllUsers(@RequestParam(defaultValue = "0") Integer pageNo,
                                  @RequestParam(defaultValue = "10") Integer pageSize,
                                  @RequestParam(defaultValue = "id") String sortBy){
        return userService.findAllUsers(pageNo,pageSize,sortBy);
    }
    @PostMapping("/user")
    public void createUser(@RequestBody User user){
        userService.createUser(user);
    }
    @PutMapping("/user/{id}")
    public User updateUser(@PathVariable Long id,@RequestBody User user) throws Exception {
        return userService.updateUser(id,user);
    }
    @DeleteMapping("/user/{id}")
    public void deleteUser(@PathVariable Long id){
        userService.deleteUser(id);
    }

}
