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

    /*
    public boolean equals(Matrix other)
    {
        
    }
    */
}
