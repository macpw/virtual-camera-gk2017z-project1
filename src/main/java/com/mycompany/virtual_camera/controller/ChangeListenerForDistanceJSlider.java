/*
 * Here comes the text of your license
 * Each line should be prefixed with  * 
 */
package com.mycompany.virtual_camera.controller;

import com.mycompany.virtual_camera.model.ViewportModel;
import javax.swing.JLabel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 *
 * @author Paweł Mac
 */
public class ChangeListenerForDistanceJSlider implements ChangeListener {
    
    private final ViewportModel viewportModel;
    private final JLabel distanceJLabel;
    
    public ChangeListenerForDistanceJSlider(final ViewportModel viewportModel, final JLabel distanceJLabel) {
        this.viewportModel = viewportModel;
        this.distanceJLabel = distanceJLabel;
    }
    
    @Override
    public void stateChanged(ChangeEvent e) {
        JSlider sourceJSlider = (JSlider)e.getSource();
        if (!sourceJSlider.getValueIsAdjusting()) {
            int value = sourceJSlider.getValue();
            distanceJLabel.setText(Integer.toString(value));
            viewportModel.setDistanceBetweenObserverAndViewport((double)value);
        }
    }
}
