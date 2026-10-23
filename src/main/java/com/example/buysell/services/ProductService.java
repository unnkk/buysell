package com.example.buysell.services;

import com.example.buysell.entities.Image;
import com.example.buysell.entities.Product;
import com.example.buysell.entities.User;
import com.example.buysell.repositories.ProductRepository;
import com.example.buysell.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    public List<Product> getProductList(String title) {
        if(title != null){
            return productRepository.findByTitle(title);
        }
        return productRepository.findAll();
    }

    public void saveProduct(Principal principal, Product product, List<MultipartFile> files) throws IOException {
        product.setUser(getUserByPrincipal(principal));
        {
            int it = 0;
            for (MultipartFile file : files) {
                if(file.getSize() != 0) {
                    Image image = toImageEntity(file);
                    if (it == 0) {
                        image.setIsPreviewImage(Boolean.TRUE);
                    }
                    product.addImage(image);
                }
                ++it;
            }
        }
        log.info("Saving product.\n Title: {}\nSeller: {}", product.getTitle(), product.getUser().getEmail());
        Product preProd = productRepository.save(product);
        preProd.setPreviewImageId(preProd.getImages().getFirst().getId());
        productRepository.save(preProd);
    }

    public User getUserByPrincipal(Principal principal) {
        if(principal == null) return new User();
        return userRepository.findByEmail(principal.getName());
    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    public Product getProductById(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    private Image toImageEntity(MultipartFile file) throws IOException {
        return Image.builder()
                .name(file.getName())
                .originalFileName(file.getOriginalFilename())
                .contentType(file.getContentType())
                .size(file.getSize())
                .bytes(file.getBytes())
                .build();
    }
}
