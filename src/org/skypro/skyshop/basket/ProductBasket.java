package org.skypro.skyshop.basket;

import org.skypro.skyshop.product.Product;

import java.util.*;

public class ProductBasket {
    private final Map<String, List<Product>> products = new HashMap<>();

    public void add(Product product) {
        products.computeIfAbsent(product.getName(), k -> new ArrayList<>()).add(product);
    }

    public int getTotalPrice() {
        return products.values().stream().flatMap(Collection::stream).mapToInt(Product::getPrice).sum();
    }

    private long getSpecialCount() {
        return products.values().stream().flatMap(Collection::stream).filter(Product::isSpecial).count();
    }

    public void printBasket() {
        long specialCount = getSpecialCount();
        if (products.isEmpty()) {
            System.out.println("В корзине пусто!");
            return;
        }
        products.values().stream().flatMap(Collection::stream).forEach(product ->
                System.out.println(product.toString()));
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