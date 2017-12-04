/*
 * Here comes the text of your license
 * Each line should be prefixed with  * 
 */
package com.mycompany.virtual_camera.controller.rotation;

import com.mycompany.virtual_camera.model.ViewportModel;
import java.awt.Color;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.JTextField;

/**
 *
 * @author Paweł Mac
 */
public final class SetAngleAction extends AbstractAction {
    
    private final ViewportModel viewportModel;
    private final JTextField angleJTextField;
    
    public SetAngleAction(ViewportModel viewportModel, JTextField angleJTextField) {
        this.viewportModel = viewportModel;
        this.angleJTextField = angleJTextField;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            double angleInDegrees = Double.parseDouble(angleJTextField.getText());
            viewportModel.setAngleInDegrees(angleInDegrees);
            if (!Color.white.equals(angleJTextField.getBackground())) {
                angleJTextField.setBackground(Color.white);
            }
            angleJTextField.setToolTipText("angle="+viewportModel.getAngleInDegrees());
        } catch (NumberFormatException nfe) {
            angleJTextField.setBackground(Color.orange);
        }
    }
}
