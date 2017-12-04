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
public class Polyline3DClosed extends Polyline3DOpen {

    public Polyline3DClosed(Point3D... point3Ds) {
        super(point3Ds);
        edge3DsSet.add(new Edge3D(point3Ds[0], point3Ds[point3Ds.length-1]));
    }
}
