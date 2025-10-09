package org.helpers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;

import java.io.*;
import java.util.Date;

public class CookieHelper {
    private static final ObjectMapper mapper = new ObjectMapper();

    /**
     * Запись cookies в файл
     * @param driver текущий веб-драйвер
     * @param file файл для записи
     */
    public static void saveCookiesToFile(WebDriver driver, File file) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            for (Cookie cookie : driver.manage().getCookies()) {
                String json = mapper.writeValueAsString(cookie);
                writer.write(json);
                writer.newLine();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Чтение cookies из файла
     * @param driver текущий веб-драйвер
     * @param file файл для чтения
     */
    public static void loadCookiesFromFile(WebDriver driver, File file) {
        driver.manage().deleteAllCookies();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                DeserializableCookie sc = mapper.readValue(line, DeserializableCookie.class);
                driver.manage().addCookie(sc.toCookie());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Вспомогательный класс для сериализации
     */
    static class DeserializableCookie {
        public String name;
        public String value;
        public String domain;
        public String path;
        public Date expiry;
        public String sameSite;
        public boolean secure;
        public boolean httpOnly;

        public Cookie toCookie() {
            return new Cookie.Builder(name, value)
                    .domain(domain)
                    .path(path)
                    .expiresOn(expiry)
                    .sameSite(sameSite)
                    .isSecure(secure)
                    .isHttpOnly(httpOnly)
                    .build();
        }
    }
}
