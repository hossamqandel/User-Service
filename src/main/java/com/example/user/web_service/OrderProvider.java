package com.example.user.web_service; // Package name could be clien, restclient or apiclient, externalservices

import com.example.user.dto.OrderDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "OrderService", url = "http://localhost:9090/api/v1") // OrderServiceClient
public interface OrderProvider { // OrderClient

    @GetMapping("/orders/users/{id}")
    List<OrderDTO> getAllUserOrders(@PathVariable Long id);

}
