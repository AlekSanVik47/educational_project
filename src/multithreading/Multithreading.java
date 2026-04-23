package multithreading;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

public class Multithreading extends Thread{
    // Способ 1: Runnable + лямбда (Java 8+)
    Runnable task = () -> {
        System.out.println("Поток: " + Thread.currentThread().getName());
        concurrencyCollections();
    };

    public void getT() {
        for(int i=1; i<5;i++) {
            Thread t = new Thread(task, "Worker-" + i);

            t.start(); // ! Важно: start(), а не run()
        }
    }

    @Override
    public String toString() {
        return "Multithreading{}";
    }

    public void concurrencyCollections() {
    // ConcurrentHashMap
    Map<String, Integer> map = new ConcurrentHashMap<>();
    map.putIfAbsent("key", 0); // атомарно
        map.putIfAbsent("key1", 5);
        map.putIfAbsent("key", 0);
        map.forEach((key, value) -> {
            map.compute("key", (k, v) -> (v == null) ? 1 : v + 1); // Java 8, атомарно
            System.out.println(key + " " + value + " поток: " + Thread.currentThread().getName());
        });
        System.out.println(map);
    // CopyOnWriteArrayList
    List<String> list = new CopyOnWriteArrayList<>();
    list.add("A");
    for (String s : list) { // безопасно даже при параллельном изменении
        System.out.println(s);
    }
}
    public static void main(String[] args) {
        new Multithreading().getT();

//        new Multithreading().concurrencyCollections();

    }

}
