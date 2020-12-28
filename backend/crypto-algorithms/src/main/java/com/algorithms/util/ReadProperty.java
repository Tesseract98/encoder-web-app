package com.algorithms.util;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@Slf4j
public class ReadProperty {
    private static final ReadProperty readProperty = new ReadProperty();
    private final Properties configProperty;

    private ReadProperty() {

        InputStream propertyStream = ClassLoader.getSystemClassLoader().
                getResourceAsStream("config.properties");
        configProperty = new Properties();
        try {
            configProperty.load(propertyStream);
        } catch (IOException e) {
            log.warn("config.property not found:", e.getCause());
            throw new RuntimeException();
        }

    }

    public static ReadProperty getInstance() {
        return readProperty;
    }

    public Properties getConfigProperty() {
        return configProperty;
    }

}
