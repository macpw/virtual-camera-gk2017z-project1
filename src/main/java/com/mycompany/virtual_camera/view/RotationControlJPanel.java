/*
 * Here comes the text of your license
 * Each line should be prefixed with  * 
 */
package com.mycompany.virtual_camera.view;

import com.mycompany.virtual_camera.view.util.FloatingPointFilter;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.text.AbstractDocument;
import javax.swing.text.Document;

/**
 *
 * @author Paweł Mac
 */
public final class RotationControlJPanel extends JPanel {
    
    final JLabel angleJLabel = new JLabel("angle:");
    
    final JTextField angleJTextField;
    final JButton angleSetJButton;
    
    final JButton rotateLeftJButton;
    final JButton rotateRightJButton;
    final JButton rotateUpwardJButton;
    final JButton rotateDownwardJButton;
    final JButton rotateTiltLeftJButton;
    final JButton rotateTiltRightJButton;
    
    final JPanel rotateJButtonsJPanel;
    
    public RotationControlJPanel() {
        this.angleJTextField = new JTextField(8);
        this.angleJTextField.setHorizontalAlignment(JTextField.CENTER);
        Document document = angleJTextField.getDocument();
        ((AbstractDocument)document).setDocumentFilter(new FloatingPointFilter());
        this.angleSetJButton = new JButton("set");
        
        this.rotateLeftJButton = new JButton("↶");
        this.rotateLeftJButton.setToolTipText("rotate left");
        this.rotateLeftJButton.setActionCommand("rotate left");
        
        this.rotateRightJButton = new JButton("↷");
        this.rotateRightJButton.setToolTipText("rotate right");
        this.rotateRightJButton.setActionCommand("rotate right");
        
        this.rotateUpwardJButton = new JButton("⤴");
        this.rotateUpwardJButton.setToolTipText("rotate upward");
        this.rotateUpwardJButton.setActionCommand("rotate upward");
        
        this.rotateDownwardJButton = new JButton("⤵");
        this.rotateDownwardJButton.setToolTipText("rotate downward");
        this.rotateDownwardJButton.setActionCommand("rotate downward");
        
        this.rotateTiltLeftJButton = new JButton("↺");
        this.rotateTiltLeftJButton.setToolTipText("tilt left (rotate z-axis)");
        this.rotateTiltLeftJButton.setActionCommand("rotate tilt left");
        
        this.rotateTiltRightJButton = new JButton("↻");
        this.rotateTiltRightJButton.setToolTipText("tilt right (rotate z-axis)");
        this.rotateTiltRightJButton.setActionCommand("rotate tilt right");
        
        this.rotateJButtonsJPanel = new JPanel();
        // add rotateJButtons To rotateJButtonsJPanel
        this.addRotateJButtonsToRotateJButtonsJPanel();
        
        this.setBorder(new TitledBorder("rotation control"));
        this.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        
        gbc.gridy = 0;
        this.add(angleJLabel, gbc);
        this.add(angleJTextField, gbc);
        this.add(angleSetJButton, gbc);
        
        gbc.gridy = 1;
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        this.add(rotateJButtonsJPanel, gbc);
    }
    
    // Getters and Setters
    
    public JTextField getAngleJTextField() {
        return angleJTextField;
    }
    
    public JButton getAngleSetJButton() {
        return angleSetJButton;
    }
    
    public JButton getRotateLeftJButton() {
        return rotateLeftJButton;
    }
    
    public JButton getRotateRightJButton() {
        return rotateRightJButton;
    }
    
    public JButton getRotateUpwardJButton() {
        return rotateUpwardJButton;
    }
    
    public JButton getRotateDownwardJButton() {
        return rotateDownwardJButton;
    }
    
    public JButton getRotateTiltLeftJButton() {
        return rotateTiltLeftJButton;
    }
    
    public JButton getRotateTiltRightJButton() {
        return rotateTiltRightJButton;
    }
    
    // Methods
    
    private void addRotateJButtonsToRotateJButtonsJPanel() {
        rotateJButtonsJPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridy = 0;
        gbc.gridx = 0;
        rotateJButtonsJPanel.add(rotateTiltLeftJButton, gbc);
        gbc.gridx = 1;
        rotateJButtonsJPanel.add(rotateUpwardJButton, gbc);
        gbc.gridx = 2;
        rotateJButtonsJPanel.add(rotateTiltRightJButton, gbc);
        gbc.gridy = 1;
        gbc.gridx = 0;
        rotateJButtonsJPanel.add(rotateLeftJButton, gbc);
        gbc.gridx = 1;
        rotateJButtonsJPanel.add(rotateDownwardJButton, gbc);
        gbc.gridx = 2;
        rotateJButtonsJPanel.add(rotateRightJButton, gbc);
    }
    
    // test
    public static void main(String[] args) {
        JFrame jFrame = new JFrame(RotationControlJPanel.class.getName() + " -- test");
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setMinimumSize(new Dimension(100, 75));
        
        JPanel mainJPanel = new JPanel();
        jFrame.add(mainJPanel);
        
        RotationControlJPanel rotationControlJPanel = new RotationControlJPanel();
        mainJPanel.add(rotationControlJPanel);
        
        jFrame.pack();
        jFrame.setLocationRelativeTo(null);
        jFrame.setVisible(true);
    }
}
