package org.helpers;

import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;

import java.io.*;
import java.util.Date;

public class CookieHelper {

    /**
     * Запись cookies в файл (с timestamp)
     * @param driver текущий веб-драйвер
     * @param file файл для записи
     */
    public static void saveCookiesToFile(WebDriver driver, File file) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            for (Cookie cookie : driver.manage().getCookies()) {
                writer.write(cookie.getName() + ";" +
                        cookie.getValue() + ";" +
                        cookie.getDomain() + ";" +
                        cookie.getPath() + ";" +
                        (cookie.getExpiry() != null ? cookie.getExpiry().getTime() : "null") + ";" +
                        cookie.isSecure() + ";" +
                        cookie.isHttpOnly());
                writer.newLine();
            }
        } catch (IOException e) {
            throw new IllegalArgumentException("Ошибка сохранения cookies файла", e);
        }
    }

    /**
     * Чтение cookies из файла (с timestamp)
     * @param driver текущий веб-драйвер
     * @param file файл для чтения
     */
    public static void loadCookiesFromFile(WebDriver driver, File file) {
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(";");

                if (parts.length < 7) continue;

                Date expiry = null;
                if (!"null".equals(parts[4])) {
                    try {
                        long timestamp = Long.parseLong(parts[4]);
                        expiry = new Date(timestamp);
                    } catch (NumberFormatException e) {
                        System.err.println("Ошибка парсинга timestamp: " + parts[4]);
                    }
                }

                Cookie cookie = new Cookie.Builder(parts[0], parts[1])
                        .domain(parts[2])
                        .path(parts[3])
                        .expiresOn(expiry)
                        .isSecure(Boolean.parseBoolean(parts[5]))
                        .isHttpOnly(Boolean.parseBoolean(parts[6]))
                        .build();

                driver.manage().addCookie(cookie);
            }
        } catch (IOException e) {
            throw new IllegalArgumentException("Ошибка загрузки cookies файла", e);
        }
    }
}
