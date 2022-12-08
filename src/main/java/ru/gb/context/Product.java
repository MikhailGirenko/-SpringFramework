package ru.gb.context;

public class Product {
    private Long id;
    private String title;

    public Long getId() {
        return id;
    }

    public Product(long id, String title) {
        this.id = id;
        this.title = title;
    }

    @Override
    public String toString() {
        return id + " " + title;
    }
}
