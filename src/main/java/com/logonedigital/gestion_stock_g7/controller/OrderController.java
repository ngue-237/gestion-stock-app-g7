package com.logonedigital.gestion_stock_g7.controller;

import com.logonedigital.gestion_stock_g7.dto.order.OrderDTO;
import com.logonedigital.gestion_stock_g7.dto.order.OrderReqDTO;
import com.logonedigital.gestion_stock_g7.dto.order.OrderResDTO;
import com.logonedigital.gestion_stock_g7.entities.Order;
import com.logonedigital.gestion_stock_g7.services.order.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping(path = "/add")
    public ResponseEntity<OrderDTO> addOrder(@RequestBody OrderReqDTO orderReqDTO){

        return ResponseEntity
                .status(201)
                .body(this.orderService.addOrder(orderReqDTO));
    }

    @GetMapping(path = "/get_all_with_pagination")
    public ResponseEntity<OrderResDTO> getOrderWithPagination(
            @RequestParam(value = "pageNumber", defaultValue = "0", required = false) int pageNumber,
            @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize
    ){
        return ResponseEntity
                .status(200)
                .body(this.orderService.getPaginateOrder(pageNumber, pageSize));


    }

    @GetMapping(path = "/get_all_without_pagination")
    public ResponseEntity<List<Order>> getOrderWithoutPagination(){
        return ResponseEntity
                .status(200)
                .body(this.orderService.getOrderWithoutPagination());


    }
}
