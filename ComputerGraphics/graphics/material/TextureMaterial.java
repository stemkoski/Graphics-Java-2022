package graphics.material;

import graphics.core.*;

import static org.lwjgl.opengl.GL40.*;

public class TextureMaterial extends Material
{
    public TextureMaterial(Texture texture)
    {
        // call constructor of the Material class;
        //  specify the shader programs to use.
        super("shaders/TextureMaterial.vert", "shaders/TextureMaterial.frag");
        
        float[] defaultColor = {1, 1, 1};
        addUniform("vec3", "color", defaultColor);
        
        // boolean variables in OpenGL are ints: 0 = false, 1 = true.
        addUniform("bool", "useVertexColor", 0);
    
        // data required: texture reference, and texture unit number (intermediary)
        float[] textureData = { texture.textureRef, 1 };
        // sampler2D stores information about texture and pixel sampling
        addUniform("sampler2D", "tex", textureData );
        
        // ALWAYS STATE WHAT DRAW MODE TO USE
        drawStyle = GL_TRIANGLES;
    }
}