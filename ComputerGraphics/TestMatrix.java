import graphics.math.*;

/**
 * Test methods from the Matrix class.
 */
public class TestMatrix
{
    public static void main(String[] args)
    {
        Matrix m = new Matrix(2,4);
        System.out.println( m );
        
        m.setValues(1, 2, 3, 4,
                    8, 7, 6, 5);
        System.out.println( m );
        
        Matrix n = new Matrix(2,2);
        n.setValues(1, 2,
                    3, 4 );
        Vector v = new Vector(5, 6);
        
        Vector w = n.multiplyVector(v);
        
        System.out.println( w.equals( new Vector(17, 39)) );
        
    }
}
