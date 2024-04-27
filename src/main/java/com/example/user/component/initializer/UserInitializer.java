package com.example.user.component.initializer;

import com.example.user.entity.User;
import com.example.user.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class UserInitializer implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    private void createUsers(){
        var user = new User(1L, "Hossam", "hossam@gmail.com".toLowerCase());
        var user2 = new User(2L, "Lara", "lara@gmail.com".toLowerCase());
        userRepository.saveAll(List.of(user, user2));
    }

    @Override
    public void run(String... args) {
        createUsers();
        System.out.println("Users Saved Into DataBase");
    }

}
