/*
 * Here comes the text of your license
 * Each line should be prefixed with  * 
 */
package com.mycompany.virtual_camera.view;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 *
 * @author Paweł Mac
 */
public final class DistanceJPanel extends JPanel {
    
    final JLabel  distanceJLabel  = new JLabel("distance");
    final JSlider distanceJSlider = new JSlider();
    
    public DistanceJPanel() {
        distanceJSlider.setMinimum(0);
        distanceJSlider.setMaximum(2000);
        distanceJSlider.setMajorTickSpacing(200);
        distanceJSlider.setMinorTickSpacing(50);
        distanceJSlider.setPaintTicks(true);
        distanceJSlider.setPaintLabels(true);
        distanceJSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                if (((JSlider)e.getSource()).getValue() < 10) {
                    ((JSlider)e.getSource()).setValue(10);
                }
            }
        });
        
        this.setBorder(new TitledBorder("distance between observer and viewport"));
        
        this.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 1;
        this.add(distanceJLabel, gbc);
        gbc.weightx = 1.0d;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        this.add(distanceJSlider, gbc);
    }
    
    // Getters
    
    public JSlider getDistanceJSlider() {
        return distanceJSlider;
    }
    
    public JLabel getDistanceJLabel() {
        return distanceJLabel;
    }
    
    // test
    public static void main(String[] args) {
        JFrame jFrame = new JFrame(DistanceJPanel.class.getSimpleName() + " test");
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setMinimumSize(new Dimension(200, 100));
        
        JPanel mainJPanel = new JPanel();
        jFrame.add(mainJPanel);
        DistanceJPanel zoomJPanel = new DistanceJPanel();
        zoomJPanel.setPreferredSize(new Dimension(400, 150));
        mainJPanel.add(zoomJPanel);
        
        jFrame.pack();
        jFrame.setLocationRelativeTo(null);
        jFrame.setVisible(true);
    }
}
