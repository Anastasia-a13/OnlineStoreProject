package org.skypro.skyshop.basket;

import org.skypro.skyshop.product.Product;

public class ProductBasket {
    private final Product[] products = new Product[5];
    private int count = 0;

    public void addProduct(Product product) {
        if (count >= 5) {
            System.out.println("Невозможно добавить продукт");
            return;
        }
        products[count] = product;
        count++;
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
        if (count == 0) {
            System.out.println("В корзине пусто!");
            return;
        }
        for (Product product : products) {
            if (product != null) {
                System.out.println(product);
            }
        }
        System.out.println("Итого: " + getTotalPrice());
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
        for (int i = 0; i < count; i++) {
            products[i] = null;
        }
        count = 0;
    }
}
