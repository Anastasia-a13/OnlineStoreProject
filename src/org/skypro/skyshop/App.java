package org.skypro.skyshop;

import org.skypro.skyshop.basket.ProductBasket;
import org.skypro.skyshop.product.DiscountProduct;
import org.skypro.skyshop.product.FixPriceProduct;
import org.skypro.skyshop.product.Product;
import org.skypro.skyshop.product.SimpleProduct;

public class App {
    public static void main(String[] args) {
        DiscountProduct bicycle = new DiscountProduct("Велосипед", 25990, 30);
        SimpleProduct skates = new SimpleProduct("Коньки", 7990);
        FixPriceProduct ball = new FixPriceProduct("Мяч");
        Product dumbbell = new SimpleProduct("Гантель", 890);
        DiscountProduct barbell = new DiscountProduct("Штанга", 11990, 15);
        Product racket = new SimpleProduct("Ракетка", 3890);
        ProductBasket basket1 = new ProductBasket();
        ProductBasket basket2 = new ProductBasket();
//Добавление продуктов в корзину:
        basket1.addProduct(bicycle);
        basket1.addProduct(skates);
        basket1.addProduct(ball);
        System.out.println("\nПечать содержимого корзины с несколькими товарами:");
        basket1.printBasket();
        System.out.println("\nДобавление продукта в заполненную корзину, в которой нет свободного места:");
        basket2.addProduct(ball);
        basket2.addProduct(barbell);
        basket2.addProduct(racket);
        basket2.addProduct(skates);
        basket2.addProduct(dumbbell);
        basket2.addProduct(bicycle);
        System.out.println("\nПолучение стоимости корзины с несколькими товарами:");
        System.out.println(basket2.getTotalPrice());
        System.out.println("\nПоиск товара, который есть в корзине:");
        System.out.println("Есть ли в корзине коньки: " + basket1.containsProduct(skates.getName()));
        System.out.println("\nПоиск товара, которого нет в корзине:");
        System.out.println("Есть ли в корзине велосипед: " + basket2.containsProduct(bicycle.getName()));
        System.out.println("\nОчистка корзины:");
        basket2.clearBasket();
        System.out.println("\nПечать пустой корзины:");
        basket2.printBasket();
        System.out.println("\nПолучение стоимости пустой корзины:");
        System.out.println(basket2.getTotalPrice());
        System.out.println("\nПоиск товара по имени в пустой корзине:");
        System.out.println("Есть ли в корзине ракетка: " + basket2.containsProduct(racket.getName()));
    }
}