package com.example.user.apiclient; // Package name could be clien, restclient or apiclient, externalservices

import com.example.user.dto.OrderDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(
        name = "OrderServiceClient",
        url = "http://localhost:9090/api/v1"
) // OrderServiceClient
public interface OrderClient { // OrderClient

    @GetMapping("/users/{userId}/orders")
    List<OrderDTO> getAllUserOrders(@PathVariable Long userId);

}
