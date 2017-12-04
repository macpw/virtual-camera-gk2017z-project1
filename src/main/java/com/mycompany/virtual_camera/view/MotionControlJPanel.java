/*
 * Here comes the text of your license
 * Each line should be prefixed with  * 
 */
package com.mycompany.virtual_camera.view;

import com.mycompany.virtual_camera.view.util.FloatingPointFilter;
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
public final class MotionControlJPanel extends JPanel {
    
    final JLabel stepJLabel = new JLabel("step:");
    final JTextField stepTextField;
    
    final JButton moveForwardJButton;
    final JButton moveBackwardJButton;
    final JButton moveLeftJButton;
    final JButton moveRightJButton;
    final JButton moveUpwardJButton;
    final JButton moveDownwardJButton;
    
    public MotionControlJPanel() {
        this.stepTextField = new JTextField(1);
        this.stepTextField.setHorizontalAlignment(JTextField.RIGHT);
        Document document = this.stepTextField.getDocument();
        ((AbstractDocument)document).setDocumentFilter(new FloatingPointFilter());
        
        this.moveForwardJButton  = new JButton("↑");
        this.moveBackwardJButton = new JButton("↓");
        this.moveLeftJButton     = new JButton("←");
        this.moveRightJButton    = new JButton("→");
        this.moveUpwardJButton   = new JButton("↥");
        this.moveDownwardJButton = new JButton("↧");
        
        this.moveForwardJButton .setToolTipText("move forward");
        this.moveBackwardJButton.setToolTipText("move backward");
        this.moveLeftJButton    .setToolTipText("move left");
        this.moveRightJButton   .setToolTipText("move right");
        this.moveUpwardJButton  .setToolTipText("move upward");
        this.moveDownwardJButton.setToolTipText("move downward");
        
        this.moveForwardJButton .setActionCommand("move forward");
        this.moveBackwardJButton.setActionCommand("move backward");
        this.moveLeftJButton    .setActionCommand("move left");
        this.moveRightJButton   .setActionCommand("move right");
        this.moveUpwardJButton  .setActionCommand("move upward");
        this.moveDownwardJButton.setActionCommand("move downward");
        
        this.setBorder(new TitledBorder("motion control"));
        this.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        
        this.add(stepJLabel, gbc);
        
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        this.add(stepTextField, gbc);
        
        gbc.fill = GridBagConstraints.NONE;// defualt
        gbc.gridwidth = 1;// defualut
        
        gbc.gridy = 1;
        gbc.gridx = 1;
        this.add(moveForwardJButton, gbc);
        gbc.gridy = 2;
        this.add(moveBackwardJButton, gbc);
        gbc.gridx = 0;
        this.add(moveLeftJButton, gbc);
        gbc.gridx = 2;
        this.add(moveRightJButton, gbc);
        gbc.gridy = 1;
        gbc.gridx = 3;
        this.add(moveUpwardJButton, gbc);
        gbc.gridy = 2;
        this.add(moveDownwardJButton, gbc);
    }
    
    // Getters
    
    public JTextField getStepTextField() {    
        return stepTextField;
    }
    
    public JButton getMoveForwardJButton() {
        return moveForwardJButton;
    }
    
    public JButton getMoveBackwardJButton() {
        return moveBackwardJButton;
    }
    
    public JButton getMoveLeftJButton() {
        return moveLeftJButton;
    }
    
    public JButton getMoveRightJButton() {    
        return moveRightJButton;
    }
    
    public JButton getMoveUpwardJButton() {
        return moveUpwardJButton;
    }
    
    public JButton getMoveDownwardJButton() {
        return moveDownwardJButton;
    }
    
    // Methods
    
    // test
    public static void main(String[] args) {
        JFrame jFrame = new JFrame(MotionControlJPanel.class.getSimpleName()+" -- test");
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        JPanel mainJPanel = new JPanel();
        jFrame.add(mainJPanel);
        
        MotionControlJPanel stepControlJPanel = new MotionControlJPanel();
        mainJPanel.add(stepControlJPanel);
        
        jFrame.pack();
        jFrame.setLocationRelativeTo(null);
        jFrame.setVisible(true);
    }
}
