package graphics.geometry;
import graphics.math.*;

public class PlaneGeometry extends SurfaceGeometry
{
    public PlaneGeometry()
    {
        super( (u,v) -> { return new Vector(u,v,0); },
            0, 1, 10,
            0, 1, 10 );
            
    }
}
