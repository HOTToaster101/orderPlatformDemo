package com.ordersystem.demo.controller;

import com.ordersystem.demo.model.Order;
import com.ordersystem.demo.service.OrderAcceptanceService;
import com.ordersystem.demo.service.OrderCreationService;
import com.ordersystem.demo.service.OrderEnquiryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
//@RequestMapping("/platform")
public class OrderSystemController {
    @Autowired
    private OrderAcceptanceService orderAcceptanceService;
    @Autowired
    private OrderCreationService orderCreationService;
    @Autowired
    private OrderEnquiryService orderEnquiryService;

    public String hello()
    {
        return "Hello User";
    }

    @PostMapping("/orders")
    public Map<String, Object> createOrder(@RequestBody Map<String, List<String>> location)
    {
        return orderCreationService.createOrder(location);
    }

    @PatchMapping("/orders/{id}")
    public Map<String, Object> acceptOrder(@PathVariable String id, @RequestBody Map<String, String> status)
    {
        return orderAcceptanceService.takeOrder(id, status);
    }

    @GetMapping("/orders")
    public List<Order> orderEnquiry(@RequestParam(value = "page") String page, @RequestParam(value = "limit") String limit)
    {
        return orderEnquiryService.orderEnquiry(page, limit);
    }


}

