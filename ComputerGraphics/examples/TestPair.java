package examples;


/**
 * Write a description of class TestPair here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class TestPair
{
    public static void main(String[] args)
    {
        Pair<String> name = new Pair<String>("Lee", "Stemkoski");
        System.out.println(name);
        
        Pair<Integer> numbers = new Pair<Integer>( 42, 337 );
        System.out.println(numbers);
        
    }
}
