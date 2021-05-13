package ua.com.alevel.uniquename;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class UniqueName {

    // Algorithm difficulty: O(n)
    public static String firstUniqueName(List<String> names) {
        Map<String, Integer> namesCount = new LinkedHashMap<>();

        // Add all names to map
        for (String name : names) {
            if (namesCount.containsKey(name)) {
                namesCount.put(name, namesCount.get(name) + 1);
            } else {
                namesCount.put(name, 1);
            }
        }

        Set<String> keys = namesCount.keySet();

        // Find first unique name
        for (String key : keys) {
            if (namesCount.get(key) == 1) {
                return key;
            }
        }

        return null;
    }
}
