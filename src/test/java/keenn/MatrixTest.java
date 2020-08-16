package keenn;

import org.junit.Test;
import static org.junit.Assert.*;

public class MatrixTest{
    @Test public void testConstructor(){
        float[][] array = {{0.1f, 0.5f}, {0.2f, 0.6f},
                            {0.3f, 0.7f}, {0.4f, 0.8f},
                            {0.5f, 0.1f}, {0.6f, 0.2f}};
        Matrix matrix = new Matrix(array);
        assertTrue("matrix should have as many rows as array has", matrix.getRows() == array.length);
        assertTrue("matrix should have as many columns as array has", matrix.getColumns() == array[0].length);
        for(int row = 0; row < array.length; row++){
            for(int col = 0; col < array[0].length; col++){
                assertTrue("there sould be equal numbers on the position (" + row + ", " + col + ")", 
                    matrix.getNum(row, col) == array[row][col]);
            }
        }
    }
}