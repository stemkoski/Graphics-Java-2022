package graphics.material;

import graphics.core.*;

import static org.lwjgl.opengl.GL40.*;

public class WaterMaterial extends Material
{
    public WaterMaterial()
    {
        // call constructor of the Material class;
        //  specify the shader programs to use.
        super("shaders/Water.vert", "shaders/Water.frag");
        
        addUniform("float", "time", 0.0f ); 
        
        addUniform("float", "noiseLevel", 1.0f);
        
        addUniform("float", "noiseSpeed", 1.0f);
         
        // repeat image texture
        float[] defaultRepeat = { 1f, 1f };
        addUniform("vec2", "repeatUV", defaultRepeat);
        
        
        
        // ALWAYS STATE WHAT DRAW MODE TO USE
        drawStyle = GL_TRIANGLES;
    }
}