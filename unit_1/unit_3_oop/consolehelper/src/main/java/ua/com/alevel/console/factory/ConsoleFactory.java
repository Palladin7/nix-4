package ua.com.alevel.console.factory;

import org.reflections.Reflections;
import ua.com.alevel.console.ConsoleService;
import ua.com.alevel.console.impl.DefaultConsoleService;

import java.util.Set;

public class ConsoleFactory {
    private static ConsoleFactory instance;
    private Reflections reflections;
    private Set<Class<? extends ConsoleService>> consoles;

    private ConsoleFactory() {
        reflections = new Reflections("ua.com.alevel");
        consoles = reflections.getSubTypesOf(ConsoleService.class);
    }

    public static ConsoleFactory getInstance() {
        if (instance == null) {
            instance = new ConsoleFactory();
        }
        return instance;
    }

    public ConsoleService getConsoleService() throws RuntimeException{
        for (Class<? extends ConsoleService> console : consoles) {
            if (!console.isAnnotationPresent(Deprecated.class)) {
                try {
                    return console.getDeclaredConstructor().newInstance();
                } catch (Exception e) {
                    throw new RuntimeException();
                }
            }
        }
        throw new RuntimeException();
    }
}
