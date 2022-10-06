package com.challengerFinal.arte.service.implement;

import com.challengerFinal.arte.dtos.CreateProductDto;
import com.challengerFinal.arte.dtos.ProductDto;
import com.challengerFinal.arte.dtos.UpdateProductDTO;
import com.challengerFinal.arte.model.Client;
import com.challengerFinal.arte.model.Product;
import com.challengerFinal.arte.repositories.ClientRepository;
import com.challengerFinal.arte.repositories.ProductRepository;
import com.challengerFinal.arte.service.FileService;
import com.challengerFinal.arte.service.ServiceProduct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductImplement implements ServiceProduct {
    @Autowired
    ProductRepository productRepository;
    @Autowired
    ClientRepository clientRepository;

    @Autowired
    private FileService fileService;

    @Override
    public Product saveArtworks(Product product) {
        return productRepository.save(product);
    }
    @Override
    public List<ProductDto> getArtworksAll() {
        return productRepository.findAll().stream().map(ProductDto::new).collect(Collectors.toList());
    }
    @Override
    public ProductDto getArtworkById(Long id) {
        return productRepository.findById(id).map(ProductDto::new).orElse(null);
    }

    @Override
    public ResponseEntity<Object> createProduct(Authentication authentication,
                                                CreateProductDto createProductDto) {

        Client client = clientRepository.findByEmail(authentication.getName());

        if (
                createProductDto.getName().isEmpty()
                || createProductDto.getCategory().isEmpty()
                || createProductDto.getDimensionsList().isEmpty()
                || createProductDto.getDescription().isEmpty()
            ) {
            return new  ResponseEntity<>(HttpStatus.FORBIDDEN);
        }

        String image = createProductDto.getImage();
        if (image == null) {
            image = "http://localhost:8080/images/product/default_image.jpg";
        }

        Product newProduct =new Product (
                createProductDto.getName(),
                createProductDto.getDescription(),
                createProductDto.getCategory(),
                createProductDto.getPrice(),
                true,
                LocalDate.now(),
                createProductDto.getUnits(),
                createProductDto.getDimensionsList(),
                image,
                client
                );
        productRepository.save(newProduct);
        return new ResponseEntity<>("Created a new work of art"+newProduct,HttpStatus.CREATED);
    }
//No terminado.
    @Override
    public ResponseEntity<Object> updateProduct(Long id, UpdateProductDTO updateProductDto,Authentication authentication) {
        Client client = clientRepository.findByEmail(authentication.getName());
        Optional<Product> updateProduct = productRepository.findById(id);
        //Si el product no existe
        if (updateProduct == null) {
            return new ResponseEntity<>("Product not updated",HttpStatus.FORBIDDEN);
        }
        updateProduct.get().setName(updateProductDto.getName());
        updateProduct.get().setCategory(updateProductDto.getCategory());
        updateProduct.get().setDate(LocalDate.now());
        updateProduct.get().setDescription(updateProductDto.getDescription());
        updateProduct.get().setImage(updateProductDto.getImage());
        updateProduct.get().setPrice(updateProductDto.getPrice());
        updateProduct.get().setStatus(updateProductDto.getStatus());
        updateProduct.get().setUnits(updateProductDto.getUnits());
        updateProduct.get().setClient(client);

        productRepository.save(updateProduct.get());
        return new ResponseEntity<>("Producto actualizado", HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Object> deleteProduct(Long id) {
        return null;
    }

    @Override
    public ResponseEntity<Object> uploadFile(@RequestParam("files") MultipartFile file, long idProduct, Authentication authentication){
        try {
            if (authentication == null) {
                return new ResponseEntity<Object>("You're not logged in",HttpStatus.FORBIDDEN);
            }
            Client authClient = clientRepository.findByEmail(authentication.getName());
            Product product = productRepository.findById(idProduct).orElse(null);

            if (product == null) {
                return new ResponseEntity<Object>("Product not found",HttpStatus.FORBIDDEN);
            }

            if (!authClient.getProducts().contains(product)) {
                return new ResponseEntity<Object>("You're not the owner of the product",HttpStatus.FORBIDDEN);
            }

            String fileUrl = fileService.updateFile(file, product.getId().toString(), "product");
            product.setImage(fileUrl);
            clientRepository.save(authClient);
            return new ResponseEntity<Object>(HttpStatus.CREATED);
        } catch (Exception e) {
            System.out.println(e);
            return new ResponseEntity<Object>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
