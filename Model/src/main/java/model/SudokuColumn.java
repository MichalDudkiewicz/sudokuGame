package model;

import java.util.Objects;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class SudokuColumn extends SudokuElement {

    private final int columnIndex;

    public SudokuColumn(int x, final SudokuBoard board) {
        super(board);
        columnIndex = x;
    }

    @Override
    public void update() {
        int rowIndex = 0;
        for (SudokuField[] row : board.getBoard()) {
            element.set(rowIndex, row[columnIndex]);
            ++rowIndex;
        }
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.NO_CLASS_NAME_STYLE)
                .append(columnIndex + 1 + " COLUMN", super.toString()).toString();
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        SudokuColumn that = (SudokuColumn) o;
        return columnIndex == that.columnIndex;
    }

    @Override
    public int hashCode() {
        return Objects.hash(columnIndex);
    }

    @Override
    public SudokuColumn clone() throws CloneNotSupportedException {
        return (SudokuColumn) super.clone();
    }
}
