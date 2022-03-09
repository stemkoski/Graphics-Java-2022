package graphics.core;
import graphics.geometry.*;
import graphics.material.*;

/**
 * Group together Geometry and Material objects;
 *  store a VAO that associates vertex buffers (from Geometry) 
 *     with shader variables (from Material)
 */
public class Mesh
{
   public Geometry geometry;
   public Material material;
   
   public int vaoRef;
   
   public Mesh(Geometry geo, Material mat)
   {
       geometry = geo;
       material = mat;
   }
}
