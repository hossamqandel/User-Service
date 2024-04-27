package com.example.user.service;

import com.example.user.dto.OrderDTO;
import com.example.user.dto.UserDTO;
import com.example.user.entity.User;
import com.example.user.repo.UserRepository;
import com.example.user.apiclient.OrderClient;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Log4j2
@AllArgsConstructor
public class UserService {

    private final ModelMapper mapper;
    private final OrderClient orderClient;
    private final UserRepository userRepository;

    public List<UserDTO> getUsers() {
        return userRepository.findAll()
                .stream()
                .map(user -> mapper.map(user, UserDTO.class))
                .collect(Collectors.toList());
    }

    public UserDTO getUserByEmail(String email) {
        User userEntity = userRepository.findByEmail(email.toLowerCase());
        return mapper.map(userEntity, UserDTO.class);
    }

    public UserDTO getUserById(Long userId) { // userId
        User user = userRepository.findById(userId).get();
        return mapper.map(user, UserDTO.class);
    }

    public List<OrderDTO> getUserOrders(Long userId){
        return orderClient.getAllUserOrders(userId);
    }

}