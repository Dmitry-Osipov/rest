package com.osipov.environment;

import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;

import java.io.InputStream;
import java.util.Properties;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@UtilityClass
public class PropertiesReader {
    private final Lock lock = new ReentrantLock();
    private Properties properties;

    @SneakyThrows
    public Properties getProperties(String fileName) {
        if (properties == null) {
            boolean locked = lock.tryLock();
            if (properties == null) {
                properties = new Properties();
                try (InputStream in = PropertiesReader.class.getClassLoader().getResourceAsStream(fileName)) {
                    if (in == null) {
                        throw new IllegalArgumentException("Properties file not found: " + fileName);
                    }

                    properties.load(in);
                }
            }

            if (locked) {
                lock.unlock();
            }
        }

        return properties;
    }
}
