package multithreading;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Counter {
    private int value = 0;

    // Без синхронизации → гонка данных (race condition)
    public void increment() {
        System.out.println("Счетчик до увеличения равно:\t\t"+value + ":" + " поток-> " + Thread.currentThread().getName());
        value++;
        System.out.println("Счётчик после инкремента:"+"\t"+ value + ":" + " поток-> " + Thread.currentThread().getName());
        System.out.println("===============================");
    }

    // synchronized-метод
    public synchronized void incrementSync() {
        System.out.println("Счетчик до увеличения равно:\t\t"+value + ":" + " поток-> " + Thread.currentThread().getName());
        value++;
        System.out.println("Счётчик после инкремента:"+"\t"+ value + ":" + " поток-> " + Thread.currentThread().getName());
        System.out.println("===============================");
    }

    // ReentrantLock
    private final Lock lock = new ReentrantLock();
    public void incrementLock() {
        lock.lock();
        try {
            System.out.println("Счетчик до увеличения равно:\t\t"+value + ":" + " поток-> " + Thread.currentThread().getName());
            value++;
            System.out.println("Счётчик после инкремента:"+"\t"+ value + ":" + " поток-> " + Thread.currentThread().getName());
            System.out.println("===============================");
        } finally { lock.unlock(); }
    }

    public static void main(String[] args) {
        Counter counter =new Counter();
        System.out.println("Начальное значение счетчика:"+" "+counter.value+"\n");
        for(int i=1; i<=5;i++) {

            //               counter.increment();
            //                counter.incrementLock();
            Runnable task = counter::incrementSync;

            Thread t = new Thread(task, "Worker-" + Integer.toString(i) + "-thread");
                t.start();
        }

    }
}
