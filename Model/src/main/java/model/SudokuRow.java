package model;

import java.util.Objects;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class SudokuRow extends SudokuElement {

    private final int rowIndex;

    public SudokuRow(int y, final SudokuBoard board) {
        super(board);
        rowIndex = y;
    }

    @Override
    public void update() {
        int columnIndex = 0;
        for (SudokuField field: board.getBoard()[rowIndex]) {
            element.set(columnIndex, field);
            ++columnIndex;
        }
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.NO_CLASS_NAME_STYLE)
                .append(rowIndex + 1 + " ROW", super.toString()).toString();
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }
        SudokuRow sudokuRow = (SudokuRow) o;
        return rowIndex == sudokuRow.rowIndex;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), rowIndex);
    }

    @Override
    public SudokuRow clone() throws CloneNotSupportedException {
        return (SudokuRow) super.clone();
    }
}
