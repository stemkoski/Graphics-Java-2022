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
               return new Vector( Math.cos(u),
                                  v,
                                  Math.sin(u) );
           },
           0, 2 * Math.PI, numberOfSides + 1,
           -1, 1, 4 );
           
   }
}
