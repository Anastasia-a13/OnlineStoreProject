package org.skypro.skyshop.basket;

import org.skypro.skyshop.product.Product;

import java.util.*;

public class ProductBasket {
    private final Map<String, List<Product>> products = new HashMap<>();

    public void add(Product product) {
        products.computeIfAbsent(product.getName(), k -> new ArrayList<>()).add(product);
    }

    public int getTotalPrice() {
        int total = 0;
        for (List<Product> productList : products.values()) {
            for (Product product : productList) {
                if (product != null) {
                    total += product.getPrice();
                }
            }
        }
        return total;
    }

    public void printBasket() {
        int specialCount = 0;
        if (products.isEmpty()) {
            System.out.println("В корзине пусто!");
            return;
        }
        for (List<Product> productList : products.values()) {
            for (Product product : productList) {
                if (product != null) {
                    System.out.println(product);
                }
                if (product != null && product.isSpecial()) {
                    specialCount++;
                }
            }
        }
        System.out.println("Итого: " + getTotalPrice());
        System.out.println("Специальных товаров: " + specialCount);
    }

    public boolean containsProduct(String name) {
        return products.containsKey(name);
    }

    public void clearBasket() {
        products.clear();
    }

    public List<Product> removeProductByName(String name) {
        List<Product> removedProduct = products.remove(name);
        if (removedProduct != null) {
            return removedProduct;
        } else {
            return new ArrayList<>();
        }
    }
}