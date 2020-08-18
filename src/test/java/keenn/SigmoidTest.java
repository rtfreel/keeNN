package keenn;

import org.junit.Test;

import keenn.exceptions.MatrixDifferentSizeException;

import static org.junit.Assert.*;

public class SigmoidTest{
    @Test public void testSigmoid(){
        keenn.functions.Sigmoid function = new keenn.functions.Sigmoid();
        for(int i = 0; i < 1000; i++){
            float result = function.calc(i);
            assertTrue("result should be between 0 and 1 anyway", result >= 0 && result <= 1);
        }
    }
}