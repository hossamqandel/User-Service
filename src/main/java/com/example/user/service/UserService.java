package com.example.user.service;

import com.example.user.dto.OrderDTO;
import com.example.user.dto.UserDTO;
import com.example.user.entity.User;
import com.example.user.repo.UserRepository;
import com.example.user.web_service.OrderProvider;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Log4j2
@AllArgsConstructor
public class UserService implements CommandLineRunner {

    private final ModelMapper mapper;
    private final OrderProvider orderProvider;
    private final UserRepository userRepository;

    @Override
    public void run(String... args) {
        createUsers();
    }

    public List<UserDTO> getUsers() {
        return userRepository.findAll()
                .stream()
                .map(user -> mapper.map(user, UserDTO.class))
                .collect(Collectors.toList());
    }

    public UserDTO getUserByEmail(String email) {
        return mapper.map(userRepository.findByEmail(email), UserDTO.class);
    }

    public UserDTO getUserOrders(Long id) {
        User user = userRepository.findById(id).get();
        UserDTO userDTO = mapper.map(user, UserDTO.class);
        List<OrderDTO> orders = orderProvider.getAllUserOrders(id);
        userDTO.setOrders(orders);
        return userDTO;
    }


    private void createUsers(){
        var user = new User(null, "Hossam", "hossam@gmail.com".toLowerCase());
        var user2 = new User(null, "Lara", "lara@gmail.com".toLowerCase());
        userRepository.saveAll(List.of(user, user2));
    }

}