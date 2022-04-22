package graphics.geometry;

import graphics.math.*;

/**
 * 
 */
public class SphereGeometry extends SurfaceGeometry
{
    public SphereGeometry()
    {
        super(
            (u, v) -> 
            {
                return new Vector((float)Math.cos(u) * (float)Math.sin(v),
                                  (float)Math.cos(v),
                                  (float)Math.sin(u) * (float)Math.sin(v));
            },
            0, 2 * (float)Math.PI, 64,
            0, (float)Math.PI, 32  );
    }
}
