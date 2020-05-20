package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public abstract class SudokuElement implements Serializable, Cloneable {

    protected List<SudokuField> element;
    SudokuBoard board;

    public SudokuElement(final SudokuBoard boardToTrack) {
        board = boardToTrack;
        element = Arrays.asList(new SudokuField[board.getSudokuWidth()]);
    }

    /**
     * Verify the number of non-zero digits on the board.
     * @return True if the number of non-zero digits is equal to the number
     *              of fields on the board, otherwise false.
     */
    public boolean verify() {
        Set<Integer> fields = new HashSet<>();
        int nonZeroDigitCounter = 0;
        for (int j = 0; j < board.getSudokuWidth(); j++) {
            if (element.get(j).getValue() != 0) {
                fields.add(element.get(j).getValue());
                ++nonZeroDigitCounter;
            }
        }
        return fields.size() == nonZeroDigitCounter;
    }

    public ArrayList<SudokuField> getElement() {
        return new ArrayList<>(element);
    }

    public abstract void update();

    @Override
    public String toString() {
        return element.toString();
    }

    @Override
    public boolean equals(final Object o) {
        SudokuElement that = (SudokuElement) o;
        return element.equals(that.element)
                && board.equals(that.board);
    }

    @Override
    public int hashCode() {
        return Objects.hash(element, board);
    }
}
