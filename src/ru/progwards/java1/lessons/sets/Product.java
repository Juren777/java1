package ru.progwards.java1.lessons.sets;

import java.util.Objects;
import java.util.Set;

public class Product {
    private String code;

    public Product(String code){
        this.code = code;
    }
    public String getCode(){
        return this.code;
    }

//    @Override
//    public String toString() {
//        return "Product{" +
//                "code='" + code + '\'' +
//                '}';
//    }
//
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (!(o instanceof Product)) return false;
//        Product product = (Product) o;
//        return getCode().equals(product.getCode());
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(getCode());
//    }
}