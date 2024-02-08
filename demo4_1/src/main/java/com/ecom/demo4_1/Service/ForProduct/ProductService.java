package com.ecom.demo4_1.Service.ForProduct;

import com.ecom.demo4_1.Dto.ProductDto;
import com.ecom.demo4_1.Model.Product;

import java.util.List;

public interface ProductService {
    Product addProduct(ProductDto productDto);
    List<Product> getAllProducts();
    Product updateProduct(Long prodId, ProductDto updatedProductDto);
}
