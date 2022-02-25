package graphics.core;
import graphics.math.*;

import static org.lwjgl.opengl.GL40.*;

/**
 * Store and send uniform (constant) data to the GPU.
 */
public class Uniform<T>
{
    // "int" | "float" | "vec2" | "vec3" | "vec4"
    public String dataType;
    
    public T data;
    
    public int uniformRef;
    
    public Uniform(String dataType, T data)
    {
        this.dataType = dataType;
        this.data = data;
    }
    
    public void associateVariable(int programRef, String variableName)
    {
        uniformRef = glGetUniformLocation(programRef, variableName);
    }
    
    public void uploadData()
    {
        if (dataType.equals("int"))
            glUniform1i(uniformRef, (Integer)data);
        else if (dataType.equals("float"))
            glUniform1f(uniformRef, (Float)data);
        else if (dataType.equals("vec2"))
        {
            Float[] floatArray = (Float[])data;
            glUniform2f(uniformRef, floatArray[0], floatArray[1]);
        }
        else if (dataType.equals("vec3"))
        {
            Float[] floatArray = (Float[])data;
            glUniform3f(uniformRef, floatArray[0], floatArray[1], floatArray[2]);
        }
        else if (dataType.equals("vec4"))
        {
            Float[] floatArray = (Float[])data;
            glUniform4f(uniformRef, floatArray[0], floatArray[1], floatArray[2], floatArray[3]);
        }
        else if (dataType.equals("mat4"))
        {
            Matrix M = (Matrix)data; 
            glUniform4fv( uniformRef, M.flatten() );
        }
    }
}







