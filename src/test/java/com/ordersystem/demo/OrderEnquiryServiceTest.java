package com.ordersystem.demo;

import com.ordersystem.demo.model.Order;
import com.ordersystem.demo.repository.OrderRepository;
import com.ordersystem.demo.service.impl.OrderCreationServiceImpl;
import com.ordersystem.demo.service.impl.OrderEnquiryServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
public class OrderEnquiryServiceTest {
    @InjectMocks
    private OrderEnquiryServiceImpl orderEnquiryService;

    @Mock
    private OrderRepository orderRepository;

    @Test
    public void orderEnquiry_success(){
        Page<Order> pagedResponse = Mockito.mock(Page.class);
        Mockito.when(orderRepository.findAll(any(Pageable.class))).thenReturn(pagedResponse);
        Assertions.assertDoesNotThrow(()->orderEnquiryService.orderEnquiry(":1", ":6"));

    }

    @Test
    public void orderEnquiry_wrongPageThrowsIndexOutOfBoundsException(){
        Assertions.assertThrows(IndexOutOfBoundsException.class,()->orderEnquiryService.orderEnquiry("0", "0"));
    }

    @Test
    public void orderEnquiry_noArgumentThrowsNumberFormatException(){
        Assertions.assertThrows(NumberFormatException.class,()->orderEnquiryService.orderEnquiry(":3", ":abc"));
    }
}
