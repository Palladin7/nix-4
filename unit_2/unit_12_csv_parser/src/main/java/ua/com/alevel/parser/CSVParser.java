package ua.com.alevel.parser;

import ua.com.alevel.annotation.Deserializable;
import ua.com.alevel.entity.Table;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

public class CSVParser {

    public <T> List<T> getData(Table table, Class<T> clazz) {
        List<T> data = new ArrayList<>();

        try {
            Constructor<T> tConstructor = clazz.getConstructor();

            for (int i = 0; i < table.getRows(); i++) {
                T element = tConstructor.newInstance();

                for (Field field : clazz.getDeclaredFields()) {
                    field.setAccessible(true);

                    Deserializable deserializable = field.getAnnotation(Deserializable.class);
                    String value = table.getCell(i, deserializable.value());

                    Class<?> type = field.getType();

                    if (type == String.class) {
                        field.set(element, value);
                    } else if (type.isEnum()) {
                        field.set(element, Enum.valueOf((Class<Enum>) type, value));
                    } else if (type == int.class || type == Integer.class) {
                        field.set(element, Integer.parseInt(value));
                    } else if (type == long.class || type == Long.class) {
                        field.set(element, Long.parseLong(value));
                    } else if (type == double.class || type == Double.class) {
                        field.set(element, Double.parseDouble(value));
                    } else if (type == boolean.class || type == Boolean.class) {
                        field.set(element, Boolean.parseBoolean(value));
                    } else {
                        throw new UnsupportedOperationException();
                    }
                }
                data.add(element);
            }
        } catch (NoSuchMethodException | InvocationTargetException | InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return data;
    }
}
