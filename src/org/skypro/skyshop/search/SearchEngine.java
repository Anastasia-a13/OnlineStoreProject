package org.skypro.skyshop.search;

import java.util.ArrayList;
import java.util.List;

public class SearchEngine {
    private final List<Searchable> items = new ArrayList<>();

    public void add(Searchable item) {
        items.add(item);
    }

    public List<Searchable> search(String query) {
        List<Searchable> results = new ArrayList<>();
        for (Searchable item : items) {
            if (item.getName().toLowerCase().contains(query.toLowerCase())) {
                results.add(item);
            }
        }
        return results;
    }

    public Searchable findBestMatch(String search) throws BestResultNotFound {
        if (search == null || search.isBlank()) {
            throw new BestResultNotFound("Поисковый запрос не может быть пустым");
        }
        Searchable bestMatch = null;
        int maxCount = 0;
        for (Searchable item : items) {
            if (item != null) {
                int count = countOccurrences(item.getSearchTerm().toLowerCase(), search.toLowerCase());
                if (count > maxCount) {
                    maxCount = count;
                    bestMatch = item;
                }
            }
        }

        if (bestMatch == null) {
            throw new BestResultNotFound("Не найдено совпадений для запроса: '" + search + "'");
        }

        return bestMatch;
    }

    private int countOccurrences(String text, String substring) {
        if (text == null || substring == null || substring.isBlank()) {
            return 0;
        }
        int count = 0;
        int index = 0;
        int substringLength = substring.length();
        while ((index = text.indexOf(substring, index)) != -1) {
            count++;
            index += substringLength;
        }

        return count;
    }
}