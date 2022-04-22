package graphics.geometry;

import graphics.math.*;

public class SurfaceGeometry extends Geometry
{
    public SurfaceGeometry( Surface.Function F, 
        float uStart, float uEnd, int uNumPoints,
        float vStart, float vEnd, int vNumPoints  )
    {
        Surface surf = new Surface(F);
        
        Vector[][] positions = surf.getVertexPositions(uStart, uEnd, uNumPoints,
                                                       vStart, vEnd, vNumPoints  );
        
        Vector[][] uvs = surf.getVertexUVs(uNumPoints, vNumPoints);
        
        
        
        vertexCount = 3 * 2 * (uNumPoints - 1) * (vNumPoints - 1);
        
        Vector[] trianglePoints = new Vector[vertexCount];
        Vector[] triangleColors = new Vector[vertexCount];
        Vector[] triangleUVs    = new Vector[vertexCount];
        
        /*
        // colorful triangles!
        Vector C0 = new Vector(0.5, 0.75, 1); 
        Vector C1 = new Vector(0.5, 1, 0.5);
        Vector C2 = new Vector(0.75, 0.5, 1);
        Vector C3 = new Vector(0.85, 0.10, 0.25); // crimson!
        Vector C4 = new Vector(1, 0.5, 0);
        Vector C5 = new Vector(1, 1, 0);
        */
       
        // gray scale triangles
        Vector C0 = new Vector( 0.5f, 0.5f, 0.5f );
        Vector C1 = new Vector( 0.6f, 0.6f, 0.6f );
        Vector C2 = new Vector( 0.7f, 0.7f, 0.7f );
        Vector C3 = new Vector( 0.8f, 0.8f, 0.8f );
        Vector C4 = new Vector( 0.9f, 0.9f, 0.9f );
        Vector C5 = new Vector( 1.0f, 1.0f, 1.0f );
        
        int triangleIndex = 0;
        
        for (int uIndex = 0; uIndex < uNumPoints - 1; uIndex++)
        {
            for (int vIndex = 0; vIndex < vNumPoints - 1; vIndex++)
            {
                Vector P0 = positions[uIndex][vIndex];
                Vector P1 = positions[uIndex+1][vIndex];
                Vector P2 = positions[uIndex+1][vIndex+1];
                Vector P3 = positions[uIndex][vIndex+1];
                
                Vector uv0 = uvs[uIndex][vIndex];
                Vector uv1 = uvs[uIndex+1][vIndex];
                Vector uv2 = uvs[uIndex+1][vIndex+1];
                Vector uv3 = uvs[uIndex][vIndex+1];
                
                trianglePoints[triangleIndex] = P0;
                triangleColors[triangleIndex] = C0;
                triangleUVs[triangleIndex]    = uv0;
                triangleIndex++;
                trianglePoints[triangleIndex] = P1;
                triangleColors[triangleIndex] = C1;
                triangleUVs[triangleIndex]    = uv1;
                triangleIndex++;
                trianglePoints[triangleIndex] = P2;
                triangleColors[triangleIndex] = C2;
                triangleUVs[triangleIndex]    = uv2;
                triangleIndex++;
                trianglePoints[triangleIndex] = P0;
                triangleColors[triangleIndex] = C3;
                triangleUVs[triangleIndex]    = uv0;
                triangleIndex++;
                trianglePoints[triangleIndex] = P2;
                triangleColors[triangleIndex] = C4;
                triangleUVs[triangleIndex]    = uv2;
                triangleIndex++;
                trianglePoints[triangleIndex] = P3;
                triangleColors[triangleIndex] = C5;
                triangleUVs[triangleIndex]    = uv3;
                triangleIndex++;
            }
        }
        
        float[] vertexPositionData = Vector.flattenArray(trianglePoints);
        addAttribute("vec3", "position", vertexPositionData);
        
        float[] vertexColorData = Vector.flattenArray(triangleColors);
        addAttribute("vec3", "vertexColor", vertexColorData);
        
        float[] vertexUVData = Vector.flattenArray(triangleUVs);
        addAttribute("vec2", "vertexUV", vertexUVData);
    }
}
