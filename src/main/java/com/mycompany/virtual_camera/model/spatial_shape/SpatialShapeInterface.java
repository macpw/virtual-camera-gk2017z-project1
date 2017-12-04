/*
 * Here comes the text of your license
 * Each line should be prefixed with  * 
 */
package com.mycompany.virtual_camera.model.spatial_shape;

import com.mycompany.virtual_camera.model.Edge3D;
import com.mycompany.virtual_camera.model.Point3D;
import java.util.Set;

/**
 *
 * @author Paweł Mac
 */
public interface SpatialShapeInterface {

    public Set<Point3D> getPoint3DsSet();

    public Set<Edge3D> getEdge3DsSet();
}
