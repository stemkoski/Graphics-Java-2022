package graphics.geometry;

import graphics.math.*;

public class PolygonGeometry extends Geometry
{
    public PolygonGeometry(int numberOfSides)
    {
        float baseAngle = 2 * (float)Math.PI / numberOfSides;
        
        // store position of each vertex around triangle
        Vector[] P = new Vector[numberOfSides];
        
        for (int i = 0; i < numberOfSides; i++)
        {
            float x = (float)Math.cos( i * baseAngle );
            float y = (float)Math.sin( i * baseAngle );
            float z = 0;
            P[i] = new Vector( x, y, z );
        }
        
        Vector[] trianglePoints = new Vector[numberOfSides * 3];
        Vector origin = new Vector(0,0,0);
        int triangleArrayIndex = 0;
        
        for (int pointArrayIndex = 0; pointArrayIndex < numberOfSides; pointArrayIndex++)
        {
            trianglePoints[triangleArrayIndex] = P[pointArrayIndex];
            triangleArrayIndex++;
            
            // when i is equal to last value, set it back to 0 with mod (%)
            trianglePoints[triangleArrayIndex] = P[(pointArrayIndex+1) % numberOfSides];
            triangleArrayIndex++;
            
            trianglePoints[triangleArrayIndex] = origin;
            triangleArrayIndex++;
        }
        
        float[] positionData = Vector.flattenArray(trianglePoints);
        
        addAttribute("vec3", "position", positionData);
        
        Vector[] triangleColors = new Vector[numberOfSides * 3];
        Vector C0 = new Vector(0.5f, 0, 1);
        Vector C1 = new Vector(0, 1, 1);
        Vector C2 = new Vector(1, 0, 0);
        
        triangleArrayIndex = 0;
        for (int i = 0; i < numberOfSides; i++)
        {
            triangleColors[triangleArrayIndex] = C0;
            triangleArrayIndex++;
            
            triangleColors[triangleArrayIndex] = C1;
            triangleArrayIndex++;
            
            triangleColors[triangleArrayIndex] = C2;
            triangleArrayIndex++;
        }
        
        float[] vertexColorData = Vector.flattenArray( triangleColors );
        addAttribute("vec3", "vertexColor", vertexColorData);
        
        Vector[] triangleUVs = new Vector[numberOfSides * 3];
        triangleArrayIndex = 0;
        Vector uvOrigin = new Vector(0.5f, 0.5f);
        
        for (int i = 0; i < numberOfSides; i++)
        {
            Vector uv1 = new Vector(P[i].values[0], P[i].values[1]);
            uv1.multiplyScalar(0.5f);
            uv1.addVector( uvOrigin );
            
            Vector uv2 = new Vector(P[(i+1)%numberOfSides].values[0], 
                                    P[(i+1)%numberOfSides].values[1]);
            uv2.multiplyScalar(0.5f);
            uv2.addVector( uvOrigin );
            
            triangleUVs[triangleArrayIndex] = uv1;
            triangleArrayIndex++;
            
            triangleUVs[triangleArrayIndex] = uv2;
            triangleArrayIndex++;
            
            triangleUVs[triangleArrayIndex] = uvOrigin;
            triangleArrayIndex++;
        }
        
        float[] vertexUVData = Vector.flattenArray(triangleUVs);
        addAttribute("vec2", "vertexUV", vertexUVData);
        
        
        vertexCount = numberOfSides * 3;
    }
}
