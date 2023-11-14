package christmas.domain;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class OrderedItems {

    private final Map<String, Integer> items = new HashMap<>();

    public Map<String, Integer> getItemsMap() {
        return Collections.unmodifiableMap(items);
    }

    public void addItem(String item, int quantity) {
        items.merge(item, quantity, Integer::sum);
    }

}
