package com.example.user.controller;

import com.example.user.dto.UserDTO;
import com.example.user.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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

    @GetMapping("/users/{email}")
    public ResponseEntity<UserDTO> getUserByEmail(@PathVariable String email){
        var asLowerCaseEmail = email.toLowerCase();
        UserDTO userDTO = userService.getUserByEmail(asLowerCaseEmail);
        if (userDTO == null) ResponseEntity.notFound().build();
        return new ResponseEntity<>(userDTO, HttpStatus.OK);
    }
// Paid, Pending, Cancelled
    @GetMapping("/orders/users/{id}") // /users/{userId}/orders
    public ResponseEntity<UserDTO> getUserOrders(@PathVariable Long id){
        UserDTO userDTO = userService.getUserOrders(id);
        if (userDTO == null) ResponseEntity.notFound().build();
        return new ResponseEntity<>(userDTO, HttpStatus.OK);
    }

}
