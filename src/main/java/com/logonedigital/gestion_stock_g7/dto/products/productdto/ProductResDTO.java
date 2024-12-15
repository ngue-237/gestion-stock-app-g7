package com.logonedigital.gestion_stock_g7.dto.products.productdto;


import lombok.Data;

import java.util.List;


@Data
public class ProductResDTO {
   private List<ProductDTO> content;
   private int pageNumber;
   private int pageSize;
   private long totalElements;
   private int totalPages;
   private boolean last;
}
