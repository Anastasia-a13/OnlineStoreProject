package org.skypro.skyshop.basket;

import org.skypro.skyshop.product.Product;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class ProductBasket {
    private final List<Product> products = new LinkedList<>();

    public void add(Product product) {
        products.add(product);
    }

    public int getTotalPrice() {
        int total = 0;
        for (Product product : products) {
            if (product != null) {
                total += product.getPrice();
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
        for (Product product : products) {
            if (product != null) {
                System.out.println(product);
            }
            if (product != null && product.isSpecial()) {
                specialCount++;
            }
        }
        System.out.println("Итого: " + getTotalPrice());
        System.out.println("Специальных товаров: " + specialCount);
    }

    public boolean containsProduct(String name) {
        for (Product product : products) {
            if (product != null && product.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }

    public void clearBasket() {
        products.clear();
    }

    public List<Product> removeProductByName(String name) {
        List<Product> removedProduct = new LinkedList<>();
        Iterator<Product> iterator = products.iterator();
        while (iterator.hasNext()) {
            Product product = iterator.next();
            if (product.getName().equals(name)) {
                iterator.remove();
                removedProduct.add(product);
            }
        }
        return removedProduct;
    }
}