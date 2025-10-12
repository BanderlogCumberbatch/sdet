package utils;

public class Finder {
    /**
     * Находит строку максимальной длинны из массива строк
     * @param strings массив строк
     * @return строка максимальной длинны
     */
    public static String findLongestString(String[] strings) {
        String longestString = "";
        for (String hobby : strings) {
            if (hobby.length() > longestString.length()) {
                longestString = hobby;
            }
        }
        return longestString;
    }
}
