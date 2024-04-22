import java.util.Scanner;

public class SudokuSolver {
    private static final int SIZE = 9;
    private int[][] grid;

    public SudokuSolver(int[][] grid) {
        this.grid = grid;
    }

    public boolean solveSudoku() {
        int row = -1;
        int col = -1;
        boolean isEmpty = true;

        // Find an empty cell
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (grid[i][j] == 0) {
                    row = i;
                    col = j;
                    isEmpty = false;
                    break;
                }
            }
            if (!isEmpty) {
                break;
            }
        }

        // If there is no empty cell, puzzle is solved
        if (isEmpty) {
            return true;
        }

        // Try values from 1 to 9
        for (int num = 1; num <= SIZE; num++) {
            if (isSafe(row, col, num)) {
                grid[row][col] = num;

                if (solveSudoku()) {
                    return true;
                } else {
                    // Reset cell if no solution found
                    grid[row][col] = 0;
                }
            }
        }
        return false;
    }

    private boolean isSafe(int row, int col, int num) {
        // Check row
        for (int i = 0; i < SIZE; i++) {
            if (grid[row][i] == num) {
                return false;
            }
        }

        // Check column
        for (int i = 0; i < SIZE; i++) {
            if (grid[i][col] == num) {
                return false;
            }
        }

        // Check subgrid (3x3)
        int startRow = row - row % 3;
        int startCol = col - col % 3;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (grid[i + startRow][j + startCol] == num) {
                    return false;
                }
            }
        }
        return true;
    }

    public void displaySudoku() {
        for (int i = 0; i < SIZE; i++) {
            if (i % 3 == 0 && i != 0) {
                System.out.println("---------------------");
            }
            for (int j = 0; j < SIZE; j++) {
                if (j % 3 == 0 && j != 0) {
                    System.out.print("| ");
                }
                System.out.print(grid[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        int[][] puzzle = new int[SIZE][SIZE];
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the Sudoku puzzle (use 0 to represent empty cells):");
        for (int i = 0; i < SIZE; i++) {
            System.out.printf("Enter row %d: ", i + 1);
            for (int j = 0; j < SIZE; j++) {
                puzzle[i][j] = scanner.nextInt();
            }
        }

        SudokuSolver solver = new SudokuSolver(puzzle);
        if (solver.solveSudoku()) {
            System.out.println("Sudoku puzzle solved:");
            solver.displaySudoku();
        } else {
            System.out.println("No solution exists for the Sudoku puzzle.");
        }
    }
}
