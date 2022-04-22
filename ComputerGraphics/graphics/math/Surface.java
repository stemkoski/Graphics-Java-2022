package graphics.math;

/**
 * Store data to generate the vertices (and related data: positions, colors, ....)
 *    of a 3D surface.
 */
public class Surface
{
    // define a class interface to store the mathematical function.
    public interface Function
    {
        // function F(u,v) = (x,y,z)
        public Vector F(float u, float v);
    }

    // constructor stores function,
    //   used when generating vertex data
    public Function function;
    public Surface(Function function)
    {
        this.function = function;
    }

    public Vector[][] getVertexPositions(float uStart, float uEnd, int uNumPoints,
                                         float vStart, float vEnd, int vNumPoints)
    {
        // store the positions
        Vector[][] positions = new Vector[uNumPoints][vNumPoints];
        for (int uIndex = 0; uIndex < uNumPoints; uIndex++)
        {
            for (int vIndex = 0; vIndex < vNumPoints; vIndex++)
            {
                float u = uStart + ((uEnd - uStart)/(uNumPoints - 1)) * uIndex;
                float v = vStart + ((vEnd - vStart)/(vNumPoints - 1)) * vIndex;
                Vector vec = function.F(u,v);
                positions[uIndex][vIndex] = vec;
            }
        }
        return positions;
    }
    
    public Vector[][] getVertexUVs(int uNumPoints, int vNumPoints)
    {
        Vector[][] uvs = new Vector[uNumPoints][vNumPoints];
        for (int uIndex = 0; uIndex < uNumPoints; uIndex++)
        {
            for (int vIndex = 0; vIndex < vNumPoints; vIndex++)
            {
                float u = 1f - uIndex / (uNumPoints - 1f);
                float v = 1f - vIndex / (vNumPoints - 1f);
                Vector vec = new Vector(u, v);
                uvs[uIndex][vIndex] = vec;
            }
        }
        return uvs;
    }

}
