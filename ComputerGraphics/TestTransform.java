import graphics.core.*;
import graphics.math.*;
import static org.lwjgl.opengl.GL40.*;

/**
 * Test Matrix multiplication in shaders to move vertices.
 */
public class TestTransform extends Base
{
    public int programRef;
    public int arrayRef;
    public Uniform<Matrix> transformUniform;

    public void initialize()
    {
        programRef = OpenGLUtils.initializeProgram(
            "shaders/transform.vert",
            "shaders/yellow.frag"
        );

        arrayRef = glGenVertexArrays();
        glBindVertexArray(arrayRef);

        float[] positionData = {
                0.0f,  0.8f, 0.0f,
                -0.5f, -0.5f, 0.0f,
                0.5f, -0.5f, 0.0f
            };

        Attribute positionAttribute = new Attribute("vec3", positionData);
        positionAttribute.associateVariable(programRef, "pos");

        Matrix M = Matrix.makeTranslation(0, 0, 0);
        
        transformUniform = new Uniform<Matrix>(
            "mat4", M );
        transformUniform.associateVariable(
            programRef, "transform");

    }

    public void update()
    {
        glUseProgram( programRef );
        
        glBindVertexArray(arrayRef);
        
        transformUniform.uploadData();
        
        glDrawArrays( GL_TRIANGLES, 0, 3 );
    }
    
    public static void main(String[] args)
    {
        new TestTransform().run();
    }
    
    
    
    
    
    
}
