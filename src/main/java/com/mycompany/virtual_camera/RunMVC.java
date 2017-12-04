/*
 * Here comes the text of your license
 * Each line should be prefixed with  * 
 */
package com.mycompany.virtual_camera;

import com.mycompany.virtual_camera.controller.Controller;
import com.mycompany.virtual_camera.model.Edge3D;
import com.mycompany.virtual_camera.model.Point3D;
import com.mycompany.virtual_camera.model.ViewportModel;
import com.mycompany.virtual_camera.model.spatial_shape.Cuboid;
import com.mycompany.virtual_camera.model.spatial_shape.Polyline3DOpen;
import com.mycompany.virtual_camera.model.spatial_shape.Pyramid;
import com.mycompany.virtual_camera.model.spatial_shape.SpatialShapesCollection;
import com.mycompany.virtual_camera.view.View;
import com.mycompany.virtual_camera.view.ViewportJPanel;
import java.util.Set;

/**
 *
 * @author Paweł Mac
 */
public class RunMVC implements Runnable {
    
    public RunMVC() {
    }
    
    @Override
    public void run() {
        // create spatial shapes
        Cuboid cuboid_0 = new Cuboid(-100, 0,  300,  200,  250,  150);
        Cuboid cuboid_1 = new Cuboid(1000, 0, 2000, 1000, 1000, 1000);
        Cuboid cuboid_2 = new Cuboid(3000, 0, 2000,  600, 2000,  700);
        Cuboid cuboid_3 = new Cuboid(1000, 0, 4000, 1000, 3000, 1000);
        Cuboid cuboid_4 = new Cuboid(3000, 0, 4000,  600, 4000,  900);
        Polyline3DOpen polyline3DOpen_c1 = new Polyline3DOpen(
                new Point3D(2050, 0, 2000),
                new Point3D(2050, 0, 3050),
                new Point3D(1000, 0, 3050)
        );
        Polyline3DOpen polyline3DOpen_c2 = new Polyline3DOpen(
                new Point3D(2950, 0, 2000),
                new Point3D(2950, 0, 3050),
                new Point3D(4000, 0, 3050)
        );
        Polyline3DOpen polyline3DOpen_c3 = new Polyline3DOpen(
                new Point3D(1000, 0, 3950),
                new Point3D(2050, 0, 3950),
                new Point3D(2050, 0, 5000)
        );
        Polyline3DOpen polyline3DOpen_c4 = new Polyline3DOpen(
                new Point3D(2950, 0, 5000),
                new Point3D(2950, 0, 3950),
                new Point3D(4000, 0, 3950)
        );
        Cuboid  cubeHouse = new Cuboid( -2300,   0, 800, 300, 300, 300);
        Pyramid roofHouse = new Pyramid(-2310, 300, 790, 320, 100, 320);
        Pyramid pyramid_1 = new Pyramid(    0, 0, -1000, 230, 146, 230);
        Pyramid pyramid_2 = new Pyramid( -690, 0, -1690, 460, 292, 460);
        Pyramid pyramid_3 = new Pyramid(-1840, 0, -2840, 920, 584, 920);
        // spatialShapesCollection
        SpatialShapesCollection spatialShapesCollection = new SpatialShapesCollection();
        // add spatial shapes to spatialShapesCollection
        spatialShapesCollection.addSpatialShape(cuboid_0);
        spatialShapesCollection.addSpatialShape(cuboid_1);
        spatialShapesCollection.addSpatialShape(cuboid_2);
        spatialShapesCollection.addSpatialShape(cuboid_3);
        spatialShapesCollection.addSpatialShape(cuboid_4);
        spatialShapesCollection.addSpatialShape(polyline3DOpen_c1);
        spatialShapesCollection.addSpatialShape(polyline3DOpen_c2);
        spatialShapesCollection.addSpatialShape(polyline3DOpen_c3);
        spatialShapesCollection.addSpatialShape(polyline3DOpen_c4);
        spatialShapesCollection.addSpatialShape(cubeHouse);
        spatialShapesCollection.addSpatialShape(roofHouse);
        spatialShapesCollection.addSpatialShape(pyramid_1);
        spatialShapesCollection.addSpatialShape(pyramid_2);
        spatialShapesCollection.addSpatialShape(pyramid_3);
        Set<Point3D> point3DsSet = spatialShapesCollection.getPoint3DsSet();
        Set<Edge3D>   edge3DsSet = spatialShapesCollection.getEdge3DsSet();
        // create viewportModel
        ViewportModel viewportModel = new ViewportModel(
                point3DsSet, 
                edge3DsSet, 
                ConstantsForViewportModel.OBSERVER_POINT,
                ConstantsForViewportModel.DISTANCE_FROM_VIEWPORT, 
                ConstantsForViewportModel.VIEWPORT_WIDTH, 
                ConstantsForViewportModel.VIEWPORT_HEIGHT
        );
        // create the view
        View view = new View(
                viewportModel.getViewportWidth(), 
                viewportModel.getViewportHeight()
        );
        // viewportJPanel set the references to 
        // viewportModel Collection of Line2DHolder (Line2D)
        ViewportJPanel viewportJPanel = view.getViewportJPanel();
        viewportJPanel.setLine2DHoldersCollection(viewportModel.getCollectionOfLine2DHolder());
        // add viewportJPanel as Observer
        viewportModel.addObserver(viewportJPanel);
        // controller
        Controller controller = new Controller(viewportModel, view);
    }
}
