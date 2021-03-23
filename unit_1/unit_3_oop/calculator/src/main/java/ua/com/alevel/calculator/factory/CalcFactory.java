package ua.com.alevel.calculator.factory;

import ua.com.alevel.calculator.CalcService;

import org.reflections.Reflections;
import java.util.Set;

public class CalcFactory {
    private static CalcFactory instance;
    private Reflections reflections;
    private Set<Class<? extends CalcService>> calcServices;

    private CalcFactory() {
        reflections = new Reflections("ua.com.alevel");
        calcServices = reflections.getSubTypesOf(CalcService.class);
    }

    public static CalcFactory getInstance() {
        if (instance == null) {
            instance = new CalcFactory();
        }
        return instance;
    }

    public CalcService getCalcService() {
        for (Class<? extends CalcService> calcService : calcServices) {
            if (!calcService.isAnnotationPresent(Deprecated.class)) {
                try {
                    return calcService.getDeclaredConstructor().newInstance();
                } catch (Exception e) {
                    throw new RuntimeException();
                }
            }
        }
        throw new RuntimeException();
    }
}
