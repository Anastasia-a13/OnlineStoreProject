package org.skypro.skyshop.search;

import java.util.*;
import java.util.stream.Collectors;

public class SearchEngine {
    private final Set<Searchable> items = new HashSet<>();

    public void add(Searchable item) {
        items.add(item);
    }

    public TreeSet<Searchable> search(String query) {
        if (query == null || query.isBlank()) {
            return new TreeSet<>(comparatorByLengthThenName());
        }
        return items.stream()
                .filter(item -> item.getSearchTerm().toLowerCase().contains(query.toLowerCase()))
                .collect(Collectors.toCollection(() -> new TreeSet<>(comparatorByLengthThenName())));
    }

    private Comparator<Searchable> comparatorByLengthThenName() {
        return (item1, item2) -> {
            int length1 = item1.getName().length();
            int length2 = item2.getName().length();
            if (length1 != length2) {
                return Integer.compare(length2, length1);
            }
            return item1.getName().compareTo(item2.getName());
        };
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