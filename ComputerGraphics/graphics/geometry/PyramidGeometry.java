package graphics.geometry;

import graphics.math.*;

/**
    Makes a pyramid
 */
public class PyramidGeometry extends SurfaceGeometry
{
    public PyramidGeometry(int numberOfSides)
    {
        super(
           (u, v) ->
           {
               return new Vector( Math.cos(u) * ( -0.5 * v + 0.5 ),
                                  v,
                                  Math.sin(u) * ( -0.5 * v + 0.5 ) );
           },
           0, 2 * Math.PI, numberOfSides + 1,
           -1, 1, 4 );
    }
}
