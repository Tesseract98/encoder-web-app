package com.algorithms.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

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
            e.printStackTrace();
        }

    }

    public static ReadProperty getInstance() {
        return readProperty;
    }

    public Properties getConfigProperty() {
        return configProperty;
    }

}
