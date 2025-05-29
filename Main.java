

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
