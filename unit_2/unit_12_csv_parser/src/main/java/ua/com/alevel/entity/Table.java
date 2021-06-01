package ua.com.alevel.entity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Table {

    private List<String> header;
    private List<String[]> table;
    private int columns;
    private int rows;

    public Table(List<String[]> table) {
        header = Arrays.asList(table.get(0));
        this.table = new ArrayList<>();

        this.table.addAll(table);
        this.table.remove(0);

        columns = header.size();
        rows = this.table.size();
    }

    public String getCell(int row, int column) {
        if (row < 0 || column < 0) {
            throw new IllegalStateException();
        }
        return table.get(row)[column];
    }

    public String getCell(int row, String column) {
        if (row < 0 || !header.contains(column)) {
            throw new IllegalStateException();
        }
        return table.get(row)[header.indexOf(column)];
    }

    public List<String> getHeader() {
        return header;
    }

    public int getColumns() {
        return columns;
    }

    public int getRows() {
        return rows;
    }
}
