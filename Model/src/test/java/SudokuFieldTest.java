/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import model.SudokuField;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotSame;

public class SudokuFieldTest {

    @Test
    void getSetFieldValueTest() {
        SudokuField field1 = new SudokuField();
        assertEquals(0, field1.getValue());
        field1.setValue(7);
        assertEquals(7, field1.getValue());
    }

    @Test
    void toStringTest() {
        SudokuField field1 = new SudokuField();
        field1.setValue(7);
        assertEquals("7", field1.toString());
    }

    @Test
    void hashCodeTest() {
        SudokuField field1 = new SudokuField();
        field1.setValue(7);
        SudokuField field2 = new SudokuField();
        field2.setValue(9);
        assertEquals(Objects.hash(field1.getValue()), field1.hashCode());
        assertNotEquals(field1.hashCode(), field2.hashCode());
    }

    @Test
    void equalsTest() {
        SudokuField field1 = new SudokuField();
        field1.setValue(7);
        SudokuField field2 = new SudokuField();
        field2.setValue(7);
        SudokuField field3 = new SudokuField();
        field3.setValue(8);
        assertEquals(field1, field2);
        assertEquals(field1, field1);
        assertNotEquals(field1, null);
        assertNotEquals(field1, 3);
        assertNotEquals(field1, field3);
    }

    @Test
    void compareToTest() {
        SudokuField field1 = new SudokuField();
        field1.setValue(7);
        SudokuField field2 = new SudokuField();
        field2.setValue(7);
        SudokuField field3 = new SudokuField();
        field3.setValue(8);
        SudokuField field4 = new SudokuField();
        field4.setValue(5);
        assertEquals(field1.compareTo(field2), 0);
        assertEquals(field1.compareTo(field3), -1);
        assertEquals(field1.compareTo(field4), 1);
        assertEquals(field2.compareTo(field1), 0);
        assertEquals(field3.compareTo(field1), 1);
        assertEquals(field4.compareTo(field1), -1);
        Assertions.assertThrows(NullPointerException.class, () -> System.out.println(field4.compareTo(null)));
    }

    @Test
    void cloneTest() throws CloneNotSupportedException {
        SudokuField field1 = new SudokuField();
        field1.setValue(7);
        SudokuField field2 = field1.clone();
        assertNotSame(field1, field2);
        assertEquals(field1, field2);
        field1.setValue(5);
        assertNotSame(field1, field2);
        assertNotEquals(field1, field2);
    }
}
