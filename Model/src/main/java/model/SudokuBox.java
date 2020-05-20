package model;

import java.util.Objects;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class SudokuBox extends SudokuElement {

    private final int rowIndex;
    private final int columnIndex;

    /**
     * Class for defining the boxes (3x3 squares) of the board.
     * @param x The X-coordinate of the field.
     * @param y The Y-coordinate of the field.
     * @param board The board which the box is part of.
     */
    public SudokuBox(int x, int y, final SudokuBoard board) {
        super(board);
        rowIndex = x;
        columnIndex = y;
    }

    @Override
    public void update() {
        int rowIndexIt = 0;
        int boxIndex = 0;
        for (SudokuField[] row : board.getBoard()) {
            if (rowIndexIt / 3 == rowIndex) {
                int columnIndexIt = 0;
                for (SudokuField field : row) {
                    if (columnIndexIt / 3 == columnIndex) {
                        element.set(boxIndex, field);
                        ++boxIndex;
                    }
                    ++columnIndexIt;
                }
            }
            ++rowIndexIt;
        }
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.NO_CLASS_NAME_STYLE)
                .append(rowIndex + 1 + "/" + (columnIndex + 1) + " BOX",
                        super.toString()).toString();
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        SudokuBox sudokuBox = (SudokuBox) o;
        return rowIndex == sudokuBox.rowIndex
                && columnIndex == sudokuBox.columnIndex;
    }

    @Override
    public int hashCode() {
        return Objects.hash(rowIndex, columnIndex);
    }

    @Override
    public SudokuBox clone() throws CloneNotSupportedException {
        return (SudokuBox) super.clone();
    }
}
