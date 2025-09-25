package com.clickcart.app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.clickcart.app.dto.ProductDTO;
import com.clickcart.app.entity.Product;
import com.clickcart.app.repository.ProductRepository;

@Service
public class ProductService {
     @Autowired
    private ProductRepository productRepository;

    public Product addProduct(ProductDTO productDTO){
        Product product = new Product();
        product.setName(productDTO.getName());
        product.setDescription(productDTO.getDescription());
        product.setPrice(productDTO.getPrice());
        product.setStock(productDTO.getStock());
        product.setImageUrl(productDTO.getImageUrl());
        return productRepository.save(product);
    }

    public List<Product> getAllProducts(){
        return productRepository.findAll();
    }

    public List<Product> getProductsByCategory(String category){
        return productRepository.findByCategory(category);
    }
    public List<Product> searchProducts(String keyword){
        return productRepository.findByNameContainingIgnoreCase(keyword);
    }

    public Product updateProduct(Long id, ProductDTO productDTO) throws Exception{
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new Exception("Product not found with id: " + id));
        product.setName(productDTO.getName());
        product.setDescription(productDTO.getDescription());
        product.setPrice(productDTO.getPrice());
        product.setStock(productDTO.getStock());
        product.setImageUrl(productDTO.getImageUrl());
        return productRepository.save(product);
    }

    public void deleteProduct(Long id){
        productRepository.deleteById(id);
    }

    public Optional<Product> getProductById(Long id) {
        return productRepository.findById(id);
    }
}
