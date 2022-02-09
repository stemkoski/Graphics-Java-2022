package examples;

// store a pair of objects of ANY type T.
public class Pair<T>
{
    public T x;
    public T y;
    
    public Pair(T initX, T initY)
    {
        x = initX;
        y = initY;
    }
    
    public String toString()
    {
        return "( " + x + " , " + y + " )";
    }
}
