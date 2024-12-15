package com.logonedigital.gestion_stock_g7.services.order;

import com.logonedigital.gestion_stock_g7.dto.order.OrderDTO;
import com.logonedigital.gestion_stock_g7.dto.order.OrderItemDTO;
import com.logonedigital.gestion_stock_g7.dto.order.OrderReqDTO;
import com.logonedigital.gestion_stock_g7.dto.order.OrderResDTO;
import com.logonedigital.gestion_stock_g7.entities.Customer;
import com.logonedigital.gestion_stock_g7.entities.Order;
import com.logonedigital.gestion_stock_g7.entities.OrderItem;
import com.logonedigital.gestion_stock_g7.entities.Product;
import com.logonedigital.gestion_stock_g7.exception.ResourceNotFoundException;
import com.logonedigital.gestion_stock_g7.mapper.OrderMapper;
import com.logonedigital.gestion_stock_g7.mapper.ProductMapper;
import com.logonedigital.gestion_stock_g7.repositories.CustomerRepo;
import com.logonedigital.gestion_stock_g7.repositories.OrderItemRepo;
import com.logonedigital.gestion_stock_g7.repositories.OrderRepo;
import com.logonedigital.gestion_stock_g7.repositories.ProductRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.*;


@Slf4j
@Service
public class OrderServiceImpl implements OrderService{
    private final OrderRepo orderRepo;
    private final CustomerRepo customerRepo;
    private final OrderItemRepo orderItemRepo;
    private final ProductRepo productRepo;
    private final OrderMapper orderMapper;

    public OrderServiceImpl(OrderRepo orderRepo, CustomerRepo customerRepo, OrderItemRepo orderItemRepo, ProductRepo productRepo, OrderMapper orderMapper) {
        this.orderRepo = orderRepo;
        this.customerRepo = customerRepo;
        this.orderItemRepo = orderItemRepo;
        this.productRepo = productRepo;
        this.orderMapper = orderMapper;
    }

    private OrderItem saveOrderItem(OrderItemDTO orderItemDTO){
        Product product = this.productRepo
                .findById(orderItemDTO.getProductId())
                .orElseThrow(()->new ResourceNotFoundException("Product Not found !"));
        OrderItem orderItem = new OrderItem();
        orderItem.setQuantity(orderItem.getQuantity());
        orderItem.setProduct(product);


        return this.orderItemRepo.save(orderItem);

    }
    @Override
    public OrderDTO addOrder(OrderReqDTO orderReqDTO) {
        Order order = new Order();

        //Recherche d'un customer en fonction du customerId en provenant du orderReqDTO
        Customer customer = this.customerRepo.findById(orderReqDTO.getCustomerId())
               .orElseThrow(()-> new ResourceNotFoundException("Customer not found !"));

        order.setCustomer(customer);
        order.setCreatedAt(new Date());
        order.setReference(UUID.randomUUID().toString());
        order.setStatus(true);
        List<OrderItem> orderItems = orderReqDTO.getOrderItemDTOS()
                .stream()
                .distinct()
                .map(orderItem -> this.saveOrderItem(orderItem))
                .toList();

        order.setOrderItems(orderItems);

        return this.orderMapper.fromOder(this.orderRepo.save(order));
    }


    @Override
    public OrderResDTO getPaginateOrder(int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by(Sort.Direction.DESC, "createdAt"));
        Page<Order> orderPage = this.orderRepo.findAll(pageable);
        List<OrderDTO> orderDTOS = orderPage
                .stream()
                .map(order -> {
                    OrderDTO orderDTO =this.orderMapper.fromOder(order);
                    log.info("OrderItems : {}", order.getOrderItems());
                    return this.orderMapper.fromOder(order);
                })
                .toList();
        OrderResDTO orderResDTO = new OrderResDTO();
        orderResDTO.setContent(orderDTOS);
        orderResDTO.setPageNumber(orderPage.getNumber());
        orderResDTO.setPageSize(orderPage.getSize());
        orderResDTO.setTotalElements(orderPage.getTotalElements());
        orderResDTO.setTotalPages(orderPage.getTotalPages());
        orderResDTO.setLast(orderPage.isLast());

        return orderResDTO;
    }

    @Override
    public List<Order> getOrderWithoutPagination() {
        List<List<OrderItem>> orderItems = this.orderRepo.findAll()
                .stream()
                .map(order -> order.getOrderItems())
                .toList();
        log.info("OrderItem :{}", orderItems);
        return this.orderRepo.findAll();
    }
}
