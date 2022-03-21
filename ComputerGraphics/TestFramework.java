import graphics.core.*;
import graphics.geometry.*;
import graphics.material.*;
import graphics.math.*;

public class TestFramework extends Base
{
    public Object3D sceneRoot;
    public Camera   camera;
    public Renderer renderer;
    
    public void initialize()
    {
        sceneRoot = new Object3D();
        camera    = new Camera();
        
        Geometry geo = new RectangleGeometry(0.5, 0.5);
        Material mat = new SurfaceMaterial();
        Mesh rectangle = new Mesh(geo, mat);
        
        rectangle.setPosition( new Vector(0, 0, 1) );
        
        sceneRoot.add( rectangle );
        
        renderer = new Renderer();
    }
    
    public void update()
    {
        renderer.render( sceneRoot, camera );
    }
    
    public static void main(String[] args)
    {
        new TestFramework().run();
    }
}
