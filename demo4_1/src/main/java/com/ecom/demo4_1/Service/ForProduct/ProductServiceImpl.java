package com.ecom.demo4_1.Service.ForProduct;

import com.ecom.demo4_1.Dto.ProductDto;
import com.ecom.demo4_1.Model.Product;
import com.ecom.demo4_1.Repo.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService{
    @Autowired
    private ProductRepository productRepository;

    @Override
    public Product addProduct(ProductDto productDto){
        Product product=new Product();
        product.setProdname(productDto.getProdname());
        product.setPrice(productDto.getPrice());
        return productRepository.save(product);
    }

    @Override
    public List<Product> getAllProducts(){
        return productRepository.findAll();
    }

    @Override
    public Product updateProduct(Long productId, ProductDto updatedProductDto) {
        // Retrieve the existing product from the database
        Product existingProduct = productRepository.findById(productId).orElse(null);

        if (existingProduct == null) {
            // Handle the case where the product is not found
            // You may throw an exception or return an appropriate response
            return null;
        }

        // Update the existing product with the new information
        existingProduct.setProdname(updatedProductDto.getProdname());
        existingProduct.setPrice(updatedProductDto.getPrice());
        // Add more fields to update as needed

        // Save the updated product
        return productRepository.save(existingProduct);
    }

}
