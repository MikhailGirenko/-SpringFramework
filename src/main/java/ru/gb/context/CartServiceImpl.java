package ru.gb.context;
import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.stereotype.Service;


@Service
public class CartServiceImpl implements CartService {

    private final ProductRepository productRepository;

    public CartServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Lookup
    @Override
    public Cart getNewCart() {
        return null;
    }

    @Override
    public void addProduct(Cart cart, Product product, Integer quantity) {
        cart.addProduct(product, quantity);
    }

    @Override
    public void delProduct(Cart cart, Product product, Integer quantity) {
        cart.delProduct(product, quantity);
    }

    @Override
    public int getProductQuantity(Cart cart, Product product) {
        if (cart.getCartMap().containsKey(product)) {
            return cart.getCartMap().get(product);
        }
        return 0;
    }

    @Override
    public int getProductQuantity(Cart cart, Long prodId) {
        Product product = productRepository.findById(prodId);
        return this.getProductQuantity(cart, product);
    }
}