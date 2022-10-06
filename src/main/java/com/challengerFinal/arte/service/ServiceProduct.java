package com.challengerFinal.arte.service;

import com.challengerFinal.arte.dtos.CreateProductDto;
import com.challengerFinal.arte.dtos.ProductDto;
import com.challengerFinal.arte.dtos.UpdateProductDTO;
import com.challengerFinal.arte.model.Product;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ServiceProduct {
    Product saveArtworks(Product product);
    List <ProductDto> getArtworksAll();
    ProductDto getArtworkById(Long id);

    public ResponseEntity<Object> createProduct(Authentication authentication,
             CreateProductDto createProductDto);

    ResponseEntity<Object> updateProduct(@PathVariable("id") Long id, UpdateProductDTO updateProductDto,Authentication authentication);

    ResponseEntity<Object> deleteProduct(@PathVariable ("id") Long id);

    ResponseEntity<Object> uploadFile(@RequestParam("files") MultipartFile file, long idProduct, Authentication authentication);
}
