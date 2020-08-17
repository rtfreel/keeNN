package keenn.exceptions;

/**
 * MatrixDifferentSizeException is thrown in case when matrix size is unsuitable for
 * particular operation.
 * @author Tkachenko Roman
 */
public class MatrixDifferentSizeException extends Exception{
    public MatrixDifferentSizeException(String message){
        super(message);
    }
}