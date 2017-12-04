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
public class LineSegment3D extends AbstractSpatialShape {
    
    final Point3D p1;
    final Point3D p2;
    final Edge3D e;
    
    public LineSegment3D(double x1, double y1, double z1, double x2, double y2, double z2) {
        super();
        p1 = new Point3D(x1, y1, z1);
        p2 = new Point3D(x2, y2, z2);
        e = new Edge3D(p1, p2);
        point3DsSet.add(p1);
        point3DsSet.add(p2);
        edge3DsSet.add(e);
    }

    public LineSegment3D(Point3D p1, Point3D p2) {
        super();
        this.p1 = p1;
        this.p2 = p2;
        this.e = new Edge3D(p1, p2);
        this.point3DsSet.add(p1);
        this.point3DsSet.add(p2);
        this.edge3DsSet.add(e);
    }   
}
