package org.skypro.skyshop;

import org.skypro.skyshop.article.Article;
import org.skypro.skyshop.basket.ProductBasket;
import org.skypro.skyshop.product.DiscountProduct;
import org.skypro.skyshop.product.FixPriceProduct;
import org.skypro.skyshop.product.Product;
import org.skypro.skyshop.product.SimpleProduct;
import org.skypro.skyshop.search.BestResultNotFound;
import org.skypro.skyshop.search.SearchEngine;
import org.skypro.skyshop.search.Searchable;

import java.util.Arrays;

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
        Article chooseBicycle = new Article("Как выбрать велосипед", "Перед покупкой обратите внимание на количество скоростей и качество материалов");
        Article ballTypes = new Article("Мячи: какие виды бывают", "В продаж можно встретить баскетбольные, теннисные, футбольные и много других видов мячей");
        Article discountBarbell = new Article("Скидка на штанги!", "Весь декабрь штанга будет продаваться со скидкой -15%");
        SearchEngine searchEngine = new SearchEngine(10);
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
//Добавление товаров и статей в поисковый массив:
        searchEngine.add(bicycle);
        searchEngine.add(skates);
        searchEngine.add(ball);
        searchEngine.add(dumbbell);
        searchEngine.add(barbell);
        searchEngine.add(racket);
        searchEngine.add(chooseBicycle);
        searchEngine.add(ballTypes);
        searchEngine.add(discountBarbell);
        System.out.println("\n" + chooseBicycle.getStringRepresentation());
        System.out.println("\nРезультаты поиска");
        Searchable[] results1 = searchEngine.search("ВЕЛОсипед");
        System.out.println("\nПоиск 'ВЕЛОсипед':");
        System.out.println(Arrays.toString(results1));
        Searchable[] results2 = searchEngine.search("Штанга");
        System.out.println("\nПоиск 'Штанга':");
        System.out.println(Arrays.toString(results2));
        Searchable[] results3 = searchEngine.search("Мяч");
        System.out.println("\nПоиск 'Мяч':");
        System.out.println(Arrays.toString(results3));
        //Демонстрация проверки данных:
        System.out.println("\nПроверка данных:");
        try {
            SimpleProduct cap = new SimpleProduct(" ", 1569);
        } catch (IllegalArgumentException e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
        try {
            SimpleProduct sneakers = new SimpleProduct("Кроссовки", 0);
        } catch (IllegalArgumentException e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
        try {
            DiscountProduct tennisBall = new DiscountProduct("Теннисный мяч", 989, 110);
        } catch (IllegalArgumentException e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
        System.out.println("\nПоиск лучшего совпадения");
        System.out.println("\nУспешный поиск");
        try {
            Searchable best1 = searchEngine.findBestMatch("РАкетка");
            System.out.println("Лучшее совпадение для 'Ракетка': " + best1.getStringRepresentation());
        } catch (BestResultNotFound e) {
            System.out.println("Ошибка поиска: " + e.getMessage());
        }
        System.out.println("\nПоиск без результата");
        try {
            Searchable best2 = searchEngine.findBestMatch("сноуборд");
            System.out.println("Лучшее совпадение для 'сноуборд': " + best2.getStringRepresentation());
        } catch (BestResultNotFound e) {
            System.out.println("Ошибка поиска: " + e.getMessage());
        }

        System.out.println("\nПустой запрос");
        try {
            searchEngine.findBestMatch("");
        } catch (BestResultNotFound e) {
            System.out.println("Ошибка поиска: " + e.getMessage());
        }
    }
}