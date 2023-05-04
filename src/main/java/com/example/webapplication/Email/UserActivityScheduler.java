package com.example.webapplication.Email;

import com.example.webapplication.user.entity.User;
import com.example.webapplication.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import java.time.LocalDateTime;
import java.util.List;

public class UserActivityScheduler {

    @Autowired
    private  UserRepository userRepository;

    @Autowired
    private  EmailService emailService;



    @Scheduled(fixedDelayString = "20000")
    public void reminderInactiveUser(){

        List<User> inactiveUsers=userRepository.findInactiveUsers();
        for (User user:inactiveUsers){
            emailService.sendReminderEmail(user);
        }

    }


}
