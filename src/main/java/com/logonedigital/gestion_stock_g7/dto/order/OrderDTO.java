package com.logonedigital.gestion_stock_g7.dto.order;

import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
public class OrderDTO {
    private Integer orderId;
    private String reference;
    private Date createdAt;
    private Date updatedAt;
    List<OrderItemDTO> orderItemDTOS = new ArrayList<>();
}
