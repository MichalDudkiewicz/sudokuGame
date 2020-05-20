/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

import java.io.Serializable;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class SudokuBoard implements Serializable, Cloneable {

    private final transient int sudokuWidth = 9;
    private final SudokuField[][] board = new SudokuField[sudokuWidth][sudokuWidth];
    private final List<SudokuRow> rows = Arrays.asList(new SudokuRow[sudokuWidth]);
    private final List<SudokuColumn> columns = Arrays.asList(new SudokuColumn[sudokuWidth]);
    private final List<SudokuBox> boxes = Arrays.asList(new SudokuBox[sudokuWidth]);
    private final SudokuSolver solver;

    /**
     * Class for storing boards.
     * @param solverToUse Defines which of the available solvers will be used
     *              with the board.
     */
    public SudokuBoard(final SudokuSolver solverToUse) {
        solver = solverToUse;
        for (SudokuField[] row: board) {
            for (int index = 0; index < sudokuWidth; ++index) {
                row[index] = new SudokuField();
            }
        }
        for (int i = 0; i < sudokuWidth; ++i) {
            rows.set(i, new SudokuRow(i, this));
            columns.set(i, new SudokuColumn(i, this));
            boxes.set(i, new SudokuBox(i / 3, i % 3, this));
            getRow(i).update();
            getColumn(i).update();
            boxes.get(i).update();
        }
    }

    public void solveGame() {
        solver.solve(this);
    }

    public SudokuField[][] getBoard() {
        return board.clone();
    }

    public SudokuField get(final Integer x, final Integer y) {
        return board[x][y];
    }

    /**
     * Function for defining the values of a board.
     * @param x The X-coordinate of the field.
     * @param y The Y-coordinate of the field.
     * @param digit The value of the field.
     */
    public void set(final Integer x, final Integer y, final Integer digit) {
        board[x][y].setValue(digit);
        getRow(y).update();
        getColumn(x).update();
        getBox(x, y).update();
    }

    /**
     * Function to check whether the values inserted into the board comply
     *              with the rules of sudoku.
     * @return If all requirements are met, returns true.
     */
    public boolean checkCorrectness() {
        Set<Integer> columnSet = new HashSet<>();
        Set<Integer> rowSet = new HashSet<>();

        for (int i = 0; i < sudokuWidth; i++) {
            for (int j = 0; j < sudokuWidth; j++) {
                rowSet.add(board[i][j].getValue());
                columnSet.add(board[j][i].getValue());
            }
            if (rowSet.size() != sudokuWidth || columnSet.size() != sudokuWidth) {
                return false;
            }
            rowSet.clear();
            columnSet.clear();
        }
        return true;
    }

    public SudokuBox getBox(final Integer x, final Integer y) {
        return boxes.get((x / 3) * 3 + y / 3);
    }

    public SudokuRow getRow(final Integer y) {
        return rows.get(y);
    }

    public SudokuColumn getColumn(final Integer x) {
        return columns.get(x);
    }

    public int getSudokuWidth() {
        return sudokuWidth;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.NO_CLASS_NAME_STYLE)
                .append("SUDOKU BOARD", "\n"
                        + Arrays.deepToString(board).replace("],", "],\n")).toString();
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        SudokuBoard that = (SudokuBoard) o;
        return Arrays.deepEquals(board, that.board);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(sudokuWidth);
        result = 31 * result;
        for (SudokuField[] row: board) {
            result += Arrays.hashCode(row);
        }
        return result;
    }

    @Override
    public SudokuBoard clone() {
        SudokuBoard clone = new SudokuBoard(solver);
        for (int i = 0; i < getSudokuWidth(); ++i) {
            for (int j = 0; j < getSudokuWidth(); ++j) {
                clone.set(i, j, get(i, j).getValue());
            }
        }
        return clone;
    }
}