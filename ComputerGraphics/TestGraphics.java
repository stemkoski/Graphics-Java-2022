import graphics.core.*;
import graphics.math.*;
import graphics.geometry.*;
import graphics.material.*;

public class TestGraphics extends Base
{
    public Object3D sceneRoot;
    public Camera camera;
    public Renderer renderer;
    
    public Material rectMat;
    
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
        mat.uniforms.get("color").data = new float[] {0.5f, 0.5f, 0.5f};
        Mesh sky = new Mesh(geo, mat);
        sky.scale(100);
        sceneRoot.add( sky );
        
        Geometry groundGeo = new PlaneGeometry();
        Material groundMat = new TextureMaterial( new Texture("images/dirt.jpg") );
        groundMat.uniforms.get("repeatUV").data = new float[] { 10, 10 };
        Mesh ground = new Mesh(groundGeo, groundMat);
        ground.scale( 100 );
        ground.translateGlobal( -50, 0, -50 );
        ground.rotateXLocal( Math.PI/2 );
        sceneRoot.add( ground );
        
        /*
        Geometry boxGeo = new BoxGeometry(1,1,1);
        Material boxMat = new TextureMaterial( new Texture("images/stone.png") );
        boxMat.uniforms.get("repeatUV").data = new float[] {2, 3};
        Mesh box = new Mesh(boxGeo, boxMat);
        box.translateGlobal(0, 1, 0);
        sceneRoot.add( box );
        */
       
        Geometry rectGeo = new RectangleGeometry(1,1);
        rectMat = new DistortMaterial( new Texture("images/color-grid.png"),
                                              new Texture("images/noise.jpg") );
        Mesh rect = new Mesh(rectGeo, rectMat);
        rect.translateGlobal(0, 1, 0);
        sceneRoot.add( rect );
       
        
    }
    
    public void update()
    {
        renderer.render(sceneRoot, camera);
        
        rectMat.uniforms.get("time").data = (float)( clock.getElapsedTime() );
        
        rig.update(1.0 / 60.0);
    }
    
    public static void main(String[] args)
    {
        new TestGraphics().run();
    }
}
