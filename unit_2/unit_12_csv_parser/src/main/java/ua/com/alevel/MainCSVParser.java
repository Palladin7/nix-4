package ua.com.alevel;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import ua.com.alevel.entity.Product;
import ua.com.alevel.entity.Table;
import ua.com.alevel.parser.CSVParser;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class MainCSVParser {

    public static void main(String[] args) {
        String path = MainCSVParser.class.getClassLoader().getResource("input.csv").getPath();

        try (CSVReader reader = new CSVReader(new FileReader(path))) {
            Table table = new Table(reader.readAll());
            CSVParser parser = new CSVParser();
            List<Product> products = parser.getData(table, Product.class);

            System.out.println();

            for (Product product : products) {
                System.out.println(product);
            }
        } catch (IOException | CsvException e) {
            e.printStackTrace();
        }
    }
}
