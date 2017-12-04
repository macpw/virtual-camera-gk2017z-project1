/*
 * Here comes the text of your license
 * Each line should be prefixed with  * 
 */
package com.mycompany.virtual_camera;

import com.mycompany.virtual_camera.model.Point3D;

/**
 *
 * @author Paweł Mac
 */
public class ConstantsForViewportModel {

    public static final Point3D OBSERVER_POINT = new Point3D(0, 0, 0);
    public static final double DISTANCE_FROM_VIEWPORT = 200;
    public static final int VIEWPORT_WIDTH  = 600;
    public static final int VIEWPORT_HEIGHT = 400;
    /*
    * Prevented from constructing objects of this class, 
    * by declaring this private constructor.
    */
    private ConstantsForViewportModel() {
    }
}
