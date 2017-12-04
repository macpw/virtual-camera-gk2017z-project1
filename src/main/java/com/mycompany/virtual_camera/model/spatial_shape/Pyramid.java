/*
 * Here comes the text of your license
 * Each line should be prefixed with  * 
 */
package com.mycompany.virtual_camera.model.spatial_shape;

import com.mycompany.virtual_camera.model.Edge3D;
import com.mycompany.virtual_camera.model.Point3D;
import java.util.Arrays;

/**
 *
 * @author Paweł Mac
 */
public class Pyramid extends AbstractSpatialShape {
    
    final Point3D p1;
    final Point3D p2;
    final Point3D p3;
    final Point3D p4;
    final Point3D p5;
    
    final Edge3D e1;
    final Edge3D e2;
    final Edge3D e3;
    final Edge3D e4;
    
    final Edge3D e5;
    final Edge3D e6;
    final Edge3D e7;
    final Edge3D e8;
    
    public Pyramid(double x, double y, double z, double width, double height, double depth) {
        super();
        
        p1 = new Point3D(x,             y,        z);
        p2 = new Point3D(x+width,       y,        z);
        p3 = new Point3D(x,             y,        z+depth);
        p4 = new Point3D(x+width,       y,        z+depth);
        p5 = new Point3D(x+(width/2.0), y+height, z+(depth/2d));
        
        e1 = new Edge3D(p1, p2);
        e2 = new Edge3D(p2, p4);
        e3 = new Edge3D(p3, p1);
        e4 = new Edge3D(p4, p3);
        
        e5 = new Edge3D(p1, p5);
        e6 = new Edge3D(p2, p5);
        e7 = new Edge3D(p3, p5);
        e8 = new Edge3D(p4, p5);
        
        Point3D[] point3Ds = {p1,p2,p3,p4,p5};
        this.point3DsSet.addAll(Arrays.asList(point3Ds));
        Edge3D[] edge3Ds = {e1,e2,e3,e4,e5,e6,e7,e8};
        this.edge3DsSet.addAll(Arrays.asList(edge3Ds));
    }
}
