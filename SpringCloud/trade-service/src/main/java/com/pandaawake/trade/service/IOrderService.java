package com.pandaawake.trade.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.pandaawake.trade.domain.dto.OrderFormDTO;
import com.pandaawake.trade.domain.po.Order;

/**
 * <p>
 *  服务类
 * </p>
 */
public interface IOrderService extends IService<Order> {

    Long createOrder(OrderFormDTO orderFormDTO);

    void markOrderPaySuccess(Long orderId);
}
