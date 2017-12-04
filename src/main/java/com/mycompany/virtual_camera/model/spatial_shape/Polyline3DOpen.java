/*
 * Here comes the text of your license
 * Each line should be prefixed with  * 
 */
package com.mycompany.virtual_camera.model.spatial_shape;

import com.mycompany.virtual_camera.model.Edge3D;
import com.mycompany.virtual_camera.model.Point3D;

/**
 *
 * @author Paweł Mac
 */
public class Polyline3DOpen extends AbstractSpatialShape {
    
    public Polyline3DOpen(Point3D... point3Ds) {
        super();
        if (point3Ds.length < 2) {
            throw new IllegalArgumentException("minimum 2 points");
        }
        
        for (int i = 1; i < point3Ds.length; i++) {
            Point3D point3D_1 = point3Ds[i-1];
            Point3D point3D_2 = point3Ds[i];
            point3DsSet.add(point3D_1);
            point3DsSet.add(point3D_2);
            edge3DsSet.add(new Edge3D(point3D_1, point3D_2));
        }
    }
}
