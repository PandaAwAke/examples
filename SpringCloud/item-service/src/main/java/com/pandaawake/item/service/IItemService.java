package com.pandaawake.item.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.pandaawake.api.dto.ItemDTO;
import com.pandaawake.api.dto.OrderDetailDTO;
import com.pandaawake.item.domain.po.Item;

import java.util.Collection;
import java.util.List;

/**
 * <p>
 * 商品表 服务类
 * </p>
 */
public interface IItemService extends IService<Item> {

    void deductStock(List<OrderDetailDTO> items);

    List<ItemDTO> queryItemByIds(Collection<Long> ids);
}
