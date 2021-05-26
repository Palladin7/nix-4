package ua.com.alevel;

import ua.com.alevel.appproperties.AppProperties;
import ua.com.alevel.utility.AppPropertiesUtility;

import java.io.IOException;

public class MainReflection {

    public static void main(String[] args) {
        try {
            AppProperties appProperties = (AppProperties) AppPropertiesUtility.setAppProperties();
            System.out.println(appProperties);
        } catch (IllegalAccessException | IOException e) {
            e.printStackTrace();
        }
    }
}
