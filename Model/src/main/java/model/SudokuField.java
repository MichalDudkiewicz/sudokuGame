package model;

import java.io.Serializable;
import java.util.Objects;

public class SudokuField implements Serializable, Cloneable, Comparable<SudokuField> {
    private Integer value = 0;

    public Integer getValue() {
        return value;
    }

    public void setValue(final Integer newValue) {
        value = newValue;
    }

    @Override
    public String toString() {
        return value.toString();
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        SudokuField that = (SudokuField) o;
        return value.equals(that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public int compareTo(SudokuField o) {
        if (o == null) {
            throw new NullPointerException("Comparing SudokuField to null");
        }
        return this.value.compareTo(o.value);
    }

    @Override
    public SudokuField clone() throws CloneNotSupportedException {
        return (SudokuField) super.clone();
    }
}
