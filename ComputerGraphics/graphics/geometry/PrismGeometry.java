package graphics.geometry;

import graphics.math.*;

/**
 * Make a prism!
 */
public class PrismGeometry extends SurfaceGeometry
{
   public PrismGeometry(int numberOfSides)
   {
       super(
           (u, v) ->
           {
               return new Vector( (float)Math.cos(u),
                                  v,
                                  (float)Math.sin(u) );
           },
           0, 2 * (float)Math.PI, numberOfSides + 1,
           -1, 1, 4 );
           
   }
}
