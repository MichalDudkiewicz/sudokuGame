package model;

public interface SudokuSolver {

    boolean solve(SudokuBoard board);

    @Override
    String toString();

    @Override
    boolean equals(final Object o);

    @Override
    int hashCode();
}
