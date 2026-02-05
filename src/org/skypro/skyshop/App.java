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

import java.util.List;

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
        SearchEngine searchEngine = new SearchEngine();
//Добавление продуктов в корзину:
        basket1.add(bicycle);
        basket1.add(skates);
        basket1.add(ball);
        basket1.add(barbell);
        System.out.println("\nПечать содержимого корзины с несколькими товарами:");
        basket1.printBasket();
        basket2.add(ball);
        basket2.add(barbell);
        basket2.add(racket);
        basket2.add(skates);
        basket2.add(dumbbell);
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
        List<Searchable> results1 = searchEngine.search("ВЕЛОсипед");
        System.out.println("\nПоиск 'ВЕЛОсипед':");
        System.out.println(results1);
        List<Searchable> results2 = searchEngine.search("Штанга");
        System.out.println("\nПоиск 'Штанга':");
        System.out.println(results2);
        List<Searchable> results3 = searchEngine.search("Мяч");
        System.out.println("\nПоиск 'Мяч':");
        System.out.println(results3);
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
        System.out.println("\nУдаление продукта из корзины");
        List<Product> removedProduct = basket1.removeProductByName("Коньки");
        if (removedProduct.isEmpty()) {
            System.out.println("Список удаленных товаров пуст");
        } else {
            for (Product product : removedProduct) {
                System.out.println("Удален товар из корзины: " + product);
            }
        }
        System.out.println("\nСодержимое корзины после удаления");
        basket1.printBasket();
        System.out.println("\nУдаление несуществующего продукта");
        List<Product> removedProduct2 = basket1.removeProductByName("Коньки");
        if (removedProduct2.isEmpty()) {
            System.out.println("Список удаленных товаров пуст");
        } else {
            for (Product product : removedProduct2) {
                System.out.println("Удален товар из корзины: " + product);
            }
        }
        System.out.println("\nСодержимое корзины");
        basket1.printBasket();
        System.out.println("\nПоиск по запросу 'ВелосИпед'");
        List<Searchable> results4 = searchEngine.search("ВелосИпед");
        System.out.println(results4);
    }
}