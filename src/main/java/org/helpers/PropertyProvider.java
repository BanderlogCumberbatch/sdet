package org.helpers;

import lombok.Getter;

import java.io.InputStream;
import java.util.Properties;

/**
 * Класс для получения параметров из .properties-файла (с envLocalInstance).
 */
@Getter
public class PropertyProvider {

    /**
     * Экземпляр класса env_local.properties.
     */
    private static PropertyProvider envLocalInstance;

    /**
     * Экземпляр класса secrets.properties.
     */
    private static PropertyProvider secretsInstance;

    /**
     * Класс для загрузки .properties-файла.
     */
    private final Properties properties = new Properties();

    /**
     * Создать новый экземпляр класса для env_local.properties, если он ещё не создан.
     * @return Текущий экземпляр класса
     */
    public static PropertyProvider getEnvLocalInstance() {
        if (envLocalInstance == null) {
            envLocalInstance = new PropertyProvider("env_local.properties");
        }
        return envLocalInstance;
    }

    /**
     * Создать новый экземпляр класса для env_local.properties, если он ещё не создан.
     * @return Текущий экземпляр класса
     */
    public static PropertyProvider getSecretsInstance() {
        if (secretsInstance == null) {
            secretsInstance = new PropertyProvider("secrets.properties");
        }
        return secretsInstance;
    }

    /**
     * Загрузка .properties-файла.
     * @param propertiesFileName имя .properties-файла в папке resources
     */
    public PropertyProvider(final String propertiesFileName) {
        try (InputStream input = getClass().getClassLoader()
                .getResourceAsStream(propertiesFileName)) {
            properties.load(input);
        } catch (Exception e) {
            throw new RuntimeException("Failed to load properties file", e);
        }
    }

    /**
     * Загрузить параметр из .properties-файла.
     * @param key ключ, для нахождения параметра
     * @return параметр, найденный по ключу
     */
    public String getProperty(String key) { return  properties.getProperty(key); }
}