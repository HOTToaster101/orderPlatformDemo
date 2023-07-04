package com.ordersystem.demo.service;

import java.util.List;
import java.util.Map;

public interface OrderCreationService {
    public Map<String, Object> createOrder(Map<String, List<String>> location);
}
