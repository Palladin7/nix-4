package ua.com.alevel.tasks;

import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class FindSortAndCountSymbolsInString {

    /*
     * Function takes string as input and counts each latin or cyrillic symbol occurrence
     * Each uppercase latter converted to corresponding lowercase and increases it's count
     * All found letters are displayed on console and returned as Map<Character, Integer>
     */
    public static Map<Character, Integer> findSortAndCountSymbols(String str) {
        String cyrillic = "абвгдеёжзийклмнопрстуфхцчшщъыьэюя";
        Map<Character, Integer> symbols = new TreeMap<>();

        for (int i = 0; i < str.length(); i++) {
            char symbol = Character.toLowerCase(str.charAt(i));
            // If symbol already in Map, increase count
            if (symbols.containsKey(symbol)) {
                int count = symbols.get(symbol);
                symbols.replace(symbol, count + 1);
            } else if (Character.isLetter(symbol) || cyrillic.contains(Character.toString(symbol)))
                // If symbol is latin or cyrillic, but not in Map, add it
                symbols.put(symbol, 1);
        }

        // Get all keys (symbols)
        Set<Character> keys = symbols.keySet();

        // Display symbols and their count
        for (char key : keys) {
            System.out.println(key + " - " + symbols.get(key));
        }

        return symbols;
    }
}
