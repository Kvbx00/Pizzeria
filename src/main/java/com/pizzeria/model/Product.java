package com.pizzeria.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotNull(message = "Nazwa nie może być pusta")
    @Column(name = "product_name")
    private String product_name;
    @NotNull(message = "Cena nie może być pusta")
    @Column(name = "product_price")
    private String product_price;
    @NotNull(message = "Opis nie może być pusty")
    @Column(name = "product_description")
    private String product_description;
    @NotNull(message = "Kategoria nie może być pusta")
    @Column(name = "product_category")
    private String product_category;
    @NotNull(message = "Zdjęcie nie może być puste")
    @Column(name = "image")
    private String image;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public String getProduct_price() {
        return product_price;
    }

    public void setProduct_price(String product_price) {
        this.product_price = product_price;
    }

    public String getProduct_description() {
        return product_description;
    }

    public void setProduct_description(String product_description) {
        this.product_description = product_description;
    }

    public String getProduct_category() {
        return product_category;
    }

    public void setProduct_category(String product_category) {
        this.product_category = product_category;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
