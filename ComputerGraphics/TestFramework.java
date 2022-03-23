import graphics.core.*;
import graphics.geometry.*;
import graphics.material.*;
import graphics.math.*;

public class TestFramework extends Base
{
    public Object3D sceneRoot;
    public Camera   camera;
    public Renderer renderer;
    
    public MovementRig rig;
    
    public void initialize()
    {
        sceneRoot = new Object3D();
        camera    = new Camera();
        
        Geometry geo = new RectangleGeometry(0.5, 0.5);
        Material mat = new SurfaceMaterial();
        Mesh rectangle = new Mesh(geo, mat);
        rectangle.setPosition( new Vector(0, 0, -2) );        
        sceneRoot.add( rectangle );
        
        Geometry geo2 = new RectangleGeometry(1, 0.75);
        Mesh rectangle2 = new Mesh(geo2, mat);
        rectangle2.setPosition( new Vector(0, 0, -3) );
        sceneRoot.add( rectangle2 );
        
        
        
        renderer = new Renderer();
        
        rig = new MovementRig(camera, input);
    }
    
    public void update()
    {
        renderer.render( sceneRoot, camera );
        
        rig.update(1.0 / 60.0);
    }
    
    public static void main(String[] args)
    {
        new TestFramework().run();
    }
}
