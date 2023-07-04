package com.ordersystem.demo.service;

import com.ordersystem.demo.model.Order;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Map;

public interface OrderEnquiryService {
    public List<Order> orderEnquiry(String page, String limit);

}
