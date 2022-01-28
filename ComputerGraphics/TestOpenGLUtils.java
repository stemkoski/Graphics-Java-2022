import graphics.core.*;

/**
 * Write a description of class TestOpenGLUtils here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class TestOpenGLUtils
{
    public static void main(String[] args)
    {
        String code = OpenGLUtils.readFile("shaders/center.vert");
        System.out.println(code);
    }
}
