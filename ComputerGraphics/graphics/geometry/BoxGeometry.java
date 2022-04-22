package graphics.geometry;

import graphics.math.*;

/**
 * It's a BOX
 */
public class BoxGeometry extends Geometry
{
    // set up attributes (position and vertex colors) and vertex count
    public BoxGeometry(float width, float height, float depth)
    {
        Vector P0 = new Vector(-width/2,  height/2, -depth/2);
        Vector P1 = new Vector( width/2,  height/2, -depth/2);
        Vector P2 = new Vector(-width/2,  height/2,  depth/2);
        Vector P3 = new Vector( width/2,  height/2,  depth/2);
        Vector P4 = new Vector(-width/2, -height/2, -depth/2);
        Vector P5 = new Vector( width/2, -height/2, -depth/2);
        Vector P6 = new Vector(-width/2, -height/2,  depth/2);
        Vector P7 = new Vector( width/2, -height/2,  depth/2);
        
        float[] positionData = Vector.flattenArray( 
                                    P0,P1,P2, P1,P3,P2, // TOP SIDE
                                    P2,P3,P6, P3,P7,P6, // FRONT
                                    P6,P7,P4, P7,P5,P4, // BOTTOM
                                    P0,P2,P4, P2,P6,P4, // LEFT
                                    P3,P1,P7, P1,P5,P7, // RIGHT
                                    P1,P0,P5, P0,P4,P5  // BACK
                                    );
        
        addAttribute("vec3", "position", positionData);
        
        Vector C0 = new Vector(1.00f, 0.00f, 0.00f);
        Vector C1 = new Vector(1.00f, 0.50f, 0.00f);
        Vector C2 = new Vector(0.50f, 1.00f, 0.50f);
        Vector C3 = new Vector(0.75f, 0.75f, 1.00f);
        Vector C4 = new Vector(1.00f, 0.75f, 0.75f);
        Vector C5 = new Vector(0.80f, 0.60f, 1.00f);
        
        float[] colorData = Vector.flattenArray( C0,C0,C0,C0,C0,C0,
                                                 C1,C1,C1,C1,C1,C1,
                                                 C2,C2,C2,C2,C2,C2,
                                                 C3,C3,C3,C3,C3,C3,
                                                 C4,C4,C4,C4,C4,C4,
                                                 C5,C5,C5,C5,C5,C5 );
                                    
        addAttribute("vec3", "vertexColor", colorData);
        
        Vector uv0 = new Vector(1,1);
        Vector uv1 = new Vector(0,1);
        Vector uv2 = new Vector(0,0);
        Vector uv3 = new Vector(1,0);
        
        float[] uvData = Vector.flattenArray(   uv1,uv0,uv2, uv0,uv3,uv2,
                                                uv1,uv0,uv2, uv0,uv3,uv2,
                                                uv1,uv0,uv2, uv0,uv3,uv2,
                                                uv1,uv0,uv2, uv0,uv3,uv2,
                                                uv1,uv0,uv2, uv0,uv3,uv2,
                                                uv1,uv0,uv2, uv0,uv3,uv2   );
        
        addAttribute("vec2", "vertexUV", uvData);
        
        vertexCount = 36;
    }
}
