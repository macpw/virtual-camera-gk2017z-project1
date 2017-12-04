/*
 * Here comes the text of your license
 * Each line should be prefixed with  * 
 */
package com.mycompany.virtual_camera.model.spatial_shape;

import com.mycompany.virtual_camera.model.Edge3D;
import com.mycompany.virtual_camera.model.Point3D;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author Paweł Mac
 */
public abstract class AbstractSpatialShape implements SpatialShapeInterface {
    
    protected final Set<Point3D> point3DsSet;
    protected final Set<Edge3D>   edge3DsSet;
    
    public AbstractSpatialShape() {
        point3DsSet = new HashSet<>();
        edge3DsSet = new HashSet<>();
    }
    
    @Override
    public Set<Point3D> getPoint3DsSet() {
        return point3DsSet;
    }
    
    @Override
    public Set<Edge3D> getEdge3DsSet() {
        return edge3DsSet;
    }
}
