package ua.com.alevel.appproperties;

import ua.com.alevel.annotation.PropertyKey;

public class AppProperties {
    @PropertyKey("version")
    public String version;

    @PropertyKey("name")
    public String name;

    @PropertyKey("created")
    public String created;

    @Override
    public String toString() {
        return "AppProperties{" +
                "version='" + version + '\'' +
                ", name='" + name + '\'' +
                ", created='" + created + '\'' +
                '}';
    }
}
