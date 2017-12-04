/*
 * Here comes the text of your license
 * Each line should be prefixed with  * 
 */
package com.mycompany.virtual_camera.controller.rotation;

import com.mycompany.virtual_camera.model.ViewportModel;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;

/**
 *
 * @author Paweł Mac
 */
public class RotateRightAction extends AbstractAction {
    
    private final ViewportModel viewportModel;
    
    public RotateRightAction(ViewportModel viewportModel) {
        this.viewportModel = viewportModel;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        viewportModel.rotateRight();
    }
}
