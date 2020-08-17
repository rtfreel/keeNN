package keenn;

import keenn.exceptions.MatrixDifferentSizeException;

/**
 * Matrix stores numbers and is able to perform maths.
 * Numbers are float by default.
 * It would perform better using GPU, which will be added later.
 * @author Tkachenko Roman
 */
public class Matrix{

    private float[][] matrix;
    private int rows, columns;

    /**
     * Creates default Matrix object with 1 row and 1 column
     */
    public Matrix(){
        this.rows = 1;
        this.columns = 1;
        this.matrix = new float[this.rows][this.columns];
    }
    
    /**
     * Creates new Matrix object with specified amount of rows and columns
     * @param rows amount of rows in the matrix
     * @param columns amount of columns in the matrix
     */
    public Matrix(int rows, int columns){
        this.rows = rows;
        this.columns = columns;
        this.matrix = new float[rows][columns];
    }

    /**
     * Creates new Matrix object from existing array
     * @param matrix
     */
    public Matrix(float[][] matrix){
        if(matrix != null){
            int r = matrix.length;
            int column = 0;
            for(int row = 0; row < matrix.length; row++){
                if(matrix[row].length > column)
                    column = matrix[row].length;
            }
            createMatrix(r, column);
            for(int row = 0; row < this.rows; row++){
                for(int col = 0; col < this.columns; col++){
                    float val = 0.0f;
                    try{
                        val = matrix[row][col];
                    }catch(IndexOutOfBoundsException ioobe){}
                    this.matrix[row][col] = val;
                }
            }
        }
    }

    /**
     * Multiplies two matrices and returns new Matrix object with matrix as a result. 
     * Only works when amount of columns in first matrix equals to amount of rows in second and. 
     * Otherwise, returns default Matrix object.
     * @param a first matrix to multiply
     * @param b second matrix to multiply by
     * @return result of multiplication
     */
    public static Matrix product(Matrix a, Matrix b){
        Matrix result = null;
        try{
            if(a.getColumns() != b.getRows()){
                throw new MatrixDifferentSizeException("These matrices are not suitable for product operation");
            }
            result = new Matrix(a.getRows(), b.getColumns());
            for(int row = 0; row < result.getRows(); row++){
                for(int col = 0; col < result.getColumns(); col++){
                    float num = 0.0f;
                    for(int i = 0; i < a.getColumns(); i++){
                        num += a.getNum(row, i) * b.getNum(i, col);
                    }
                    result.setNum(row, col, num);
                }
            }
        }catch(MatrixDifferentSizeException mdse){
            mdse.printStackTrace();
        }
        return result;
    }

    /**
     * Adds a number to every element in the matrix
     * @param number
     */
    public void add(float number){
        for(int row = 0; row < this.rows; row++){
            for(int col = 0; col < this.columns; col++){
                this.matrix[row][col] += number;
            }
        }
    }

    /**
     * Adds another matrix to this matrix element-wise
     * @param matrix
     */
    public void add(Matrix matrix){
        try{
            if(sameSize(matrix)){
                for(int row = 0; row < this.rows; row++){
                    for(int col = 0; col < this.columns; col++){
                        this.matrix[row][col] += matrix.getNum(row, col);
                    }
                }
            }else{
                throw new MatrixDifferentSizeException("Connot add matrices with different sizes");
            }
        }catch(MatrixDifferentSizeException mdse){
            mdse.printStackTrace();
        }
    }

    /**
     * Multiplies every element in the matrix by the scalar
     * @param number
     */
    public void mult(float number){
        for(int row = 0; row < this.rows; row++){
            for(int col = 0; col < this.columns; col++){
                this.matrix[row][col] *= number;
            }
        }
    }

    /**
     * Multiplies matrix by another element-wise
     * @param matrix
     */
    public void mult(Matrix matrix){
        try{
            if(sameSize(matrix)){
                for(int row = 0; row < this.rows; row++){
                    for(int col = 0; col < this.columns; col++){
                        this.matrix[row][col] *= matrix.getNum(row, col);
                    }
                }
            }else{
                throw new MatrixDifferentSizeException("Connot add matrices with different sizes");
            }
        }catch(MatrixDifferentSizeException mdse){
            mdse.printStackTrace();
        }
    }

    /**
     * Fills matrix with random numbers between 0 and 1
     */
    public void randomize(){
        for(int row = 0; row < this.rows; row++){
            for(int col = 0; col < this.columns; col++){
                this.matrix[row][col] = (float)Math.random();
            }
        }
    }

    /**
     * Fills matrix with random numbers between -limit and limit
     * @param limit
     */
    public void randomize(float limit){
        randomize(-1 * limit, limit);
    }

    /**
     * Fills matrix with random numbers between limit1 and limit2
     * @param limit1 - lower limit
     * @param limit2 - upper limit
     */
    public void randomize(float limit1, float limit2){
        for(int row = 0; row < this.rows; row++){
            for(int col = 0; col < this.columns; col++){
                this.matrix[row][col] = ((float)Math.random() * (limit2 - limit1)) + limit1;
            }
        }
    }

    /**
     * Creates matrix of float numbers in the Matrix object with specified 
     * amount of rows and columns
     * @param rows amount of rows in the matrix
     * @param columns amount of columns in the matrix
     */
    public void createMatrix(int rows, int columns){
        this.rows = rows;
        this.columns = columns;
        this.matrix = new float[rows][columns];
    }

    /**
     * Returns true if both matrices have same sizes
     * @param matrix
     * @return
     */
    public boolean sameSize(Matrix matrix){
        if(this.rows == matrix.getRows() && this.columns == this.getColumns())
            return true;
        return false;
    }

    /**
     * Returns true if both matrices have the same sizes and numbers inside
     * @param matrix another matrix
     * @return equality of two matrices
     */
    public boolean equals(Matrix matrix){
        if(!sameSize(matrix))
            return false;
        for(int row = 0; row < this.rows; row++){
            for(int col = 0; col < this.columns; col++){
                if(this.matrix[row][col] != matrix.getNum(row, col))
                    return false;
            }
        }
        return true;
    }

    /**
     * Returns amount of rows in the matrix
     * @return amount of rows
     */
    public int getRows(){
        return this.rows;
    }

    /**
     * Returns amount of columns in the matrix
     * @return amount of columns
     */
    public int getColumns(){
        return this.columns;
    }

    /**
     * Returns string view of matrix in a single line
     * @return formatted string
     */
    public String singleLineView(){
        StringBuilder result = new StringBuilder();
        for(int row = 0; row < this.rows; row++){
            for(int col = 0; col < this.columns; col++){
                result.append(String.format("%.2f\t", this.matrix[row][col]));
            }
        }
        return result.toString();
    }

    /**
     * Returns string view of matrix in a few lines like table
     * @return formatted string
     */
    public String tableView(){
        StringBuilder result = new StringBuilder();
        for(int row = 0; row < this.rows; row++){
            for(int col = 0; col < this.columns; col++){
                result.append(String.format("%.9f\t|", this.matrix[row][col]));
            }
            result.append("\n");
        }
        return result.toString();
    }

    /**
     * Returns number from the specified position in the matrix
     * @param row
     * @param column
     * @return float number
     */
    public float getNum(int row, int column){
        float result = 0.0f;
        try{
            result = this.matrix[row][column];
        }catch(IndexOutOfBoundsException ioobe){
            ioobe.printStackTrace();
        }
        return result;
    }

    public float[][] toArray(){
        return this.matrix;
    }

    /**
     * Sets value to specified position in the Matrix
     * @param row
     * @param column
     * @param value
     * @return
     */
    public void setNum(int row, int column, float value){
        try{
            this.matrix[row][column] = value;
        }catch(IndexOutOfBoundsException ioobe){
            ioobe.printStackTrace();
        }
    }
}