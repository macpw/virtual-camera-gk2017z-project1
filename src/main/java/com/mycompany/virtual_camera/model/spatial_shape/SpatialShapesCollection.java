/*
 * Here comes the text of your license
 * Each line should be prefixed with  * 
 */
package com.mycompany.virtual_camera.model.spatial_shape;

/**
 *
 * @author Paweł Mac
 */
public class SpatialShapesCollection extends AbstractSpatialShape {

    public SpatialShapesCollection() {
        super();
    }
    
    public void addSpatialShape(SpatialShapeInterface spatialShape) {
        point3DsSet.addAll(spatialShape.getPoint3DsSet());
        edge3DsSet.addAll(spatialShape.getEdge3DsSet());
    }
}
