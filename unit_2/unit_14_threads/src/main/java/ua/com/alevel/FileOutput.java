package ua.com.alevel;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FileOutput extends Thread {

    private final ConsoleReader consoleReader = new ConsoleReader();

    @Override
    public void run() {
        consoleReader.start();

        while (consoleReader.isAlive()) {
            try {
                sleep(1000);
                write(consoleReader.getInput());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void write(String output) {
        File file = new File("output.txt");
        try (FileWriter fileWriter = new FileWriter(file)) {
            fileWriter.write(output);
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }
}
