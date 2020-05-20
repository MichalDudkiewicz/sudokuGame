package view;

import javafx.util.converter.NumberStringConverter;

public class SudokuConverter extends NumberStringConverter {

    @Override
    public String toString(Number value) {
        if (value != null) {
            if (value.equals(0)) {
                return "";
            }
        }
        return super.toString(value);
    }

    @Override
    public Number fromString(String value) {
        if (value.equals("")) {
            return 0;
        }
        return super.fromString(value);
    }
}
