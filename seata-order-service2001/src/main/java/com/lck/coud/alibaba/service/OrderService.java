package com.lck.coud.alibaba.service;

import com.lck.coud.alibaba.domain.Order;

public interface OrderService {

    /**
     * 创建订单
     */
    void create(Order order);

}
