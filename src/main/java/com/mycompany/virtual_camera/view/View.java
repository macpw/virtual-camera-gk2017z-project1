/*
 * Here comes the text of your license
 * Each line should be prefixed with  * 
 */
package com.mycompany.virtual_camera.view;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author Paweł Mac
 */
public class View {

    final ViewportJPanel viewportJPanel;
    final JPanel controlJPanel;
    final MotionControlJPanel motionControlJPanel;
    final RotationControlJPanel rotationControlJPanel;
    final DistanceJPanel distanceJPanel;
    
    public View(int viewportWidth, int viewportHeight) {
        this.viewportJPanel = new ViewportJPanel(viewportWidth, viewportHeight);
        this.controlJPanel = new JPanel();
        this.motionControlJPanel = new MotionControlJPanel();
        this.rotationControlJPanel = new RotationControlJPanel();
        this.distanceJPanel = new DistanceJPanel();
        this.createAndShowGui();
    }
    
    // Getters
    
    public ViewportJPanel getViewportJPanel() {
        return viewportJPanel;
    }
    
    public MotionControlJPanel getMotionControlJPanel() {
        return motionControlJPanel;
    }
    
    public RotationControlJPanel getRotationControlJPanel() {
        return rotationControlJPanel;
    }
    
    public DistanceJPanel getDistanceJPanel() {
        return distanceJPanel;
    }
    
    // Methods
    
    private void createAndShowGui() {
        JFrame jFrame = new JFrame();
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setMinimumSize(new Dimension(100, 75));
        
        JPanel mainJPanel = new JPanel();
        jFrame.add(mainJPanel);
        this.addComponents(mainJPanel);
        
        jFrame.pack();
        jFrame.setLocationRelativeTo(null);
        jFrame.setVisible(true);
    }
    
    private void addComponents(Container pane) {
        GridBagLayout gridBagLayout = new GridBagLayout();
        pane.setLayout(gridBagLayout);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        pane.add(viewportJPanel, gbc);
        pane.add(controlJPanel, gbc);
        
        gbc.gridx = GridBagConstraints.RELATIVE;// default
        
        controlJPanel.setLayout(new GridBagLayout());
        
        gbc.gridy = 0;
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        controlJPanel.add(distanceJPanel, gbc);
        gbc.gridy = 1;
        gbc.gridwidth = 1;// defualut
        controlJPanel.add(motionControlJPanel, gbc);
        controlJPanel.add(rotationControlJPanel, gbc);
    }
    
    // test view
    public static void main(String[] args) {
        int viewportWidth  = 600;
        int viewportHeight = 400;
        new View(viewportWidth, viewportHeight);
    }
}
