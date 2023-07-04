package com.ordersystem.demo.service.impl;

import com.ordersystem.demo.model.Order;
import com.ordersystem.demo.repository.OrderRepository;
import com.ordersystem.demo.service.OrderEnquiryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderEnquiryServiceImpl implements OrderEnquiryService {

    @Autowired
    private OrderRepository orderRepository;
    public List<Order> orderEnquiry(String page, String limit){
        page = page.replace(":","");
        limit = limit.replace(":","");
        try {
            //check: 1. no page or limit is smaller than 1  2. parameter is fetchable
            if (Integer.parseInt(page) < 1 || Integer.parseInt(limit) < 1) {
                throw new IndexOutOfBoundsException("Page or limit is smaller than 1");
            }
            //put query result as page
            Page<Order> allProductsSortedByIDPage = orderRepository.findAll(PageRequest.of(Integer.parseInt(page) - 1, Integer.parseInt(limit)));
            List<Order> allProductsSortedByID = allProductsSortedByIDPage.getContent();
            return allProductsSortedByID;
        }catch (NumberFormatException e){
            throw new NumberFormatException("No integer can be parsed from argument");

        }

    }
}
