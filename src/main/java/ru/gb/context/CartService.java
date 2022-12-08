package ru.gb.context;


public interface CartService {
    Cart getNewCart();
    void addProduct(Cart cart, Product product, Integer quantity);

    void delProduct(Cart cart, Product product, Integer quantity);


    int getProductQuantity(Cart cart, Product product);
    int getProductQuantity(Cart cart, Long prodId);
}
