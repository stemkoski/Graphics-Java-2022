import graphics.core.*;
import graphics.geometry.*;
import graphics.material.*;

public class TestFramework extends Base
{
    public Object3D sceneRoot;
    public Renderer renderer;
    
    public void initialize()
    {
        sceneRoot = new Object3D();
        
        Geometry geo = new RectangleGeometry(0.5, 0.5);
        Material mat = new SurfaceMaterial();
        Mesh rectangle = new Mesh(geo, mat);
        sceneRoot.add( rectangle );
        
        renderer = new Renderer();
    }
    
    public void update()
    {
        renderer.render( sceneRoot );
    }
    
    public static void main(String[] args)
    {
        new TestFramework().run();
    }
}
