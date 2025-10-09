package org.helpers;

import lombok.Getter;

import java.io.InputStream;
import java.util.Properties;

@Getter
public class PropertyProvider {

    private static PropertyProvider instance;

    private final Properties properties = new Properties();

    /**
     * Получение экземпляра
     */
    public static PropertyProvider getInstance() {
        if (instance == null) {
            instance = new PropertyProvider();
        }
        return instance;
    }

    /**
     * Загрузка .properties-файла
     */
    private PropertyProvider() {
        try (InputStream input = getClass().getClassLoader()
                .getResourceAsStream("env_local.properties")) {
            properties.load(input);
        } catch (Exception e) {
            throw new RuntimeException("Failed to load properties file", e);
        }
    }

    /**
     * Получить property
     * @param key Ключ property
     */
    public String getProperty(String key) { return  properties.getProperty(key); }
}
