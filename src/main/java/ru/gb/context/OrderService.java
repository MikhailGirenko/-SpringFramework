package ru.gb.context;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;


@Component
public class OrderService {

    private  ProductService productService;
    private  CartServiceImpl cartService;

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }
    @Autowired
    public void setCartService(CartServiceImpl cartService) {
        this.cartService = cartService;
    }
    private void errorCommand() {
        System.out.println("Неверная команда!");
    }
    private static void listCommand(){
        System.out.println("Команды для управления: ");
        System.out.println("Распечатать список продуктов: list");
        System.out.println("Добавить продукт: add [N продукта] [количество]");
        System.out.println("Удалить продукт: del [N продукта] [количество]");
        System.out.println("Удалить корзину и создать новую: new");
        System.out.println("Завершить: exit");

    }
    private static void spaseBetweenCommands(){
        System.out.println(" ");
    }
    private static void printList(List<?> list) {
        System.out.println("Список продуктов:");
        for (Object el : list) {
            System.out.println(el.toString());
        }
    }
@PostConstruct
    public void cartInteract() throws IOException {
        Cart cart = cartService.getNewCart();
        System.out.println("Открываем корзину");
        spaseBetweenCommands();
        listCommand();

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        boolean exit = false;
        while(!exit) {

            System.out.print("Введите команду: ");

            Long prodId;
            int quantity;

            String str = in.readLine();
            if (!str.isEmpty()) {
                String[] parts = str.split("\\s");
                String command = parts[0];

                if (command.equalsIgnoreCase("exit")) {
                    exit = true;
                    System.out.println("Exit");
                } else if (command.equalsIgnoreCase("/?")) {
                    listCommand();
                } else if (command.equals("list")) {
                    printList(productService.getProductList());
                } else if (command.equalsIgnoreCase("new")) {
                    cart = cartService.getNewCart();
                    System.out.println("Создана новая корзина!");
                } else if (parts.length == 3) {
                    try {
                        prodId = Long.valueOf(parts[1]);
                        quantity = Integer.parseInt(parts[2]);
                    } catch (NumberFormatException e) {
                        errorCommand();
                        continue;
                    }

                    if (command.equalsIgnoreCase("add")) {
                        // добавить продукт
                        Product product = productService.getProductById(prodId);
                        if (product != null) {
                            cartService.addProduct(cart, productService.getProductById(prodId), quantity);
                            System.out.println("В корзину добавлен товар: " + productService.getProductById(prodId) + " - " + quantity + " шт.");
                        } else {
                            System.out.println("Такого товара нет в списке.");
                        }
                    } else if (command.equalsIgnoreCase("del")) {
                        // удалить продукт
                        if (cartService.getProductQuantity(cart, prodId) > 0) {
                            cartService.delProduct(cart, productService.getProductById(prodId), quantity);
                            System.out.println("Из корзины удален товар: " + productService.getProductById(prodId) + " - " + quantity + " шт.");
                        } else {
                            System.out.println("Такого продукта нет в корзине.");
                        }
                    }
                } else {
                    errorCommand();
                }
            }
        }
    }
}
