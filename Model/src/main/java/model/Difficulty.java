package model;

import static java.lang.Math.pow;

import java.util.Random;

public enum Difficulty {

    EASY(50),
    MEDIUM(25),
    HARD(10);

    private final float percentageFilled;

    Difficulty(int percentageToFill) {
        this.percentageFilled = percentageToFill;
    }

    /**
     * Function for building a board based on selected difficulty.
     * @return Returns an appropriately filled board.
     */
    public SudokuBoard buildSudoku() {
        SudokuBoard board = new SudokuBoard(new StrangeSudokuSolver());
        board.solveGame();
        int x;
        int y;
        int counter = 0;
        while (counter <= (100 - percentageFilled) / 100 * pow(board.getSudokuWidth(), 2)) {
            x = new Random().nextInt(9);
            y = new Random().nextInt(9);
            if (board.get(x, y).getValue() != 0) {
                board.set(x, y, 0);
                ++counter;
            }
        }
        return board;
    }

    public float getPercentageFilled() {
        return percentageFilled;
    }
}
