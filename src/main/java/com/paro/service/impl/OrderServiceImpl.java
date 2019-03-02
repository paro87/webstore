package com.paro.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.paro.domain.Order;
import com.paro.domain.repository.OrderRepository;
import com.paro.service.OrderService;
@Service
public class OrderServiceImpl implements OrderService{
    @Autowired
    private OrderRepository orderRepository;
    @Override
    public Long saveOrder(Order order) {
        return orderRepository.saveOrder(order);
    }
}
