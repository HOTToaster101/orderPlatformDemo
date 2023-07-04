package com.ordersystem.demo;


import com.ordersystem.demo.model.Order;
import com.ordersystem.demo.repository.OrderRepository;
import com.ordersystem.demo.service.OrderCreationService;
import com.ordersystem.demo.service.impl.OrderCreationServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
public class OrderCreationServiceTest {

    @InjectMocks
    private OrderCreationServiceImpl orderCreationService;

    @Mock
    private OrderRepository orderRepository;

    @Test
    public void createOrder_success(){
        Order order =Mockito.mock(Order.class);
        Map<String, List<String>> testmap = new HashMap();
        String[] OCoordinate = {"0", "0"};
        List<String> stubOdestination = Arrays.asList(OCoordinate);
        testmap.put("origin", stubOdestination);
        String[] DCoordinate = {"3", "4"};
        List<String> stubDestination = Arrays.asList(DCoordinate);
        testmap.put("destination", stubDestination);
        Mockito.when(orderRepository.save(any())).thenReturn(order);

        Assertions.assertEquals(5, (orderCreationService.createOrder(testmap)).get("distance"));

    }

    @Test
    public void createOrder_throwNumberFormatException(){
        Map<String, List<String>> testmap = new HashMap();
        String[] OCoordinate = {"", ""};
        List<String> stubOdestination = Arrays.asList(OCoordinate);
        testmap.put("origin", stubOdestination);
        String[] DCoordinate = {"", ""};
        List<String> stubDestination = Arrays.asList(DCoordinate);
        testmap.put("destination", stubDestination);

        Assertions.assertThrows(NumberFormatException.class, ()->orderCreationService.createOrder(testmap));
    }

    @Test
    public void createOrder_throwIllegalArgumentException(){
        Map<String, List<String>> testmap = new HashMap();
        String[] OCoordinate = {"2", "2"};
        List<String> stubOdestination = Arrays.asList(OCoordinate);
        testmap.put("origin", stubOdestination);
        String[] DCoordinate = {"2", "2"};
        List<String> stubDestination = Arrays.asList(DCoordinate);
        testmap.put("destination", stubDestination);
        Mockito.when(orderRepository.save(any(Order.class))).thenThrow(new IllegalArgumentException());

        Assertions.assertThrows(IllegalArgumentException.class, ()->orderCreationService.createOrder(testmap));
    }
}

