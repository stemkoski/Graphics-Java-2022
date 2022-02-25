package graphics.math;

/**
 * The Matrix class stores a grid of numbers, and defines operations on matrices.
 */
public class Matrix
{
    public double[][] values;
    public int rows;
    public int columns;

    public Matrix(int rows, int columns)
    {
        values = new double[rows][columns];
        this.rows = rows;
        this.columns = columns;
    }

    // requires rows x columns inputs (all doubles)
    // setValues(...v) is varargs : variable number of arguments.
    // can use with any number of arguments: setValues(1,2,3,4,5,6)
    // all the arguments are then packed into an array
    //   for use in the function.
    public void setValues(double ...newValues)
    {
        for (int i = 0; i < newValues.length; i++)
        {
            double v = newValues[i];
            int rowNum = i / columns;
            int columnNum = i % columns;
            values[rowNum][columnNum] = v;
        }
    }

    public Vector getRow(int rowNum)
    {
        // each row has one entry from each column of the matrix
        Vector v = new Vector(columns);
        for (int i = 0; i < columns; i++)
        {
            v.values[i] = this.values[rowNum][i];
        }
        return v;
    }

    public Vector getColumn(int columnNum)
    {   
        // each column has one entry from each row of the matrix
        Vector v = new Vector(rows);
        for (int i = 0; i < rows; i++)
        {
            v.values[i] = this.values[i][columnNum];
        }
        return v;
    }

    public String toString()
    {
        String s = "";
        for (int i = 0; i < rows; i++)
            s += getRow(i).toString() + "\n";
        return s;   
    }

    /**
     *  Multiply this matrix by a vector.
     */
    public Vector multiplyVector(Vector v)
    {
        Vector w = new Vector(rows);

        for (int i = 0; i < rows; i++)
            w.values[i] = Vector.dot( getRow(i), v );

        return w;
    }

    /**
     * Multiply this matrix times another matrix.
     * Calculates (this * other).
     * @param other the other matrix to multiply by.
     */
    public Matrix multiplyMatrix(Matrix other)
    {
        // create a new matrix to store values from calculation
        Matrix M = new Matrix(this.rows, other.columns);

        for (int rowNum = 0; rowNum < this.rows; rowNum++)
        {
            Vector row = this.getRow(rowNum);
            for (int columnNum = 0; columnNum < other.columns; columnNum++)
            {
                Vector column = other.getColumn(columnNum);
                double number = Vector.dot( row, column );
                M.values[rowNum][columnNum] = number;
            }
        }

        return M;
    }

    /**
     * Multiply two matrices and return a new matrix that is their product.
     *
     * @param A first matrix
     * @param B second matrix
     * @return the matrix product A * B
     */
    public static Matrix multiplyMatrices(Matrix A, Matrix B)
    {
        // create a new matrix to store values from calculation
        Matrix M = new Matrix(A.rows, B.columns);

        for (int rowNum = 0; rowNum < A.rows; rowNum++)
        {
            Vector row = A.getRow(rowNum);
            for (int columnNum = 0; columnNum < B.columns; columnNum++)
            {
                Vector column = B.getColumn(columnNum);
                double number = Vector.dot( row, column );
                M.values[rowNum][columnNum] = number;
            }
        }

        return M;
    }

    public boolean equals(Matrix other)
    {    
        boolean same = true;
        for (int i = 0; i < this.rows; i++)
        {
            for (int j = 0; j < this.columns; j++)
            {
                same = same && (this.values[i][j] == other.values[i][j]);
            }
        }
        return same;
    }

    // convert 2D array of values into a 1D array of (float) values
    public float[] flatten()
    {
        float[] array = new float[rows * columns];
        int index = 0;
        for (int columnNum = 0; columnNum < columns; columnNum++)
        {
            for (int rowNum = 0; rowNum < rows; rowNum++)
            {
                array[ index ] = (float)values[rowNum][columnNum];
                index++;
            }
        }
        return array;
    }

    // represents no translation, no rotation.
    public static Matrix makeIdentity()
    {
        Matrix M = new Matrix(4,4);
        M.setValues( 1, 0, 0, 0,
            0, 1, 0, 0,
            0, 0, 1, 0,
            0, 0, 0, 1  );
        return M;
    }

    public static Matrix makeTranslation(double dx, double dy, double dz)
    {
        Matrix M = new Matrix(4,4);
        M.setValues( 1, 0, 0, dx,
            0, 1, 0, dy,
            0, 0, 1, dz,
            0, 0, 0,  1  );
        return M;
    }

}
