package graphics.material;

import static org.lwjgl.opengl.GL40.*;

/**
 * Render a shape using points (instead of lines or triangles)
 */
public class PointMaterial extends Material
{
    public PointMaterial()
    {
        // call constructor of the Material class;
        //  specify the shader programs to use.
        super("shaders/SurfaceMaterial.vert", "shaders/SurfaceMaterial.frag");

        float[] defaultColor = {1, 1, 1};
        addUniform("vec3", "color", defaultColor);

        // boolean variables in OpenGL are ints: 0 = false, 1 = true.
        addUniform("bool", "useVertexColor", 1);

        // make points large enough to see
        glPointSize(10); 
        // make the points round instead of square by default
        glEnable(GL_POINT_SMOOTH);
        
        // ALWAYS STATE WHAT DRAW MODE TO USE
        drawStyle = GL_POINTS;
    }
}
