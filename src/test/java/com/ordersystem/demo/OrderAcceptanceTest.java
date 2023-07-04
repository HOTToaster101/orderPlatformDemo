package com.ordersystem.demo;

import com.ordersystem.demo.model.Order;
import com.ordersystem.demo.repository.OrderRepository;
import com.ordersystem.demo.service.impl.OrderAcceptanceServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;

@ExtendWith(MockitoExtension.class)
public class OrderAcceptanceTest {
    @InjectMocks
    private OrderAcceptanceServiceImpl orderAcceptanceService;

    @Mock
    private OrderRepository orderRepository;

    @Test
    public void takeOrder_success(){
        Order mockerOrder = Mockito.mock(Order.class);
        Map<String, String> testmap = new HashMap();
        testmap.put("status", "TAKEN");
        Map<String, Object> expectedResult = new HashMap();
        expectedResult.put("status", "SUCCESS");
        Mockito.when(orderRepository.findById(anyInt())).thenReturn(Optional.of(mockerOrder));
        Mockito.when(mockerOrder.getStatus()).thenReturn("UNASSIGNED");

        Assertions.assertEquals(expectedResult, (orderAcceptanceService.takeOrder("1", testmap)));
    }

    @Test
    public void takeOrder_wrongArgumentThrowsIllegalArgumentException(){
        Order mockerOrder = Mockito.mock(Order.class);
        Map<String, String> testmap = new HashMap();
        testmap.put("status", "TAKEN");
        Map<String, Object> expectedResult = new HashMap();
        expectedResult.put("status", "SUCCESS");

        Assertions.assertThrows(IllegalArgumentException.class, ()->orderAcceptanceService.takeOrder("test",testmap));
    }

    @Test
    public void takeOrder_wrongIDThrowsIllegalArgumentException(){
        Order mockerOrder = Mockito.mock(Order.class);
        Map<String, String> testmap = new HashMap();
        testmap.put("status", "TAKEN");
        Map<String, Object> expectedResult = new HashMap();
        expectedResult.put("status", "SUCCESS");

        Assertions.assertThrows(NoSuchElementException.class, ()->orderAcceptanceService.takeOrder("222",testmap));
    }

    @Test
    public void takeOrder_isTAKENThrowsIllegalArgumentException(){
        Order mockerOrder = Mockito.mock(Order.class);
        Map<String, String> testmap = new HashMap();
        testmap.put("status", "TAKEN");
        Map<String, Object> expectedResult = new HashMap();
        expectedResult.put("status", "SUCCESS");
        Mockito.when(orderRepository.findById(anyInt())).thenReturn(Optional.of(mockerOrder));
        Mockito.when(mockerOrder.getStatus()).thenReturn("TAKEN");

        Assertions.assertThrows(IllegalArgumentException.class, ()->orderAcceptanceService.takeOrder("1",testmap));
    }

    @Test
    public void takeOrder_saveOrderThrowsIllegalArgumentException(){
        Order mockerOrder = Mockito.mock(Order.class);
        Map<String, String> testmap = new HashMap();
        testmap.put("status", "TAKEN");
        Map<String, Object> expectedResult = new HashMap();
        expectedResult.put("status", "SUCCESS");
        Mockito.when(orderRepository.findById(anyInt())).thenReturn(Optional.of(mockerOrder));
        Mockito.when(mockerOrder.getStatus()).thenReturn("UNASSIGNED");
        Mockito.when(orderRepository.save(any(Order.class))).thenThrow(new IllegalArgumentException());

        Assertions.assertThrows(IllegalArgumentException.class, ()->orderAcceptanceService.takeOrder("1",testmap));
    }


}
