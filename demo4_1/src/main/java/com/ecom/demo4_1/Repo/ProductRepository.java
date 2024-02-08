package com.ecom.demo4_1.Repo;

import com.ecom.demo4_1.Dto.ProductDto;
import com.ecom.demo4_1.Model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product,Long> {
    List<Product> findByProdIdIn(List<Long> ids);

}
