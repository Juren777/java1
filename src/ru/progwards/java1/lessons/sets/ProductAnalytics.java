package ru.progwards.java1.lessons.sets;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ProductAnalytics {

    private List<Shop> shops;
    private List<Product> products;


    public ProductAnalytics(List<Product> products, List<Shop> shops) {
        this.shops = shops;
        this.products = products;
    }

    public Set<Product> existInAll() { // товары из products, которые имеются во всех магазинах
        Set<Product> products = new HashSet<>(this.products);
        for (Shop s : this.shops
        ) {
            products.retainAll(s.getProducts());
        }
        return products;
    }

    public Set<Product> existAtListInOne() {// товары из products, которые имеются хотя бы в одном магазине
        Set<Product> products = new HashSet<>(this.products);
        Set<Product> result = new HashSet<>();
        for (Shop s : this.shops
        ) {
            result.addAll(s.getProducts());
        }
        result.retainAll(products);
        return result;
    }

    public Set<Product> notExistInShops() { // товары из products, которых нет ни в одном магазине
        Set<Product> products = new HashSet<>(this.products);
        for (Shop s : this.shops
        ) {
            products.removeAll(s.getProducts());
        }
        return products;
    }

    public Set<Product> existOnlyInOne() { // товары из products, которые есть только в одном магазине

        Set<Product> products = new HashSet<>(this.products);
    //    Set<Product> products = new HashSet<>(existAtListInOne());
    //    products.removeAll(existInAll());
        Set<Product> result = new HashSet<>();
        for (Product p : products
        ) {
            int count = 0;
            for (Shop s : this.shops
            ) {
                if (s.getProducts().contains(p)) {
                    count++;
                }
            }
            if (count == 1){
                result.add(p);
            }
        }

        return result;
    }

    public static void main(String[] args) {

//        List<Shop> shops = new ArrayList<>();
//
//        List<Product> products1 = new ArrayList<>();
//        products1.add(new Product("Vodka"));
//        products1.add(new Product("Whisky"));
//        products1.add(new Product("Rum"));
//        products1.add(new Product("Brandy"));
//
//        List<Product> products2 = new ArrayList<>();
//        products2.add(new Product("Vodka"));
//        products2.add(new Product("Soap"));
//        products2.add(new Product("Shampoo"));
//        products2.add(new Product("Petrolatum"));
//
//        List<Product> products3 = new ArrayList<>();
//        products3.add(new Product("Soap"));
//        products3.add(new Product("Vodka"));
//
//        List<Product> products = new ArrayList<>();
//        products.add(new Product("Vodka"));
//        products.add(new Product("Birch tar"));
//        products.add(new Product("Soap"));
//        products.add(new Product("Shampoo"));
//        products.add(new Product("Rum"));
//        products.add(new Product("Brandy"));
//
//        shops.add(new Shop(products1));
//        shops.add(new Shop(products2));
//        shops.add(new Shop(products3));
//
//        ProductAnalytics productAnalytics = new ProductAnalytics(products, shops);
//
//        System.out.println(productAnalytics.existOnlyInOne());
    }
}
