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
        
        Geometry geo = new SphereGeometry();
        Material mat = new TextureMaterial( new Texture("images/sky.jpg") );
        Mesh mesh = new Mesh(geo, mat);
        mesh.scale(100);
        sceneRoot.add( mesh );
        
        Geometry groundGeo = new PlaneGeometry();
        Material groundMat = new TextureMaterial( new Texture("images/dirt.jpg") );
        Mesh ground = new Mesh(groundGeo, groundMat);
        ground.scale( 100 );
        ground.translateGlobal( -50, 0, -50 );
        ground.rotateXLocal( Math.PI/2 );
        sceneRoot.add( ground );
        
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
