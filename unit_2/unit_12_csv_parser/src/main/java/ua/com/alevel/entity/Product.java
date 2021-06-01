package ua.com.alevel.entity;

import ua.com.alevel.annotation.Deserializable;
import ua.com.alevel.enumeration.Type;

public class Product {

    @Deserializable("name")
    private String name;

    @Deserializable("type")
    private Type type;

    @Deserializable("price")
    private Double price;

    @Deserializable("amount")
    private Integer amount;

    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", price=" + price +
                ", amount=" + amount +
                '}';
    }
}
