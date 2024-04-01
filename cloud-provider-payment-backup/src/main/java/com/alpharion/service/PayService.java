package com.alpharion.service;

import com.alpharion.entity.Pay;

import java.util.List;

/**
 * 订单支付Service
 *
 * @author XieYT
 * @since 2024/03/24 22:07
 */
public interface PayService {

    int add(Pay pay);

    int delete(Integer id);

    int update(Pay pay);

    Pay getById(Integer id);

    List<Pay> getAll();
}
