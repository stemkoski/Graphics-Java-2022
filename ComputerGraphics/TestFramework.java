import graphics.core.*;
import graphics.geometry.*;
import graphics.material.*;
import graphics.math.*;

public class TestFramework extends Base
{
    public Object3D sceneRoot;
    public Camera   camera;
    public Renderer renderer;

    public Mesh box;

    public MovementRig rig;

    public void initialize()
    {
        sceneRoot = new Object3D();
        camera    = new Camera();
        camera.setPosition( new Vector(0,1,3) );

        // floor / ground
        Geometry geo = new RectangleGeometry(10, 10);
        Material mat = new SurfaceMaterial();
        Mesh rectangle = new Mesh(geo, mat);
        rectangle.setPosition( new Vector(0, 0, -2) );  
        rectangle.rotateXLocal( -(float)Math.PI/2 );
        sceneRoot.add( rectangle );

        /*
        Geometry geo2 = new RectangleGeometry(1, 0.75);
        Material mat2 = new SurfaceMaterial();

        // turn off vertex colors
        // mat2.uniforms.get("useVertexColor").data = 0;
        mat2.uniforms.get("color").data = new float[] {0.5f, 0.5f, 1.0f};

        Mesh rectangle2 = new Mesh(geo2, mat2);
        rectangle2.setPosition( new Vector(0, 1, -3) );
        sceneRoot.add( rectangle2 );
         */

        /*
        Geometry boxGeo = new BoxGeometry(1,1,1);
        Material boxMat = new SurfaceMaterial();
        box = new Mesh(boxGeo, boxMat);
        box.setPosition( new Vector(0,1.5,0) );
        sceneRoot.add(box);
         */

        // Geometry polyGeo = new PolygonGeometry(8);
        // Geometry plane = new PlaneGeometry();
        // Material polyMat = new SurfaceMaterial();

        // turn off vertex colors:
        // polyMat.uniforms.get("useVertexColor").data = 0;
        // polyMat.uniforms.get("color").data = new float[] {1, 1, 0};

        /*
        Geometry sphereGeo = new TorusGeometry();
        Material sphereMat = new SurfaceMaterial();
        Mesh mesh = new Mesh(sphereGeo, sphereMat);
         */

        /*
        // add top and bottom to this shape.
        Geometry prismGeo = new PrismGeometry(6);
        Mesh mesh = new Mesh(prismGeo, mat);
        // add top and bottom to the prism
        Geometry hexagonGeo = new HexagonGeometry();
        Mesh top = new Mesh(hexagonGeo, mat);
        top.rotateXLocal(Math.PI / 2);
        top.translateGlobal(0,1,0);
        mesh.add(top);
         */

        /*
        Geometry cylinderGeo = new CylinderGeometry();
        Mesh mesh = new Mesh(cylinderGeo, mat);
         */

        /*
        Geometry pyramidGeo = new PyramidGeometry(4);
        Mesh mesh = new Mesh(pyramidGeo, mat);
         */

        Material pointMat = new PointMaterial();
        Material lineMat = new LineMaterial();
        Material surfMat = new SurfaceMaterial();

        // pointMat.uniforms.get("color").data = new float[] {1, 0, 0};
        lineMat.uniforms.get("color").data = new float[] {0, 0, 1};
        // surfMat.uniforms.get("color").data = new float[] { 1.0f, 1.0f, 0.1f };

        Geometry geom = new SphereGeometry();

        Mesh mesh = new Mesh(geom, surfMat);
        mesh.setPosition( new Vector(0, 1.5f, 0) );

        Mesh wireMesh = new Mesh(geom, lineMat);
        mesh.add( wireMesh );
        
        sceneRoot.add( mesh );

        renderer = new Renderer();

        rig = new MovementRig(camera, input);
    }

    public void update()
    {
        /*
        box.rotateXLocal(0.011);
        box.rotateYLocal(0.027);
         */

        renderer.render( sceneRoot, camera );

        rig.update(1.0f / 60.0f);
    }

    public static void main(String[] args)
    {
        new TestFramework().run();
    }
}
