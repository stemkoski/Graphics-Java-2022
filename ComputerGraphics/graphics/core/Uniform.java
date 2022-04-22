package graphics.core;
import graphics.math.*;
import java.util.Arrays;

import static org.lwjgl.opengl.GL40.*;
 
/**
 * Store and send uniform (constant) data to the GPU.
 */
public class Uniform<T>
{
    // "int" | "float" | "vec2" | "vec3" | "vec4" | "mat4" | "bool" | "sampler2D"
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
        else if (dataType.equals("bool"))
            glUniform1i(uniformRef, (Integer)data);
        else if (dataType.equals("float"))
            glUniform1f(uniformRef, (float)data);
        else if (dataType.equals("vec2"))
        {
            float[] floatArray = (float[])data;
            glUniform2f(uniformRef, floatArray[0], floatArray[1]);
        }
        else if (dataType.equals("vec3"))
        {
            float[] floatArray = (float[])data;
            glUniform3f(uniformRef, floatArray[0], floatArray[1], floatArray[2]);
        }
        else if (dataType.equals("vec4"))
        {
            float[] floatArray = (float[])data;
            glUniform4f(uniformRef, floatArray[0], floatArray[1], floatArray[2], floatArray[3]);
        }
        else if (dataType.equals("mat4"))
        {
            Matrix M = (Matrix)data;
            // System.out.println("Uploading data...");
            // System.out.println( Arrays.toString( M.flatten() ) );
            glUniformMatrix4fv( uniformRef, false, M.flatten() ); 
        }
        else if (dataType.equals("sampler2D"))
        {
            int[] values = (int[])(data);
            int textureRef  = values[0];
            int textureUnit = values[1];
            // activate texture unit
            glActiveTexture( GL_TEXTURE0 + textureUnit );
            // bind texture reference to texture unit
            glBindTexture( GL_TEXTURE_2D, textureRef );
            // upload texture unit number to uniform variable in shader
            glUniform1i( uniformRef, textureUnit );
        }
    }
}







