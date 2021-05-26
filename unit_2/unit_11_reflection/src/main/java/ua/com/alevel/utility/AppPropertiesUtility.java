package ua.com.alevel.utility;

import ua.com.alevel.MainReflection;
import ua.com.alevel.annotation.PropertyKey;
import ua.com.alevel.appproperties.AppProperties;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.Properties;

public class AppPropertiesUtility {

    public static Properties readProperties() throws IOException {
        Properties properties = new Properties();

        try (InputStream inputStream = MainReflection.class.getResourceAsStream("/app.properties")) {
            properties.load(inputStream);
        }
        return properties;
    }

    public static Object setAppProperties() throws IllegalAccessException, IOException {
        Properties properties = readProperties();
        Object appProperties = new AppProperties();

        Field[] fields = appProperties.getClass().getDeclaredFields();

        // Initialize fields
        for (Field field : fields) {
            if (field.isAnnotationPresent(PropertyKey.class)) {
                PropertyKey propertyKey = field.getAnnotation(PropertyKey.class);
                field.setAccessible(true);
                field.set(appProperties, properties.getProperty(propertyKey.value()));
            }
        }
        return appProperties;
    }
}
