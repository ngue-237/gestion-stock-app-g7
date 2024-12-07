package com.logonedigital.gestion_stock_g7.mapper;

import com.logonedigital.gestion_stock_g7.dto.customer.CustomerReqDTO;
import com.logonedigital.gestion_stock_g7.dto.customer.CustomerResDTO;
import com.logonedigital.gestion_stock_g7.dto.customer.LocationReqDTO;
import com.logonedigital.gestion_stock_g7.entities.Customer;
import com.logonedigital.gestion_stock_g7.entities.Location;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.context.annotation.Configuration;

@Mapper(componentModel = "spring")
@Configuration
public interface CustomerMapper {
    Customer fromCustomerReqDTO(CustomerReqDTO customerReqDTO);
    Location fromLocationReqDTO(LocationReqDTO locationReqDTO);
    @Mapping(source = "location", target = "locationResDTO")
    CustomerResDTO fromCustomer(Customer customer);
}
