package graphics.geometry;
import graphics.math.*;

public class RectangleGeometry extends Geometry
{
    // purpose: define all data arrays and create attributes.
    
    public RectangleGeometry(float width, float height)
    {
        Vector P0 = new Vector( width/2, height/2, 0);
        Vector P1 = new Vector(-width/2, height/2, 0);
        Vector P2 = new Vector(-width/2, -height/2, 0);
        Vector P3 = new Vector( width/2, -height/2, 0);
         
        float[] positionData = Vector.flattenArray( P0,P1,P2, P0,P2,P3 );

        addAttribute("vec3", "position", positionData);
    
        Vector C0 = new Vector(1.00f, 0.00f, 0.00f);
        Vector C1 = new Vector(0.00f, 1.00f, 1.00f); 
        Vector C2 = new Vector(1.00f, 0.50f, 0.00f);
        Vector C3 = new Vector(0.75f, 0.75f, 1.00f);
        
        float[] colorData = Vector.flattenArray( C0,C1,C2, C0,C2,C3 );
        
        addAttribute("vec3", "vertexColor", colorData);
        
        Vector uv0 = new Vector(1,1);
        Vector uv1 = new Vector(0,1);
        Vector uv2 = new Vector(0,0);
        Vector uv3 = new Vector(1,0);
        
        float[] uvData = Vector.flattenArray( uv0,uv1,uv2, uv0,uv2,uv3 );
        
        addAttribute("vec2", "vertexUV", uvData);
        
        // ALWAYS STATE HOW MANY VERTICES NEED TO BE DRAWN
        vertexCount = 6;
    }
}