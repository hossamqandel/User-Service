package com.example.user.controller;

import com.example.user.dto.UserDTO;
import com.example.user.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/users")
    public ResponseEntity<List<UserDTO>> getUsers(){
        List<UserDTO> allUsers = userService.getUsers();
        if (allUsers.isEmpty()) ResponseEntity.noContent().build();
        return new ResponseEntity<>(allUsers, HttpStatus.OK);
    }

    @GetMapping("/users/search")
    public ResponseEntity<UserDTO> getUserByEmail(@RequestParam String email){
        UserDTO userDTO = userService.getUserByEmail(email);
        if (userDTO == null) ResponseEntity.notFound().build();
        return new ResponseEntity<>(userDTO, HttpStatus.OK);
    }
// Paid, Pending, Canceled
    @GetMapping("/users/{userId}/orders") // /users/{userId}/orders
    public ResponseEntity<UserDTO> getUserOrders(@PathVariable Long userId){
        var userDTO = userService.getUserById(userId);
        userDTO.setOrders(userService.getUserOrders(userId));
        return new ResponseEntity<>(userDTO, HttpStatus.OK);
    }

}
