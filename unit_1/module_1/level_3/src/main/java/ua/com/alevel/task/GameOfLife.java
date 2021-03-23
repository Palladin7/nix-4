package ua.com.alevel.task;

public class GameOfLife {
    // Dimensions of board
    static int m = 10;
    static int n = 20;

    // Board itself
    static int[][] board = new int[m][n];

    // Initialize board
    static {
        populate();
    }

    // Populate board
    public static void populate() {
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                // Probability of cell being alive is 1 / 7
                int aliveProbability = (int) (Math.random() * 10) % 7;
                // Populate cells with probability of being alive lower then dead
                if (aliveProbability == 0) {
                    board[i][j] = 1;
                } else {
                    board[i][j] = 0;
                }
            }
        }
    }

    public static void printBoard() {
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                // Last cell in row
                if (j == n - 1) {
                    // Don't print 0 for dead cell
                    if (board[i][j] == 0) {
                        System.out.println("| |");
                    } else {
                        System.out.println("|" + board[i][j] + "|");
                    }
                } else {
                    // Don't print 0 for dead cell
                    if (board[i][j] == 0) {
                        System.out.print("| ");
                    } else {
                        System.out.print("|" + board[i][j]);
                    }
                }
            }
        }
        System.out.println();
    }

    // Function for calculation nex state of board
    public static void nextState() {
        int[][] newBoard = new int[m][n];
        // Check each cell
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (cellWillBeAlive(i, j)) {
                    newBoard[i][j] = 1;
                } else {
                    newBoard[i][j] = 0;
                }
            }
        }
        board = newBoard;
        printBoard();
    }

    // Check current cell's neighbours to see if it will survive
    public static boolean cellWillBeAlive(int row, int column) {
        int aliveNeighbours = 0;

        // Check 3 neighbours above the cell
        for (int j = column - 1; j <= column + 1; j++) {
            if (row - 1 >= 0 && j >= 0 && j < n && board[row - 1][j] == 1) {
                aliveNeighbours++;
            }
        }
        // Check two neighbours left and right to cell
        for (int j = column - 1; j <= column + 1; j += 2) {
            if (j >= 0 && j < n && board[row][j] == 1) {
                aliveNeighbours++;
            }
        }
        // Check two neighbours under the cell
        for (int j = column - 1; j <= column + 1; j++) {
            if (row + 1 < m && j >= 0 && j < n && board[row + 1][j] == 1) {
                aliveNeighbours++;
            }
        }

        // Cell was dead before
        if (board[row][column] == 0) {
            return aliveNeighbours == 3;
        } else {
            return aliveNeighbours == 2 || aliveNeighbours == 3;
        }
    }
}
