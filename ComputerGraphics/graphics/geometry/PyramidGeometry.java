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
               return new Vector( (float)Math.cos(u) * ( -0.5f * v + 0.5f ),
                                  v,
                                  (float)Math.sin(u) * ( -0.5f * v + 0.5f ) );
           },
           0, 2 * (float)Math.PI, numberOfSides + 1,
           -1, 1, 4 );
    }
}
