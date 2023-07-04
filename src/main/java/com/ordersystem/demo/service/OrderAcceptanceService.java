package com.ordersystem.demo.service;

import java.util.List;
import java.util.Map;

public interface OrderAcceptanceService {
    public Map<String, Object> takeOrder(String id, Map<String, String> map);

}
