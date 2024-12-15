package com.logonedigital.gestion_stock_g7.dto.order;

import lombok.Data;

@Data
public class OrderItemDTO {
    private Double quantity;
    private Integer productId;
}
