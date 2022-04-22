package graphics.material;

import graphics.core.*;

import static org.lwjgl.opengl.GL40.*;

public class DistortMaterial extends Material
{
    public DistortMaterial(Texture texture, Texture noiseTexture)
    {
        // call constructor of the Material class;
        //  specify the shader programs to use.
        super("shaders/DistortMaterial.vert", "shaders/DistortMaterial.frag");
        
        float[] defaultColor = {1, 1, 1};
        addUniform("vec3", "color", defaultColor);
        
        // boolean variables in OpenGL are ints: 0 = false, 1 = true.
        addUniform("bool", "useVertexColor", 0);
    
        // data required: texture reference, and texture unit number (intermediary)
        int[] textureData = { texture.textureRef, 1 };
        // sampler2D stores information about texture and pixel sampling
        addUniform("sampler2D", "tex", textureData );
        
        int[] noiseTextureData = { noiseTexture.textureRef, 2 };
        addUniform("sampler2D", "noise", noiseTextureData );
        
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