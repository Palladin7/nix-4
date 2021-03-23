package ua.com.alevel.task2;

public class KnightMoveOnChessboard {
    static String[][] chessboard = new String[8][8];
    static {
        setChessboard();
    }

    // Initializes chessboard cells
    private static void setChessboard() {
        char column = 'A';

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                chessboard[i][j] = (8 - i) + "" + column++;
            }
            column = 'A';
        }
    }

    // Outputs chessboard with current and desirable cell
    public static void printChessboard(String currentCell, String desirableCell) {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (chessboard[i][j].equals(currentCell)) {
                    System.out.printf("(%s)", chessboard[i][j]);
                } else if (chessboard[i][j].equals(desirableCell)) {
                    System.out.printf("[%s]", chessboard[i][j]);
                } else {
                    System.out.printf(" %s ", chessboard[i][j]);
                }
            }
            System.out.println();
        }
    }

    // Check if you can move knight from currentCell to desirableCell
    public static boolean move(String currentCell, String desirableCell) {
        // Check if current and desirable cells are present at chessboard
        if (correctCell(currentCell) && correctCell(desirableCell)) {
            // index 0 for current, index 1 for desirable
            int[] row = new int[2];
            char[] column = new char[2];

            // Get rows of current and desirable cells
            row[0] = Character.getNumericValue(currentCell.charAt(0));
            row[1] = Character.getNumericValue(desirableCell.charAt(0));

            // Get columns of current and desirable cells
            column[0] = currentCell.charAt(1);
            column[1] = desirableCell.charAt(1);

            // Find difference between rows and columns of current and desirable cells
            int rowDifference = Math.abs(row[0] - row[1]);
            int columnDifference = Math.abs(column[0] - column[1]);

            // Knight move is possible if one difference is 1 and another is 2
            return rowDifference == 2 && columnDifference == 1 ||
                    rowDifference == 1 && columnDifference == 2;
        } else {
            return false;
        }
    }

    // Check if user entered correct cell
    private static boolean correctCell(String cell) {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (chessboard[i][j].equals(cell)) {
                    return true;
                }
            }
        }
        return false;
    }
}
