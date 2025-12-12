package org.skypro.skyshop.search;

public class SearchEngine {
    private final Searchable[] items;
    private int count = 0;

    public SearchEngine(int capacity) {
        this.items = new Searchable[capacity];
    }

    public void add(Searchable item) {
        if (count < items.length) {
            items[count] = item;
            count++;
        } else {
            System.out.println("Невозможно добавить элемент: хранилище переполнено");
        }
    }

    public Searchable[] search(String query) {
        Searchable[] results = new Searchable[5];
        int resultCount = 0;

        for (int i = 0; i < count; i++) {
            Searchable item = items[i];
            if (item.getSearchTerm().toLowerCase().contains(query.toLowerCase())) {
                results[resultCount] = item;
                resultCount++;
                if (resultCount == 5) {
                    break;
                }
            }
        }
        return results;
    }
}