package model;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class StrangeSudokuSolver implements SudokuSolver, Serializable {

    @Override
    public boolean solve(final SudokuBoard board) {
        List<Integer> digits = IntStream.range(1, board.getSudokuWidth() + 1)
                .boxed().collect(Collectors.toList());
        for (int i = 0; i < board.getSudokuWidth(); i++) {
            for (int j = 0; j < board.getSudokuWidth(); j++) {
                if (board.get(i, j).getValue() != 0) {
                    continue;
                }
                Collections.shuffle(digits);
                for (int digit : digits) {
                    if (isValid(board, i, j, digit)) {
                        board.set(i, j, digit);
                        if (solve(board)) {
                            return true;
                        }
                        board.set(i, j, 0);
                    }
                }
                return false;
            }
        }
        return true; //return true if all cells are checked
    }

    private boolean isValid(final SudokuBoard board,
             final Integer row,
             final Integer col,
             final Integer digit) {

        for (SudokuField field: board.getRow(row).getElement()) {
            if (field.getValue().equals(digit)) {
                return false;
            }
        }
        for (SudokuField field: board.getColumn(col).getElement()) {
            if (field.getValue().equals(digit)) {
                return false;
            }
        }
        for (SudokuField field: board.getBox(row, col).getElement()) {
            if (field.getValue().equals(digit)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public String toString() {
        return "Strange Sudoku Solver";
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        return hashCode() == o.hashCode();
    }

    @Override
    public int hashCode() {
        return toString().hashCode();
    }
}
