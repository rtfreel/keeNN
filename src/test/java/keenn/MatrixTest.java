package keenn;

import org.junit.Test;

import keenn.exceptions.MatrixDifferentSizeException;

import static org.junit.Assert.*;

public class MatrixTest{

    float[][][] arrays = {
        {
            {0.1f, 0.5f}, 
            {0.2f, 0.6f},
            {0.3f, 0.7f}
        },

        {
            {0.4f, 0.8f},
            {0.5f, 0.1f}, 
            {0.6f, 0.2f}
        },
        
        {
            {1.0f, 5.0f, 2.0f},
            {3.0f, 7.0f, 4.0f}
        },

        {
            {8.0f, 5.0f, 1.0f},
            {6.0f, 2.0f, 1.0f}
        }
    };
    float[][][] expectedProduct = {
        {
            {(0.1f * 1f) + (0.5f * 3f), (0.1f * 5f) + (0.5f * 7f), (0.1f * 2f) + (0.5f * 4f)},
            {(0.2f * 1f) + (0.6f * 3f), (0.2f * 5f) + (0.6f * 7f), (0.2f * 2f) + (0.6f * 4f)},
            {(0.3f * 1f) + (0.7f * 3f), (0.3f * 5f) + (0.7f * 7f), (0.3f * 2f) + (0.7f * 4f)}
        },
        
        {
            {(0.4f * 8f) + (0.8f * 6f), (0.4f * 5f) + (0.8f * 2f), (0.4f * 1f) + (0.8f * 1f)},
            {(0.5f * 8f) + (0.1f * 6f), (0.5f * 5f) + (0.1f * 2f), (0.5f * 1f) + (0.1f * 1f)},
            {(0.6f * 8f) + (0.2f * 6f), (0.6f * 5f) + (0.2f * 2f), (0.6f * 1f) + (0.2f * 1f)}
        }
    };

    @Test public void testConstructor(){
        Matrix matrix = new Matrix(arrays[0]);
        assertTrue("matrix should have as many rows as array has", matrix.getRows() == arrays[0].length);
        assertTrue("matrix should have as many columns as array has", matrix.getColumns() == arrays[0][0].length);
        for(int row = 0; row < arrays[0].length; row++){
            for(int col = 0; col < arrays[0][0].length; col++){
                assertTrue("there sould be equal numbers on the position (" + row + ", " + col + ")", 
                    matrix.getVal(row, col) == arrays[0][row][col]);
            }
        }
    }

    @Test public void testMatrixEquals(){
        float[][] arr = {
                            {1.0f, 2.0f, 3.0f},
                            {1.0f, 2.0f, 3.0f}
                        };         
        Matrix m1 = new Matrix(arr);
        Matrix m2 = new Matrix(2, 3);
        for(int row = 0; row < m2.getRows(); row++){
            for(int col = 0; col < m2.getColumns(); col++){
                m2.setVal(row, col, 1.0f + col);
            }
        }
        assertTrue("matrices should have equal sizes", m1.sameSize(m2));
        assertTrue("matrices should be equal", m1.equals(m2));
    }

    @Test public void testMatrixProduct(){
        Matrix m1 = new Matrix(arrays[0]);
        Matrix m2 = new Matrix(arrays[2]);
        Matrix exp = new Matrix(expectedProduct[0]);
        Matrix res = Matrix.product(m1, m2);
        assertTrue("first product should be the same as expected", res.equals(exp));
        m1 = new Matrix(arrays[1]);
        m2 = new Matrix(arrays[3]);
        exp = new Matrix(expectedProduct[1]);
        res = Matrix.product(m1, m2);
        assertTrue("second product should be the same as expected", res.equals(exp));
    }

    @Test public void testMatrixScalarOperations(){
        float scalar = 2;
        Matrix initialMatrix = new Matrix(arrays[0]);
        Matrix testMatrix = new Matrix(arrays[0]);
        testMatrix.add(scalar);
        for(int row = 0; row < testMatrix.getRows(); row++){
            for(int col = 0; col < testMatrix.getColumns(); col++){
                assertTrue("every element should be increased by 2", 
                    Math.round(testMatrix.getVal(row, col) - initialMatrix.getVal(row, col)) == scalar);
            }
        }
        testMatrix = new Matrix(arrays[0]);
        testMatrix.mult(scalar);
        for(int row = 0; row < testMatrix.getRows(); row++){
            for(int col = 0; col < testMatrix.getColumns(); col++){
                assertTrue("every element should be in 2 times bigger", 
                    testMatrix.getVal(row, col) / initialMatrix.getVal(row, col) == scalar);
            }
        }
    } 

    @Test public void testTwoMatricesOperations(){
        //check matrices element-wise addition
        Matrix m1 = new Matrix(arrays[0]);
        Matrix m2 = new Matrix(arrays[1]);
        try{
            m1.add(m2);
            for(int row = 0; row < m1.getRows(); row++){
                for(int col = 0; col < m1.getColumns(); col++){
                    assertTrue("every element should be sum of two elements of initial arrays", 
                        m1.getVal(row, col) == arrays[0][row][col] + arrays[1][row][col]);
                }
            }
        }catch(MatrixDifferentSizeException mdse){
            fail("got an unexpected MatrixDifferentSizeException");
        }

        //check matrices element-wise multiplication
        m1 = new Matrix(arrays[0]);
        m2 = new Matrix(arrays[1]);
        try{
            m1.mult(m2);
            for(int row = 0; row < m1.getRows(); row++){
                for(int col = 0; col < m1.getColumns(); col++){
                    assertTrue("every element should be product of two elements of initial arrays", 
                        m1.getVal(row, col) == arrays[0][row][col] * arrays[1][row][col]);
                }
            }
        }catch(MatrixDifferentSizeException mdse){
            fail("got an unexpected MatrixDifferentSizeException");
        }

        //check getting MatrixDifferentSizeException
        m1 = new Matrix(arrays[0]);
        m2 = new Matrix(arrays[2]);
        boolean gotException = false;
        try{
            m1.add(m2);
        }catch(MatrixDifferentSizeException mdse){
            gotException = true;
        }
        assertTrue("should throw MatrixDifferentsizeException", gotException);
        
        gotException = false;
        try{
            m1.mult(m2);
        }catch(MatrixDifferentSizeException mdse){
            gotException = true;
        }
        assertTrue("should throw MatrixDifferentsizeException", gotException);

    }
}