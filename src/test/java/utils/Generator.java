package utils;

import java.util.Random;

public class Generator {
    /**
     * Генерирует целое число в диапазоне от 1 до N (включительно)
     * @param n верхняя граница диапазона (должна быть положительной)
     * @return случайное число от 1 до N
     * @throws IllegalArgumentException если n <= 0
     */
    public static int generate(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("N must be positive");
        }
        return new Random().nextInt(n) + 1;
    }
}
