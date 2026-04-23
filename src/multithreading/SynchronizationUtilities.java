package multithreading;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.ThreadPoolExecutor;

public class SynchronizationUtilities {
    ExecutorService service = Executors.newFixedThreadPool(10);
    // Русский алфавит (заглавные)
    private static final String RU_UPPER = "АБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯ";
    // Русский алфавит (строчные)
    private static final String RU_LOWER = "абвгдеёжзийклмнопрстуфхцчшщъыьэюя";

    private static final int LIMIT = 10;

    public void threadTask() {
        for (int i = 0; i < 5; i++) {
            service.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println("Выполняется в: " + Thread.currentThread().getName());
                    testTsk();
                }
            });
        }
        ThreadPoolExecutor pool = ((ThreadPoolExecutor) service);
        System.out.println("Тип пула: " + service.getClass().getName());
        System.out.println("Размер пула: " + pool.getPoolSize());
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        System.out.println("Активные потоки: " + pool.getActiveCount());
        System.out.println("Всего задач: " + pool.getTaskCount());
        System.out.println("Завершённые задачи: " + pool.getCompletedTaskCount());

        service.shutdown();

        // Ждём завершения всех задач
        try {
            if (!service.awaitTermination(5, java.util.concurrent.TimeUnit.SECONDS)) {
                ((ThreadPoolExecutor) service).shutdownNow();
            }
        } catch (InterruptedException e) {
            ((ThreadPoolExecutor) service).shutdownNow();
            Thread.currentThread().interrupt();
        }

        System.out.println("Пул завершён. Активные потоки: " + ((ThreadPoolExecutor) service).getActiveCount());
        System.out.println("Завершено: " + service.isTerminated());
    }

    public static void testTsk() {
        List<String> list = new ArrayList<>();
        for (int i = 1; i <= 5; ++i) {
            String srt = typingLetters();
            list.add(srt);
        }
        System.out.println(list);

    }

    public static String generateUpper(int length) {
        StringBuilder sb = new StringBuilder(length);
        int bound = RU_UPPER.length();
        for (int i = 0; i < length; i++) {
            sb.append(RU_UPPER.charAt(ThreadLocalRandom.current().nextInt(bound)));
        }
        System.out.println(sb);
        return sb.toString();
    }

    private static String typingLetters() {
        String str = "";
        StringBuilder builder = new StringBuilder(LIMIT);
        int bound = RU_UPPER.length();
        for (int i = 0; i < LIMIT - 1; i++) {
            builder.append(str).append(RU_UPPER.charAt(ThreadLocalRandom.current().nextInt(bound)));
        }
        return builder.toString();
    }


    public static void main(String[] args) {
        //      testTsk();
//        generateUpper(12);
        new SynchronizationUtilities().threadTask();

    }
}
