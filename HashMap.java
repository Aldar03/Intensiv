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

    private LinkedList<Entry<K, V>>[] map;
    private int size;

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

    @SuppressWarnings("unchecked")
    public void resize(int newSize) {
        LinkedList<Entry<K, V>>[] oldMap = map;
        int oldSize = size;

        size = newSize;
        map = new LinkedList[newSize];
        for (int i = 0; i < newSize; i++) {
            map[i] = new LinkedList<>();
        }

        for (int i = 0; i < oldSize; i++) {
            for (Entry<K, V> entry : oldMap[i]) {
                put(entry.key, entry.value);
            }
        }
    }

    public static void main(String[] args) {
        HashMap<String, Integer> map = new HashMap<>(10);
        map.put("один", 1);
        map.put("два", 2);
        map.put("три", 3);

        System.out.println("Значение для 'один': " + map.get("один"));
        System.out.println("Значение для 'два': " + map.get("два"));
        System.out.println("Значение для 'три': " + map.get("три"));

        System.out.println("\nМеняем размер хэш-карты на 20...\n");
        map.resize(20);

        System.out.println("Значение для 'один': " + map.get("один"));
        System.out.println("Значение для 'два': " + map.get("два"));
        System.out.println("Значение для 'три': " + map.get("три"));
    }
}


