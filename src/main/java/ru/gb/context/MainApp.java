package ru.gb.context;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.lang.model.AnnotatedConstruct;

public class MainApp {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext("ru.gb.context");


        context.close();
    }
}
