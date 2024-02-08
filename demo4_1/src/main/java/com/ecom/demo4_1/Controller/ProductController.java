package com.ecom.demo4_1.Controller;

import com.ecom.demo4_1.Dto.ProductDto;
import com.ecom.demo4_1.Model.Product;
import com.ecom.demo4_1.Service.ForProduct.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/product")
public class ProductController {
    @Autowired
    private ProductService productService;
    @PostMapping("/add")
    public Product addProduct(@RequestBody ProductDto productDto){
        return productService.addProduct(productDto);
    }

    @GetMapping("/all")
    public List<Product> getAllProducts() {

        return productService.getAllProducts();
    }

    @PutMapping("/update/{productId}")
    public Product updateProduct(@PathVariable Long productId, @RequestBody ProductDto updatedProductDto){
        return productService.updateProduct(productId,updatedProductDto);
    }

}
