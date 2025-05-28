import java.util.LinkedList;

public class HashMap<K, V> {
    private static class Entry<K, V> {
        K key;
        V value;

        Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    private final LinkedList<Entry<K, V>>[] map;
    private final int size;

    @SuppressWarnings("unchecked")
    public HashMap(int size) {
        this.size = size;
        map = new LinkedList[size];
        for (int i = 0; i < size; i++) {
            map[i] = new LinkedList<>();
        }
    }

    private int hash(K key) {
        return (key.hashCode() % size + size) % size;
    }

    public void put(K key, V value) {
        int index = hash(key);
        for (Entry<K, V> entry : map[index]) {
            if (entry.key.equals(key)) {
                entry.value = value;
                return;
            }
        }
        map[index].add(new Entry<>(key, value));
    }

    public V get(K key) {
        int index = hash(key);
        for (Entry<K, V> entry : map[index]) {
            if (entry.key.equals(key)) {
                return entry.value;
            }
        }
        return null;
    }

    public boolean remove(K key) {
        int index = hash(key);
        for (Entry<K, V> entry : map[index]) {
            if (entry.key.equals(key)) {
                map[index].remove(entry);
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        HashMap<String, Integer> map = new HashMap<>(10);
        map.put("один", 1);
        map.put("два", 2);

        System.out.println("Значение для 'один': " + map.get("один"));
        map.remove("один");
        System.out.println("Значение для 'один' после удаления: " + map.get("один"));

        HashMap<Integer, String> intKeyMap = new HashMap<>(10);
        intKeyMap.put(42, "Значение для ключа 42");
        System.out.println("Значение для ключа 42: " + intKeyMap.get(42));
    }
}
