package com.logonedigital.gestion_stock_g7.dto.order;

import lombok.Data;

import java.util.List;

@Data
public class OrderReqDTO {
    private Integer customerId;
    private List<OrderItemDTO> orderItemDTOS;
}
