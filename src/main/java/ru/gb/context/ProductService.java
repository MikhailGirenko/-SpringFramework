package ru.gb.context;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private ProductRepository productRepository;

    @Autowired
    public void setProductRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product getProductById(Long id) {
        return productRepository.findById(id);
    }

    public List<Product> getProductList() {
        return productRepository.findAll();
    }

    public void saveOrUpdate(Product product) {
        productRepository.saveOrUpdate(product);
    }

    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }
}
