package mvcsquares;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Extended tests for MagicSquareModel class.
 * This includes various cases to ensure robustness in testing magic square functionality.
 * 
 * @author Matthew Washburn
 * @version Fall 2024
 */
public class ExtendedTestsOfMagicSquareModel {
    private MagicSquareModel myModel;

    /** Create a blank 3x3 magic square before each test case */
    @BeforeEach
    public void setup() {
        myModel = new MagicSquareModel(3);
    }

    /** Test for successful interface implementation */
    @Test
    public void testInterfaces() {
        assertTrue(myModel instanceof SquareModel);
    }

    /** Ensure size is initialized correctly */
    @Test
    public void testSize() {
        assertEquals(3, myModel.getSize());

        myModel.setSize(4);
        assertEquals(4, myModel.getSize());
    }

    /** Test the setValueAt() method */
    @Test
    public void testSetter() {
        myModel.setValueAt("4", 1, 1);
        assertEquals("4", myModel.getValueAt(1, 1));
    }

    /** Check the isMagic() and isNormalMagic() methods on a magic non-normal square */
    @Test
    public void testMagicNotNormal() {
        myModel.setValueAt("1", 0, 0);
        myModel.setValueAt("1", 0, 1);
        myModel.setValueAt("1", 0, 2);
        myModel.setValueAt("1", 1, 0);
        myModel.setValueAt("1", 1, 1);
        myModel.setValueAt("1", 1, 2);
        myModel.setValueAt("1", 2, 0);
        myModel.setValueAt("1", 2, 1);
        myModel.setValueAt("1", 2, 2);

        assertTrue(myModel.isMagic());
        assertFalse(myModel.isNormalMagic());
    }

    /** Check the isMagic() and isNormalMagic() methods on a magic normal square */
    @Test
    public void testNormalMagic() {
        myModel.setValueAt("2", 0, 0);
        myModel.setValueAt("7", 0, 1);
        myModel.setValueAt("6", 0, 2);
        myModel.setValueAt("9", 1, 0);
        myModel.setValueAt("5", 1, 1);
        myModel.setValueAt("1", 1, 2);
        myModel.setValueAt("4", 2, 0);
        myModel.setValueAt("3", 2, 1);
        myModel.setValueAt("8", 2, 2);

        assertTrue(myModel.isMagic());
        assertTrue(myModel.isNormalMagic());
    }

    /** Test a square that isn't magic */
    @Test
    public void testNotMagic() {
        myModel.setValueAt("1", 0, 0);
        myModel.setValueAt("2", 0, 1);
        myModel.setValueAt("1", 0, 2);
        myModel.setValueAt("1", 1, 0);
        myModel.setValueAt("1", 1, 1);
        myModel.setValueAt("1", 1, 2);
        myModel.setValueAt("1", 2, 0);
        myModel.setValueAt("1", 2, 1);
        myModel.setValueAt("1", 2, 2);

        assertFalse(myModel.isMagic());
        assertFalse(myModel.isNormalMagic());
    }

    /** Test additional cases for isMagic() and isNormalMagic() */
    @Test
    public void testVariousMagicSquares() {
        // 3x3 Normal Magic Square
        Integer[][] normalMagicSquare = {
            {8, 1, 6},
            {3, 5, 7},
            {4, 9, 2}
        };
        setSquare(normalMagicSquare);
        assertTrue(myModel.isMagic(), "Expected the square to be magic.");
        assertTrue(myModel.isNormalMagic(), "Expected the square to be normal magic.");

        // 3x3 Non-Magic Square
        Integer[][] nonMagicSquare = {
            {1, 2, 3},
            {4, 5, 6},
            {7, 8, 9}
        };
        setSquare(nonMagicSquare);
        assertFalse(myModel.isMagic(), "Expected the square to not be magic.");
        assertFalse(myModel.isNormalMagic(), "Expected the square to not be normal magic.");

        // 4x4 Normal Magic Square
        myModel = new MagicSquareModel(4);
        Integer[][] normalMagicSquare4x4 = {
            {16, 2, 3, 13},
            {5, 11, 10, 8},
            {9, 7, 6, 12},
            {4, 14, 15, 1}
        };
        setSquare(normalMagicSquare4x4);
        assertTrue(myModel.isMagic(), "Expected the 4x4 square to be magic.");
        assertTrue(myModel.isNormalMagic(), "Expected the 4x4 square to be normal magic.");

        // 4x4 Non-Magic Square with duplicate numbers
        Integer[][] nonNormalMagicSquare4x4 = {
            {1, 1, 1, 1},
            {2, 2, 2, 2},
            {3, 3, 3, 3},
            {4, 4, 4, 4}
        };
        setSquare(nonNormalMagicSquare4x4);
        assertFalse(myModel.isMagic(), "Expected the 4x4 square to not be magic.");
        assertFalse(myModel.isNormalMagic(), "Expected the 4x4 square to not be normal magic.");

        // 4x4 Incomplete Magic Square
        Integer[][] incompleteMagicSquare4x4 = {
            {1, 2, 3, 4},
            {5, 6, 7, 8},
            {9, 10, 11, null},
            {null, null, null, null}
        };
        setSquare(incompleteMagicSquare4x4);
        assertFalse(myModel.isMagic(), "Expected the 4x4 square to not be magic due to nulls.");
        assertFalse(myModel.isNormalMagic(), "Expected the 4x4 square to not be normal magic due to nulls.");
    }

    /** Test that an empty square is handled correctly */
    @Test
    public void testEmptySquare() {
        for (int i = 0; i < myModel.getSize(); i++) {
            for (int j = 0; j < myModel.getSize(); j++) {
                myModel.setValueAt("", i, j); // Set all values to empty
            }
        }
        assertFalse(myModel.isMagic(), "Expected the empty square to not be magic.");
        assertFalse(myModel.isNormalMagic(), "Expected the empty square to not be normal magic.");
    }

    /** Test exception handling for illegal size */
    @Test
    public void testIllegalSize() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            myModel.setSize(0); // Set an illegal size
        });
        assertEquals("Size can't be negative or zero!", exception.getMessage());
    }

    /** Test that clear() method works correctly */
    @Test
    public void testClear() {
        myModel.setValueAt("5", 1, 1);
        myModel.clear();
        assertEquals("", myModel.getValueAt(1, 1)); // Check if it cleared correctly
    }

    private void setSquare(Integer[][] square) {
        for (int i = 0; i < square.length; i++) {
            for (int j = 0; j < square[i].length; j++) {
                if (square[i][j] != null) {
                    myModel.setValueAt(square[i][j].toString(), i, j);
                } else {
                    myModel.setValueAt("", i, j); // Represent null with an empty string
                }
            }
        }
    }
}
