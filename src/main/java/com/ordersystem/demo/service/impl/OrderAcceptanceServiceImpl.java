package com.ordersystem.demo.service.impl;

import com.ordersystem.demo.model.Order;
import com.ordersystem.demo.repository.OrderRepository;
import com.ordersystem.demo.service.OrderAcceptanceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

@Slf4j
@Service
public class OrderAcceptanceServiceImpl implements OrderAcceptanceService {

    private String status;
    private Order order;
    @Autowired
    private OrderRepository orderRepository;
    @Override
    public Map<String, Object> takeOrder(String id, Map<String, String> map){
        for(Map.Entry<String, String> entry : map.entrySet()) {
            if(entry.getKey().equals("status")){
                status = entry.getValue();
            }
        }
        id = id.replace(":","");

        try {
            //find order by id
            order = orderRepository.findById(Integer.parseInt(id)).get();

        }catch (IllegalArgumentException e){
            throw new IllegalArgumentException("No argument is found in query.");
        }catch (NoSuchElementException e){
            throw new NoSuchElementException("No value is found in entity");
        }
        //find fields by key from order and set new value
        map.forEach((key,value)->{
            Field field = ReflectionUtils.findField(Order.class, key);
            field.setAccessible(true);
            ReflectionUtils.setField(field,order,value);
        });
        try {
            if(order.getStatus()!="UNASSIGNED"){
                throw new IllegalArgumentException("The entity is already assigned.");
            }
            orderRepository.save(order);
            Map<String, Object> result = new HashMap();
            result.put("status", "SUCCESS");
            return result;
        }catch(IllegalArgumentException e){
            throw new IllegalArgumentException("Cannot save order as entity is empty.");
        }

    }
}
