package com.ordersystem.demo.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ordersystem.demo.model.Order;
import com.ordersystem.demo.repository.OrderRepository;
import com.ordersystem.demo.service.GoogleApiService;
import com.ordersystem.demo.service.OrderCreationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
@Service
public class OrderCreationServiceImpl implements OrderCreationService {

    private Double originX, originY, destinationX, destinationY;
    private List<String> originS, destinationS;
    private List<Double> origin, destination;
   
    @Autowired
    private OrderRepository orderRepository;
    @Override
    public Map<String, Object> createOrder(Map<String, List<String>> coordinate) {
        //read value and keys from json request. Save value into type of double
        try {
            for (Map.Entry<String, List<String>> entry : coordinate.entrySet()) {
                if (entry.getKey().equals("origin")) {
                    originS = entry.getValue();
                    origin = originS.stream().map(x ->
                            Double.valueOf(x)).collect(Collectors.toList());
                }
                if (entry.getKey().equals("destination")) {
                    destinationS = entry.getValue();
                    destination = destinationS.stream().map(x ->
                            Double.valueOf(x)).collect(Collectors.toList());
                }
                System.out.println("Value: " + entry.getValue());
            }
        }catch (NumberFormatException e){
            throw new NumberFormatException("No argument is found in query.");
        }

        //
        //int distance1 = GoogleApiService.getRealDistance(originS, destinationS);

        AssignCoordinate(origin,false);
        AssignCoordinate(destination,true);



        //stub distance for accessing googleapi
        int distance = CalculateDistance(originX, originY, destinationX, destinationY);

        Order order = new Order();
        try {
            order.setDistance(distance);
            order.setStatus("UNASSIGNED");
            orderRepository.save(order);
        }catch (IllegalArgumentException e){
            throw new IllegalArgumentException("Cannot save order.");
        }

        Map<String, Object> result = new HashMap();
        result.put("id", order.getId());
        result.put("distance", order.getDistance());
        result.put("status", order.getStatus());

        return result;

    }

    private void AssignCoordinate(List<Double> map, boolean type){
        if(type){
            destinationX = map.get(0);  destinationY = map.get(1);
        }else{
            originX = map.get(0);  originY = map.get(1);
        }
    }

    //stub for distance result got from google api
    private int CalculateDistance(Double originX, Double originY, Double destinationX, Double destinationY) {
        return (int) Math.round(Math.sqrt((destinationY - originY)*(destinationY - originY) + (destinationX - originX) * (destinationX - originX)));
    }


}
