/*
 * Here comes the text of your license
 * Each line should be prefixed with  * 
 */
package com.mycompany.virtual_camera.model;

import java.awt.geom.Line2D;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Observable;
import java.util.Set;

/**
 *
 * @author Paweł Mac
 */
public final class ViewportModel extends Observable {
    
    private Set<Point3D> point3DsSceneSet;
    private Set<Edge3D>  edge3DsSceneSet;
    private Point3D observerPoint;
    private double distanceBetweenObserverAndViewport;
    private final int viewportWidth;
    private final int viewportHeight;
    
    private final Map<Point3D, Point3D> point3DSceneToPoint3DObserverMap;
    private final Map<Point3D, Point3D> point3DObserverToMockPoint3DMap;// mock point3D
    private final Set<Edge3D> edge3DsObserverSet;
    private final Map<Edge3D, Edge3D> edge3DObserverToMockEdge3DMap;// mock edge3D
    private final Map<Edge3D, Edge2D> edge3DObserverToEdge2DViewportMap;
    private final Map<Edge2D, Edge3D> edge2DViewportToEdge3DObserverMap;
    private final Map<Edge2D, Line2DHolder> edge2DToLine2DHolderMap;
    
    private double step = 10.0d;
    private double angleInDegrees = 1.0d;
    
    public ViewportModel(Set<Point3D> point3DsSceneSet, Set<Edge3D> edge3DsSceneSet, Point3D observerPoint, double distanceBetweenObserverAndViewport, int viewportWidth, int viewportHeight) {
        this.point3DsSceneSet = point3DsSceneSet;
        this.edge3DsSceneSet = edge3DsSceneSet;
        this.observerPoint = observerPoint;
        this.distanceBetweenObserverAndViewport = distanceBetweenObserverAndViewport;
        this.viewportWidth = viewportWidth;
        this.viewportHeight = viewportHeight;
        
        this.point3DSceneToPoint3DObserverMap = new HashMap<>();
        this.initPoint3DSceneToPoint3DObserverMap();
        this.point3DObserverToMockPoint3DMap = new HashMap<>();// mock point3D
        this.initPoint3DObserverToMockPoint3DMap();// mock point3D
        this.edge3DsObserverSet = new HashSet<>();
        this.initEdge3DsObserverSet();
        this.edge3DObserverToMockEdge3DMap = new HashMap<>();// mock edge3D
        this.initEdge3DObserverToMockEdge3DMap();// mock edge3D
        this.edge3DObserverToEdge2DViewportMap = new HashMap<>();
        this.edge2DViewportToEdge3DObserverMap = new HashMap<>();
        this.initEdge3DObserverToEdge2DViewportMap_And_Edge2DViewportToEdge3DObserverMap();
        this.edge2DToLine2DHolderMap = new HashMap<>();
        this.initEdge2DToLine2DHolderMap();
    }

    // Getters and Setters
    
    public double getDistanceBetweenObserverAndViewport() {
        return distanceBetweenObserverAndViewport;
    }
    
    public void setDistanceBetweenObserverAndViewport(double distanceBetweenObserverAndViewport) {
        this.distanceBetweenObserverAndViewport = distanceBetweenObserverAndViewport;
        this.updateEdge3DObserverToEdge2DViewportMap();
        this.updateEdge2DToLine2DHolderMap();
        this.setChanged();
        this.notifyObservers();
    }
    
    public int getViewportWidth() {
        return viewportWidth;
    }
    
    public int getViewportHeight() {    
        return viewportHeight;
    }
    
    public double getStep() {
        return step;
    }
    
    public void setStep(double step) {
        this.step = step;
    }
    
    public double getAngleInDegrees() {
        return angleInDegrees;
    }
    
    public void setAngleInDegrees(double angleInDegrees) {
        this.angleInDegrees = angleInDegrees;
    }
    
    // Methods
    
    private void initPoint3DSceneToPoint3DObserverMap() {
        for (Point3D point3DInSceneCoordinatesSystem : point3DsSceneSet) {
            Point3D point3DInObserverCoordinatesSystem = new Point3D(0.0d, 0.0d, 0.0d);
            calculatePointInTheObserverCoordinatesSystem(point3DInSceneCoordinatesSystem, point3DInObserverCoordinatesSystem);
            point3DSceneToPoint3DObserverMap.put(point3DInSceneCoordinatesSystem, point3DInObserverCoordinatesSystem);
        }
    }
    
    private void calculatePointInTheObserverCoordinatesSystem(Point3D point3DInSceneCoordinatesSystem, Point3D point3DInObserverCoordinatesSystem) {
        point3DInObserverCoordinatesSystem.setX(point3DInSceneCoordinatesSystem.getX() - observerPoint.getX());
        point3DInObserverCoordinatesSystem.setY(point3DInSceneCoordinatesSystem.getY() - observerPoint.getY());
        point3DInObserverCoordinatesSystem.setZ(point3DInSceneCoordinatesSystem.getZ() - observerPoint.getZ());
    }
    
    private void initPoint3DObserverToMockPoint3DMap() {
        for (Point3D point3DInObserverCoordinatesSystem : point3DSceneToPoint3DObserverMap.values()) {
            Point3D mockPoint3D = 
                    new Point3D(
                            point3DInObserverCoordinatesSystem.getX(), 
                            point3DInObserverCoordinatesSystem.getY(), 
                            point3DInObserverCoordinatesSystem.getZ()
                    );
            point3DObserverToMockPoint3DMap.put(point3DInObserverCoordinatesSystem, mockPoint3D);
        }
    }
    
    private void initEdge3DsObserverSet() {
        Point3D first;
        Point3D second;
        Edge3D edge3DObserver;
        for (Edge3D edge3DScene : edge3DsSceneSet) {
            first = edge3DScene.getFirst();
            second = edge3DScene.getSecond();
            edge3DObserver = new Edge3D(point3DSceneToPoint3DObserverMap.get(first), 
                                        point3DSceneToPoint3DObserverMap.get(second));
            edge3DsObserverSet.add(edge3DObserver);
        }
    }
    
    private void initEdge3DObserverToMockEdge3DMap() {
        for (Edge3D edge3DObserver : edge3DsObserverSet) {
            Point3D first  = edge3DObserver.getFirst();
            Point3D second = edge3DObserver.getSecond();
            Point3D firstMockPoint3D = point3DObserverToMockPoint3DMap.get(first);
            Point3D secondMocKPoint3D = point3DObserverToMockPoint3DMap.get(second);
            Edge3D mockEdge3D = new Edge3D(firstMockPoint3D, secondMocKPoint3D);
            edge3DObserverToMockEdge3DMap.put(edge3DObserver, mockEdge3D);
        }
    }
    
    private void initEdge3DObserverToEdge2DViewportMap_And_Edge2DViewportToEdge3DObserverMap() {
        for (Edge3D edge3D : edge3DsObserverSet) {
            Edge2D edge2DViewport = new Edge2D(0.0d, 0.0d, 0.0d, 0.0d);
            calculateEdge2DOnViewport(edge3D, edge2DViewport);
            edge3DObserverToEdge2DViewportMap.put(edge3D, edge2DViewport);
            edge2DViewportToEdge3DObserverMap.put(edge2DViewport, edge3D);
        }
    }
    
    private void calculateEdge2DOnViewport(Edge3D edge3DObserver, Edge2D edge2DViewport) {
//        double x1 = (edge3DObserver. getFirst().getX() * distanceBetweenObserverAndViewport) 
//                  / (edge3DObserver. getFirst().getZ() + distanceBetweenObserverAndViewport);
//        double y1 = (edge3DObserver. getFirst().getY() * distanceBetweenObserverAndViewport) 
//                  / (edge3DObserver. getFirst().getZ() + distanceBetweenObserverAndViewport);
//        double x2 = (edge3DObserver.getSecond().getX() * distanceBetweenObserverAndViewport) 
//                  / (edge3DObserver.getSecond().getZ() + distanceBetweenObserverAndViewport);
//        double y2 = (edge3DObserver.getSecond().getY() * distanceBetweenObserverAndViewport) 
//                  / (edge3DObserver.getSecond().getZ() + distanceBetweenObserverAndViewport);
        double x1 = (edge3DObserver.getFirst().getX() * distanceBetweenObserverAndViewport) 
                  /  edge3DObserver.getFirst().getZ();
        double y1 = (edge3DObserver.getFirst().getY() * distanceBetweenObserverAndViewport) 
                  /  edge3DObserver.getFirst().getZ();
        double x2 = (edge3DObserver.getSecond().getX() * distanceBetweenObserverAndViewport) 
                  /  edge3DObserver.getSecond().getZ();
        double y2 = (edge3DObserver.getSecond().getY() * distanceBetweenObserverAndViewport) 
                  /  edge3DObserver.getSecond().getZ();
        edge2DViewport.setX1(x1);
        edge2DViewport.setY1(y1);
        edge2DViewport.setX2(x2);
        edge2DViewport.setY2(y2);
    }
    
    private void initEdge2DToLine2DHolderMap() {
        for (Edge2D edge2D : edge3DObserverToEdge2DViewportMap.values()) {
            Line2D.Double line2D = new Line2D.Double(0.0d, 0.0d, 0.0d, 0.0d);
            calculateLine2D(edge2D, line2D);
            Line2DHolder line2DHolder = new Line2DHolder(line2D);
            edge2DToLine2DHolderMap.put(edge2D, line2DHolder);
            // only edges/lines in front of viewport
            Edge3D edge3D = edge2DViewportToEdge3DObserverMap.get(edge2D);
            Point3D firstPoint3D = edge3D.getFirst();
            if (firstPoint3D.getZ() > distanceBetweenObserverAndViewport) {
                line2DHolder.setInFrontOfViewportFirst(true);
            } else {
                line2DHolder.setInFrontOfViewportFirst(false);
            }
            Point3D secondPoint3D = edge3D.getSecond();
            if (secondPoint3D.getZ() > distanceBetweenObserverAndViewport) {
                line2DHolder.setInFrontOfViewportSecond(true);
            } else {
                line2DHolder.setInFrontOfViewportSecond(false);
            }
        }
    }
    
    private void calculateLine2D(Edge2D edge2D, Line2D line2D) {
        double x1 =  edge2D.getX1() + viewportWidth  / 2.0d;
        double y1 = -edge2D.getY1() + viewportHeight / 2.0d;
        double x2 =  edge2D.getX2() + viewportWidth  / 2.0d;
        double y2 = -edge2D.getY2() + viewportHeight / 2.0d;
        line2D.setLine(x1, y1, x2, y2);
    }
    
    public Collection<Line2DHolder> getCollectionOfLine2DHolder() {
        return edge2DToLine2DHolderMap.values();
    }
    
    private void updateEdge3DObserverToEdge2DViewportMap() {
        for (Map.Entry<Edge3D, Edge2D> entry : edge3DObserverToEdge2DViewportMap.entrySet()) {
            Edge3D edge3DObserver = entry.getKey();
            Edge2D edge2DViewport = entry.getValue();
            
            // mockEdge3D if necessary
            Point3D firstPoint3D  = edge3DObserver.getFirst();
            Point3D secondPoint3D = edge3DObserver.getSecond();
            if (        (firstPoint3D.getZ() <= distanceBetweenObserverAndViewport || secondPoint3D.getZ() <= distanceBetweenObserverAndViewport) 
                    && !(firstPoint3D.getZ() <= distanceBetweenObserverAndViewport && secondPoint3D.getZ() <= distanceBetweenObserverAndViewport)) {
                Edge3D mockEdge3D = edge3DObserverToMockEdge3DMap.get(edge3DObserver);
                // update mockEdge3D by updating points
                Point3D firstMockPoint3D = mockEdge3D.getFirst();
                firstMockPoint3D.setX(firstPoint3D.getX());
                firstMockPoint3D.setY(firstPoint3D.getY());
                firstMockPoint3D.setZ(firstPoint3D.getZ());
                Point3D secondMockPoint3D = mockEdge3D.getSecond();
                secondMockPoint3D.setX(secondPoint3D.getX());
                secondMockPoint3D.setY(secondPoint3D.getY());
                secondMockPoint3D.setZ(secondPoint3D.getZ());
                
                if (firstPoint3D.getZ() <= distanceBetweenObserverAndViewport) {
                    Point3D calculatedFirstMockPoint3D = calculateMockPoint3D(firstMockPoint3D, secondMockPoint3D);
                    firstMockPoint3D.setX(calculatedFirstMockPoint3D.getX());
                    firstMockPoint3D.setY(calculatedFirstMockPoint3D.getY());
                    firstMockPoint3D.setZ(calculatedFirstMockPoint3D.getZ());
                } else {
                    Point3D calculatedSecondMockPoint3D = calculateMockPoint3D(secondMockPoint3D, firstMockPoint3D);
                    secondMockPoint3D.setX(calculatedSecondMockPoint3D.getX());
                    secondMockPoint3D.setY(calculatedSecondMockPoint3D.getY());
                    secondMockPoint3D.setZ(calculatedSecondMockPoint3D.getZ());
                }
                edge3DObserver = mockEdge3D;
            }
            calculateEdge2DOnViewport(edge3DObserver, edge2DViewport);
        }
    }
    
    // calculate line and plane intersection
    private Point3D calculateMockPoint3D(Point3D firstPoint3D, Point3D secondPoint3D) {
        //point
        double px = firstPoint3D.getX();
        double py = firstPoint3D.getY();
        double pz = firstPoint3D.getZ();
        // vector from first to second
        double vx = secondPoint3D.getX() - firstPoint3D.getX();
        double vy = secondPoint3D.getY() - firstPoint3D.getY();
        double vz = secondPoint3D.getZ() - firstPoint3D.getZ();
        // plane's equation: Ax + By + Cz + D = 0
        // A == 0; B == 0;
        double planeFactorC = 1;
        double planeFactorD = -(distanceBetweenObserverAndViewport);
        // line's parametric equation:
        // x = x0 + t*vx
        // y = y0 + t*vy
        // z = z0 + t*vz
        ////////////////////
        // plane's equation and line's parametric equation
        // calculate t
        // A(px + t*vx) + B(py + t*vy) + C(pz + t*vz) + D = 0
        // Apx + At*vx  + Bpy + Bt*vy  + Cpz + Ct*vz  + D = 0
        // t(Avx  + Bvy + Cvz) + Apx + Bpy + Cpz + D = 0
        // t = -(Apx + Bpy + Cpz + D) / (Avx  + Bvy + Cvz)
        // then point P(x, y, z) is
        // P(px + t*vx, py + t*vy, pz + t*vz)
        double sum_Apx_Bpy_Cpz_D = planeFactorC*pz + planeFactorD;
        double sum_Avx_Bvy_Cvz   = planeFactorC*vz;
        double t = (-sum_Apx_Bpy_Cpz_D)/sum_Avx_Bvy_Cvz;
        Point3D mockPoint3D = new Point3D(px + t*vx, py + t*vy, pz + t*vz);
        return mockPoint3D;
    }
    
    private void updateEdge2DToLine2DHolderMap() {
        for (Map.Entry<Edge2D, Line2DHolder> entry : edge2DToLine2DHolderMap.entrySet()) {
            Edge2D edge2D = entry.getKey();
            Line2DHolder line2DHolder = entry.getValue();
            calculateLine2D(edge2D, line2DHolder.getLine2D());
            // only edges/lines in front of viewport
            Edge3D edge3D = edge2DViewportToEdge3DObserverMap.get(edge2D);
            Point3D firstPoint3D = edge3D.getFirst();
            if (firstPoint3D.getZ() > distanceBetweenObserverAndViewport) {
                line2DHolder.setInFrontOfViewportFirst(true);
            } else {
                line2DHolder.setInFrontOfViewportFirst(false);
            }
            Point3D secondPoint3D = edge3D.getSecond();
            if (secondPoint3D.getZ() > distanceBetweenObserverAndViewport) {
                line2DHolder.setInFrontOfViewportSecond(true);
            } else {
                line2DHolder.setInFrontOfViewportSecond(false);
            }
        }
    }
    
    // motions
    public void moveForward() {
        for (Point3D point3DObserver : point3DSceneToPoint3DObserverMap.values()) {
            point3DObserver.setZ(point3DObserver.getZ() - step);
        }
        updateEdge3DObserverToEdge2DViewportMap();
        updateEdge2DToLine2DHolderMap();
        this.setChanged();
        this.notifyObservers();
    }
    public void moveBackward() {
        for (Point3D point3DObserver : point3DSceneToPoint3DObserverMap.values()) {
            point3DObserver.setZ(point3DObserver.getZ() + step);
        }
        updateEdge3DObserverToEdge2DViewportMap();
        updateEdge2DToLine2DHolderMap();
        this.setChanged();
        this.notifyObservers();
    }
    public void moveLeft() {
        for (Point3D point3DObserver : point3DSceneToPoint3DObserverMap.values()) {
            point3DObserver.setX(point3DObserver.getX() + step);
        }
        updateEdge3DObserverToEdge2DViewportMap();
        updateEdge2DToLine2DHolderMap();
        this.setChanged();
        this.notifyObservers();
    }
    public void moveRight() {
        for (Point3D point3DObserver : point3DSceneToPoint3DObserverMap.values()) {
            point3DObserver.setX(point3DObserver.getX() - step);
        }
        updateEdge3DObserverToEdge2DViewportMap();
        updateEdge2DToLine2DHolderMap();
        this.setChanged();
        this.notifyObservers();
    }
    public void moveUpward() {
        for (Point3D point3DObserver : point3DSceneToPoint3DObserverMap.values()) {
            point3DObserver.setY(point3DObserver.getY() - step);
        }
        updateEdge3DObserverToEdge2DViewportMap();
        updateEdge2DToLine2DHolderMap();
        this.setChanged();
        this.notifyObservers();
    }
    public void moveDownward() {
        for (Point3D point3DObserver : point3DSceneToPoint3DObserverMap.values()) {
            point3DObserver.setY(point3DObserver.getY() + step);
        }
        updateEdge3DObserverToEdge2DViewportMap();
        updateEdge2DToLine2DHolderMap();
        this.setChanged();
        this.notifyObservers();
    }
    
    //rotations
    public void rotateLeft() {
        double angleInRadians = Math.toRadians(angleInDegrees);
        for (Point3D point3DObserver : point3DSceneToPoint3DObserverMap.values()) {
            double x = Math.cos(angleInRadians) * point3DObserver.getX() + 
                                              0 * point3DObserver.getY() + 
                       Math.sin(angleInRadians) * point3DObserver.getZ();
            double y = 0 * point3DObserver.getX() + 
                       1 * point3DObserver.getY() + 
                       0 * point3DObserver.getZ();
            double z = -Math.sin(angleInRadians) * point3DObserver.getX() + 
                                               0 * point3DObserver.getY() + 
                        Math.cos(angleInRadians) * point3DObserver.getZ();
            point3DObserver.setX(x);
            point3DObserver.setY(y);
            point3DObserver.setZ(z);
        }
        updateEdge3DObserverToEdge2DViewportMap();
        updateEdge2DToLine2DHolderMap();
        this.setChanged();
        this.notifyObservers();
    }
    public void rotateRight() {
        double angleInRadians = -Math.toRadians(angleInDegrees);
        for (Point3D point3DObserver : point3DSceneToPoint3DObserverMap.values()) {
            double x = Math.cos(angleInRadians) * point3DObserver.getX() + 
                                              0 * point3DObserver.getY() + 
                       Math.sin(angleInRadians) * point3DObserver.getZ();
            double y = 0 * point3DObserver.getX() + 
                       1 * point3DObserver.getY() + 
                       0 * point3DObserver.getZ();
            double z = -Math.sin(angleInRadians) * point3DObserver.getX() + 
                                               0 * point3DObserver.getY() + 
                        Math.cos(angleInRadians) * point3DObserver.getZ();
            point3DObserver.setX(x);
            point3DObserver.setY(y);
            point3DObserver.setZ(z);
        }
        updateEdge3DObserverToEdge2DViewportMap();
        updateEdge2DToLine2DHolderMap();
        this.setChanged();
        this.notifyObservers();
    }
    public void rotateUpward() {
        double angleInRadians = Math.toRadians(angleInDegrees);
        for (Point3D point3DObserver : point3DSceneToPoint3DObserverMap.values()) {
            double x = 1 * point3DObserver.getX() + 
                       0 * point3DObserver.getY() + 
                       0 * point3DObserver.getZ();
            double y =                           0 * point3DObserver.getX() + 
                          Math.cos(angleInRadians) * point3DObserver.getY() + 
                       (-Math.sin(angleInRadians)) * point3DObserver.getZ();
            double z =                        0 * point3DObserver.getX() + 
                       Math.sin(angleInRadians) * point3DObserver.getY() + 
                       Math.cos(angleInRadians) * point3DObserver.getZ();
            point3DObserver.setX(x);
            point3DObserver.setY(y);
            point3DObserver.setZ(z);
        }
        updateEdge3DObserverToEdge2DViewportMap();
        updateEdge2DToLine2DHolderMap();
        this.setChanged();
        this.notifyObservers();
    }
    public void rotateDownward() {
        double angleInRadians = -Math.toRadians(angleInDegrees);
        for (Point3D point3DObserver : point3DSceneToPoint3DObserverMap.values()) {
            double x = 1 * point3DObserver.getX() + 
                       0 * point3DObserver.getY() + 
                       0 * point3DObserver.getZ();
            double y =                           0 * point3DObserver.getX() + 
                         Math.cos(angleInRadians)  * point3DObserver.getY() + 
                       (-Math.sin(angleInRadians)) * point3DObserver.getZ();
            double z =                        0 * point3DObserver.getX() + 
                       Math.sin(angleInRadians) * point3DObserver.getY() + 
                       Math.cos(angleInRadians) * point3DObserver.getZ();
            point3DObserver.setX(x);
            point3DObserver.setY(y);
            point3DObserver.setZ(z);
        }
        updateEdge3DObserverToEdge2DViewportMap();
        updateEdge2DToLine2DHolderMap();
        this.setChanged();
        this.notifyObservers();
    }
    public void rotateTiltLeft() {
        double angleInRadians = -Math.toRadians(angleInDegrees);
        for (Point3D point3DObserver : point3DSceneToPoint3DObserverMap.values()) {
            double x =   Math.cos(angleInRadians)  * point3DObserver.getX() + 
                       (-Math.sin(angleInRadians)) * point3DObserver.getY() + 
                                                 0 * point3DObserver.getZ();
            double y = Math.sin(angleInRadians) * point3DObserver.getX() + 
                       Math.cos(angleInRadians) * point3DObserver.getY() + 
                                              0 * point3DObserver.getZ();
            double z = 0 * point3DObserver.getX() + 
                       0 * point3DObserver.getY() + 
                       1 * point3DObserver.getZ();
            point3DObserver.setX(x);
            point3DObserver.setY(y);
            point3DObserver.setZ(z);
        }
        updateEdge3DObserverToEdge2DViewportMap();
        updateEdge2DToLine2DHolderMap();
        this.setChanged();
        this.notifyObservers();
    }
    public void rotateTiltRight() {
        double angleInRadians = Math.toRadians(angleInDegrees);
        for (Point3D point3DObserver : point3DSceneToPoint3DObserverMap.values()) {
            double x =   Math.cos(angleInRadians)  * point3DObserver.getX() + 
                       (-Math.sin(angleInRadians)) * point3DObserver.getY() + 
                                                 0 * point3DObserver.getZ();
            double y = Math.sin(angleInRadians) * point3DObserver.getX() + 
                       Math.cos(angleInRadians) * point3DObserver.getY() + 
                                              0 * point3DObserver.getZ();
            double z = 0 * point3DObserver.getX() + 
                       0 * point3DObserver.getY() + 
                       1 * point3DObserver.getZ();
            point3DObserver.setX(x);
            point3DObserver.setY(y);
            point3DObserver.setZ(z);
        }
        updateEdge3DObserverToEdge2DViewportMap();
        updateEdge2DToLine2DHolderMap();
        this.setChanged();
        this.notifyObservers();
    }
}
