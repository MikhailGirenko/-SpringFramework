package ru.gb.context;

import org.springframework.stereotype.Component;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

@Component
public class ProductRepository {

    private final Map<Long, Product> productMap = new HashMap<>();

    {

        productMap.put(1L, new Product(1L, "Bread"));
        productMap.put(2L, new Product(2L, "Milk"));
        productMap.put(3L, new Product(3L, "Beer"));
        productMap.put(4L, new Product(4L, "Cheese"));
        productMap.put(5L, new Product(5L, "Groats"));
    }

    public List<Product> findAll() {
        return new ArrayList<>(productMap.values());
    }


    public void saveOrUpdate(Product product) {
        productMap.put(product.getId(), product);
    }


    public Product findById(Long id) {
        return productMap.get(id); }


    public void deleteById(Long id) {
        productMap.remove(id);
    }
}

