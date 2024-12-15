package com.logonedigital.gestion_stock_g7.services.order;

import com.logonedigital.gestion_stock_g7.dto.order.OrderDTO;
import com.logonedigital.gestion_stock_g7.dto.order.OrderReqDTO;
import com.logonedigital.gestion_stock_g7.dto.order.OrderResDTO;
import com.logonedigital.gestion_stock_g7.entities.Order;

import java.util.List;

public interface OrderService {
    OrderDTO addOrder(OrderReqDTO orderReqDTO);
    OrderResDTO getPaginateOrder(int pageNumber, int pageSize);
    List<Order> getOrderWithoutPagination();
}
