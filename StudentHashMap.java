import java.util.LinkedList;

class Student {
    private final String id;
    private final String name;

    public Student(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Student{id='" + id + "', name='" + name + "'}";
    }
}

class StudentHashMap {
    private static class Entry {
        String key;
        Student value;

        Entry(String key, Student value) {
            this.key = key;
            this.value = value;
        }
    }

    private final LinkedList<Entry>[] map;
    private final int size;

    @SuppressWarnings("unchecked")
    public StudentHashMap(int size) {
        this.size = size;
        map = new LinkedList[size];
        for (int i = 0; i < size; i++) {
            map[i] = new LinkedList<>();
        }
    }

    private int hash(String key) {
        return (key.hashCode() % size + size) % size;
    }

    public void put(String key, Student value) {
        int index = hash(key);
        for (Entry entry : map[index]) {
            if (entry.key.equals(key)) {
                entry.value = value;
                return;
            }
        }
        map[index].add(new Entry(key, value));
    }

    public Student get(String key) {
        int index = hash(key);
        for (Entry entry : map[index]) {
            if (entry.key.equals(key)) {
                return entry.value;
            }
        }
        return null;
    }

    public boolean remove(String key) {
        int index = hash(key);
        for (Entry entry : map[index]) {
            if (entry.key.equals(key)) {
                map[index].remove(entry);
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        StudentHashMap studentMap = new StudentHashMap(10);
        studentMap.put("123", new Student("123", "АЛДАР"));
        studentMap.put("456", new Student("456", "ИВАН"));

        System.out.println(studentMap.get("123"));
        studentMap.remove("123");
        System.out.println(studentMap.get("123"));
    }
}
