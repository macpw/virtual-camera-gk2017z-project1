/*
 * Here comes the text of your license
 * Each line should be prefixed with  * 
 */
package com.mycompany.virtual_camera.model;

/**
 *
 * @author Paweł Mac
 */
public class Edge3D {
    
    private Point3D first;
    private Point3D second;
    
    public Edge3D(Point3D first, Point3D second) throws NullPointerException {
        if (first == null) {
            throw new NullPointerException("first point is null");
        } else if (second == null) {
            throw new NullPointerException("second point is null");
        }
        this.first = first;
        this.second = second;
    }

    public Point3D getFirst() {
        return first;
    }

    public void setFirst(Point3D first) {
        this.first = first;
    }

    public Point3D getSecond() {
        return second;
    }

    public void setSecond(Point3D second) {
        this.second = second;
    }

    @Override
    public String toString() {
        return "Edge3D{" + "first=" + first + ", second=" + second + '}';
    }
}
