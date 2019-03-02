package com.paro.domain.repository;

import com.paro.domain.Order;

public interface OrderRepository {
    long saveOrder(Order order);
}
