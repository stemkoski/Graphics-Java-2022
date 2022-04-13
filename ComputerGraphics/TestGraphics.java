import graphics.core.*;
import graphics.math.*;
import graphics.geometry.*;
import graphics.material.*;

public class TestGraphics extends Base
{
    public Object3D sceneRoot;
    public Camera camera;
    public Renderer renderer;
    
    public MovementRig rig;
    
    public void initialize()
    {
        sceneRoot = new Object3D();
        camera = new Camera();
        camera.setPosition( new Vector(0, 1, 3) );
        renderer = new Renderer();
        
        rig = new MovementRig(camera, input);
        
        Geometry geo = new BoxGeometry(1, 1, 1);
        Material mat = new TextureMaterial( new Texture("images/color-grid.png") );
        Mesh mesh = new Mesh(geo, mat);
        sceneRoot.add( mesh );
        
    }
    
    public void update()
    {
        renderer.render(sceneRoot, camera);
        
        rig.update(1.0 / 60.0);
    }
    
    public static void main(String[] args)
    {
        new TestGraphics().run();
    }
}
