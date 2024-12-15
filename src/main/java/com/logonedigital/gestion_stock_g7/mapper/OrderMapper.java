package com.logonedigital.gestion_stock_g7.mapper;

import com.logonedigital.gestion_stock_g7.dto.order.OrderDTO;
import com.logonedigital.gestion_stock_g7.entities.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface OrderMapper {
    @Mapping(source = "orderItems", target = "orderItemDTOS")
    OrderDTO fromOder(Order order);
}
