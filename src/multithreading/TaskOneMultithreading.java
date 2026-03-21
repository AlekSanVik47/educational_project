package multithreading;

import java.util.Arrays;

/**
 * Класс для сравнения производительности однопоточного и многопоточного вычисления.
 * Реализует два метода: calculate() (однопоточный) и calculateTwoArray() (многопоточный).
 * @see "See file: docs/TASK_DESCRIPTION.md"
 */
public class TaskOneMultithreading {
    static final int size = 10_000_000;
    static final int h = size / 2;
    static final float[] arr = new float[size];

    /**
     * Однопоточный метод вычисления значений массива по формуле.
     * Заполняет массив единицами, затем вычисляет каждое значение.
     * Замеряет время выполнения только цикла расчета.
     *
     * @param arr массив для вычисления
     */
    public static void calculate(float[] arr) {
        // Заполняем массив единицами (не входит в замер времени)
        Arrays.fill(arr, 1f);
        
        // Засекаем время начала расчета
        long startTime = System.currentTimeMillis();
        
        // Цикл расчета
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (float)(arr[i] * Math.sin(0.2f + i / 5.0) * Math.cos(0.2f + i / 5.0) * Math.cos(0.4f + i / 2.0));
        }
        
        long calculationTime = System.currentTimeMillis() - startTime;
        System.out.println("Время расчета (однопоточный): " + calculationTime + " мс");
    }

    /**
     * Многопоточный метод вычисления значений массива.
     * Разбивает массив на две части, вычисляет каждую часть в отдельном потоке,
     * затем склеивает результаты обратно. Замеряет время разбивки, вычисления и склейки.
     *
     * @param arr массив для вычисления
     */
    public static void calculateTwoArray(float[] arr) {
        // Заполняем исходный массив единицами (как в однопоточном методе)
        Arrays.fill(arr, 1f);
        
        // Общее время начала
        long totalStart = System.currentTimeMillis();
        
        // 1. Разбивка массива на две части
        long splitStart = System.currentTimeMillis();
        float[] a1 = new float[h];
        float[] a2 = new float[h];
        System.arraycopy(arr, 0, a1, 0, h);
        System.arraycopy(arr, h, a2, 0, h);
        long splitTime = System.currentTimeMillis() - splitStart;
        
        // 2. Расчет в двух потоках (каждый поток вызывает calculate для своей части)
        long calculationStart = System.currentTimeMillis();
        Thread t1 = new Thread(() -> calculate(a1));
        Thread t2 = new Thread(() -> calculate(a2));
        t1.start();
        t2.start();
        
        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        long calculationTime = System.currentTimeMillis() - calculationStart;
        
        // 3. Склейка массивов обратно
        long mergeStart = System.currentTimeMillis();
        System.arraycopy(a1, 0, arr, 0, h);
        System.arraycopy(a2, 0, arr, h, h);
        long mergeTime = System.currentTimeMillis() - mergeStart;
        
        long totalTime = System.currentTimeMillis() - totalStart;
        
        // Вывод результатов
        System.out.println("=== Результаты многопоточного расчета ===");
        System.out.println("Время разбивки массива: " + splitTime + " мс");
        System.out.println("Время расчета в двух потоках: " + calculationTime + " мс");
        System.out.println("Время склейки массивов: " + mergeTime + " мс");
        System.out.println("Общее время выполнения: " + totalTime + " мс");
    }


    public static void main(String[] args) {
        System.out.println("=== Сравнение производительности однопоточного и многопоточного вычисления ===");
        System.out.println("Размер массива: " + size + " элементов");
        System.out.println();
        
        // Однопоточный расчет
        System.out.println("1. Однопоточный метод:");
        float[] arr1 = new float[size];
        calculate(arr1);
        
        System.out.println();
        
        // Многопоточный расчет
        System.out.println("2. Многопоточный метод:");
        float[] arr2 = new float[size];
        calculateTwoArray(arr2);
        
        System.out.println();
        System.out.println("=== Сравнение завершено ===");
    }

}
