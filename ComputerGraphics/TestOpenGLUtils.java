import graphics.core.*;

import static org.lwjgl.opengl.GL40.*;

/**
 * Write a description of class TestOpenGLUtils here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class TestOpenGLUtils extends Base
{
    int programRef;
    // data array
    int arrayRef;
    public void initialize()
    {
        /*
        String code = OpenGLUtils.readFile("shaders/center.vert");
        System.out.println(code);
        
        // test compiling shader
        int shaderRef = OpenGLUtils.initializeShader(code, GL_VERTEX_SHADER);
        System.out.println("Shader reference: " + shaderRef );
        
        String code2 = OpenGLUtils.readFile("shaders/yellow.frag");
        int shaderRef2 = OpenGLUtils.initializeShader(code2, GL_FRAGMENT_SHADER);
        System.out.println("Shader reference: " + shaderRef2 );
        */
        
        programRef = OpenGLUtils.initializeProgram(
             "shaders/center.vert", "shaders/yellow.frag");
        
        System.out.println("Program reference: " + programRef);
        
        arrayRef = glGenVertexArrays();
        glBindVertexArray(arrayRef);
    }
    
    public void update()
    {
        // declare GPU program to use
        glUseProgram(programRef);
        
        glPointSize(20);
        
        // draw all the points
        // parameters: 
        //    draw style (points/lines/triangles), starting array index, # vertices
        glDrawArrays(GL_POINTS, 0, 1);
    }
    
    public static void main(String[] args)
    {
        new TestOpenGLUtils().run();
    }
}
