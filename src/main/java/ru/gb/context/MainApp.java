package ru.gb.context;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.lang.model.AnnotatedConstruct;
import java.io.IOException;

//1. Есть класс Product (id, название, цена). Товары хранятся в бине ProductRepository, в виде
 //       List<Product>, при старте в него нужно добавить 5 любых товаров.
  //      2. ProductRepository позволяет получить весь список или один товар по id. Создаем бин Cart, в
   //     который можно добавлять и удалять товары по id.
   //     3. Написать консольное приложение, позволяющее управлять корзиной.
    //    4. При каждом запросе корзины из контекста, должна создаваться новая корзина.



public class MainApp {
    public static void main(String[] args) throws IOException {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext("ru.gb.context");
        OrderService orderService =context.getBean(OrderService.class);
        orderService.cartInteract();
        context.close();
    }
}
