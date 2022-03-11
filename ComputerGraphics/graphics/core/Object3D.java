package graphics.core;

import graphics.math.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents any object that has a position and orientation in 3D space.
 * Extended by Group, Camera, Mesh, ...
 * Makes use of tree data structure to organize objects in a 3D scene.
 */
public class Object3D
{
    public Matrix transform;
    // support tree data structure
    public Object3D parent;
    public ArrayList<Object3D> children;
    
    public Object3D()
    {
        transform = Matrix.makeIdentity();
        parent = null;
        children = new ArrayList<Object3D>();
    }
    
    // tree operations
    
    public void add(Object3D child)
    {
        children.add(child);
        child.parent = this;
    }
    
    public void remove(Object3D child)
    {
        children.remove(child);
        child.parent = null;
    }
    
    /**
     * Convert Tree structure into a List, for use by Renderer class.
     */
    public ArrayList<Object3D> getDescendentList()
    {
        ArrayList<Object3D> descendentList = new ArrayList<Object3D>();
        ArrayList<Object3D> objectsToProcess = new ArrayList<Object3D>();
        // start by adding this object, root of tree, to process list
        objectsToProcess.add( this );
        // while list of objects to process is not empty,
        while (objectsToProcess.size() > 0)
        {
           // add first object in list to descendents list
           Object3D first = objectsToProcess.get(0);
           descendentList.add( first );
           // add children of first object to process list
           for (Object3D child : first.children)
           {
               objectsToProcess.add(child);
           }
           // remove first object from process list
           objectsToProcess.remove(first);
        }
        // return descendents list
        return descendentList;
    }
    
    /**
     * Determine where this object is, with respect to the root of the tree.
     * Accomplished by multiplying all matrices from this object,
     *   through ancestors (parent, parent's parent, ..., root).
     *
     * @return matrix that encodes position, rotation, scale data
     */
    public Matrix getWorldMatrix()
    {
        // calculate with recursion
        if (parent == null)
            return transform;
        else
            return Matrix.multiplyMatrices( parent.getWorldMatrix(), transform );
    }
    
    /*
    public Vector getPosition()
    {
        return transform.getPosition();
    }
    
    Also TODO:
    setPosition(x,y,z)
    translate(x,y,z)
    
    "roll"
    getRotationZ()
    setRotationZ(angle)
    rotateZ(angle)
    
    "look"
    getRotationX()
    setRotationX(angle)
    rotateX(angle)

    "turn"
    getRotationY()
    setRotationY(angle)
    rotateY(angle)
    
    */
}
