package com.logonedigital.gestion_stock_g7.mapper;

import com.logonedigital.gestion_stock_g7.dto.products.productdto.ProductReqDTO;
import com.logonedigital.gestion_stock_g7.dto.products.productdto.ProductToOrderDTO;
import com.logonedigital.gestion_stock_g7.entities.Product;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    Product fromProductToOrderDTO(ProductToOrderDTO productToOrderDTO);
}
