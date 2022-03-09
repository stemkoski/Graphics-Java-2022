package graphics.material;
import java.util.HashMap;
import graphics.core.*;

/**
 * Store appearance-related data:
 *  - shader program reference
 *  - collection of uniform objects needed by shader program
 */
public class Material
{
    public int programRef;
    
    // GL_TRIANGLES, GL_POINTS, GL_LINES, ...
    public int drawStyle;
    
    public HashMap<String, Uniform> uniforms;
    
    public Material(String vertexShaderFileName, String fragmentShaderFileName)
    {
        programRef = OpenGLUtils.initializeProgram(
                        vertexShaderFileName, fragmentShaderFileName );
                        
        uniforms = new HashMap<String, Uniform>();
    }
    
    public void addUniform(String dataType, String variableName, Object data)
    {
        Uniform unif = new Uniform(dataType, data);

        uniforms.put(variableName, unif);
    }








}