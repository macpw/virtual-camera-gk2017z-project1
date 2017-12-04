/*
 * Here comes the text of your license
 * Each line should be prefixed with  * 
 */
package com.mycompany.virtual_camera.controller.motion;

import com.mycompany.virtual_camera.model.ViewportModel;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/**
 *
 * @author Paweł Mac
 */
public class DocumentListenerForStepTextField implements DocumentListener {
    
    private final JTextField stepTextField;
    private final ViewportModel viewportModel;
    
    public DocumentListenerForStepTextField(ViewportModel viewportModel, JTextField stepTextField) {
        this.stepTextField = stepTextField;
        this.viewportModel = viewportModel;
    }
    
    private void doUpdate() {
        try {
            double parseDouble = Double.parseDouble(stepTextField.getText());
            viewportModel.setStep(parseDouble);
            if (!Color.white.equals(stepTextField.getBackground())) {
                stepTextField.setBackground(Color.white);
            }
            stepTextField.setToolTipText("step="+viewportModel.getStep());
        } catch (NumberFormatException exception) {
            stepTextField.setBackground(Color.orange);
        }
    }
    
    @Override
    public void insertUpdate(DocumentEvent e) {
        doUpdate();
    }
    
    @Override
    public void removeUpdate(DocumentEvent e) {
        doUpdate();
    }
    
    @Override
    public void changedUpdate(DocumentEvent e) {
        // never used
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
