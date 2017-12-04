/*
 * Here comes the text of your license
 * Each line should be prefixed with  * 
 */
package com.mycompany.virtual_camera.controller;

import com.mycompany.virtual_camera.controller.motion.DocumentListenerForStepTextField;
import com.mycompany.virtual_camera.controller.motion.MoveBackwardAction;
import com.mycompany.virtual_camera.controller.motion.MoveDownwardAction;
import com.mycompany.virtual_camera.controller.motion.MoveForwardAction;
import com.mycompany.virtual_camera.controller.motion.MoveLeftAction;
import com.mycompany.virtual_camera.controller.motion.MoveRightAction;
import com.mycompany.virtual_camera.controller.motion.MoveUpwardAction;
import com.mycompany.virtual_camera.controller.rotation.RotateDownwardAction;
import com.mycompany.virtual_camera.controller.rotation.RotateLeftAction;
import com.mycompany.virtual_camera.controller.rotation.RotateRightAction;
import com.mycompany.virtual_camera.controller.rotation.RotateTiltLeftAction;
import com.mycompany.virtual_camera.controller.rotation.RotateTiltRightAction;
import com.mycompany.virtual_camera.controller.rotation.RotateUpwardAction;
import com.mycompany.virtual_camera.controller.rotation.SetAngleAction;
import com.mycompany.virtual_camera.model.ViewportModel;
import com.mycompany.virtual_camera.view.View;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.KeyStroke;

/**
 *
 * @author Paweł Mac
 */
public class Controller {
    
    private final ViewportModel viewportModel;
    private final View view;

    public Controller(ViewportModel viewportModel, View view) {
        this.viewportModel = viewportModel;
        this.view = view;
        addListenerToDistanceJPanel();
        addListenersToMotionButtons();
        addListenersToRotationButtons();
    }
    
    private void addListenerToDistanceJPanel() {
        JLabel distanceJLabel   = view.getDistanceJPanel().getDistanceJLabel();
        JSlider distanceJSlider = view.getDistanceJPanel().getDistanceJSlider();
        ChangeListenerForDistanceJSlider changeListenerForDistanceJSlider = new ChangeListenerForDistanceJSlider(viewportModel, distanceJLabel);
        distanceJSlider.addChangeListener(changeListenerForDistanceJSlider);
        distanceJSlider.setValue((int)viewportModel.getDistanceBetweenObserverAndViewport());
        distanceJLabel.setText(Double.toString(viewportModel.getDistanceBetweenObserverAndViewport()));
    }
    
    private void addListenersToMotionButtons() {
        JButton moveForwardJButton  = view.getMotionControlJPanel().getMoveForwardJButton();
        JButton moveBackwardJButton = view.getMotionControlJPanel().getMoveBackwardJButton();
        JButton moveLeftJButton     = view.getMotionControlJPanel().getMoveLeftJButton();
        JButton moveRightJButton    = view.getMotionControlJPanel().getMoveRightJButton();
        JButton moveUpwardJButton   = view.getMotionControlJPanel().getMoveUpwardJButton();
        JButton moveDownwardJButton = view.getMotionControlJPanel().getMoveDownwardJButton();
        
        MoveForwardAction  moveForwardAction  = new MoveForwardAction (viewportModel);
        MoveBackwardAction moveBackwardAction = new MoveBackwardAction(viewportModel);
        MoveLeftAction     moveLeftAction     = new MoveLeftAction    (viewportModel);
        MoveRightAction    moveRightAction    = new MoveRightAction   (viewportModel);
        MoveUpwardAction   moveUpwardAction   = new MoveUpwardAction  (viewportModel);
        MoveDownwardAction moveDownwardAction = new MoveDownwardAction(viewportModel);
        
        moveForwardJButton .addActionListener(moveForwardAction);
        moveBackwardJButton.addActionListener(moveBackwardAction);
        moveLeftJButton    .addActionListener(moveLeftAction);
        moveRightJButton   .addActionListener(moveRightAction);
        moveUpwardJButton  .addActionListener(moveUpwardAction);
        moveDownwardJButton.addActionListener(moveDownwardAction);
        
        moveForwardJButton.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0), "move forward");
        moveForwardJButton.getActionMap().put("move forward", moveForwardAction);
        
        moveBackwardJButton.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0), "move backward");
        moveBackwardJButton.getActionMap().put("move backward", moveBackwardAction);
        
        moveLeftJButton.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0), "move left");
        moveLeftJButton.getActionMap().put("move left", moveLeftAction);
        
        moveRightJButton.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0), "move right");
        moveRightJButton.getActionMap().put("move right", moveRightAction);
        
        moveUpwardJButton.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_UP, InputEvent.CTRL_DOWN_MASK), "move upward");
        moveUpwardJButton.getActionMap().put("move upward", moveUpwardAction);
        
        moveDownwardJButton.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, InputEvent.CTRL_DOWN_MASK), "move upward");
        moveDownwardJButton.getActionMap().put("move upward", moveDownwardAction);
        
        
        // StepTextField
        JTextField stepTextField = this.view.getMotionControlJPanel().getStepTextField();
        stepTextField.setText(Double.toString(viewportModel.getStep()));
        stepTextField.setToolTipText("step="+viewportModel.getStep());
        DocumentListenerForStepTextField documentListenerForStepTextField = 
                new DocumentListenerForStepTextField(viewportModel, stepTextField);
        stepTextField.getDocument().addDocumentListener(documentListenerForStepTextField);
    }
    
    private void addListenersToRotationButtons() {
        JTextField angleJTextField = view.getRotationControlJPanel().getAngleJTextField();
        JButton    angleSetJButton = view.getRotationControlJPanel().getAngleSetJButton();
        angleJTextField.setText(Double.toString(viewportModel.getAngleInDegrees()));
        angleJTextField.setToolTipText("angle="+viewportModel.getAngleInDegrees());
        SetAngleAction setAngleAction = new SetAngleAction(viewportModel, angleJTextField);
        angleSetJButton.addActionListener(setAngleAction);
        
        JButton rotateLeftJButton      = view.getRotationControlJPanel().getRotateLeftJButton();
        JButton rotateRightJButton     = view.getRotationControlJPanel().getRotateRightJButton();
        JButton rotateUpwardJButton    = view.getRotationControlJPanel().getRotateUpwardJButton();
        JButton rotateDownwardJButton  = view.getRotationControlJPanel().getRotateDownwardJButton();
        JButton rotateTiltLeftJButton  = view.getRotationControlJPanel().getRotateTiltLeftJButton();
        JButton rotateTiltRightJButton = view.getRotationControlJPanel().getRotateTiltRightJButton();
        
        RotateLeftAction      rotateLeftAction      = new RotateLeftAction     (viewportModel);
        RotateRightAction     rotateRightAction     = new RotateRightAction    (viewportModel);
        RotateUpwardAction    rotateUpwardAction    = new RotateUpwardAction   (viewportModel);
        RotateDownwardAction  rotateDownwardAction  = new RotateDownwardAction (viewportModel);
        RotateTiltLeftAction  rotateTiltLeftAction  = new RotateTiltLeftAction (viewportModel);
        RotateTiltRightAction rotateTiltRightAction = new RotateTiltRightAction(viewportModel);
        
        rotateLeftJButton     .addActionListener(rotateLeftAction);
        rotateRightJButton    .addActionListener(rotateRightAction);
        rotateUpwardJButton   .addActionListener(rotateUpwardAction);
        rotateDownwardJButton .addActionListener(rotateDownwardAction);
        rotateTiltLeftJButton .addActionListener(rotateTiltLeftAction);
        rotateTiltRightJButton.addActionListener(rotateTiltRightAction);
        
        rotateLeftJButton.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, InputEvent.SHIFT_DOWN_MASK), "rotate left");
        rotateLeftJButton.getActionMap().put("rotate left", rotateLeftAction);
        
        rotateRightJButton.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, InputEvent.SHIFT_DOWN_MASK), "rotate right");
        rotateRightJButton.getActionMap().put("rotate right", rotateRightAction);
        
        rotateUpwardJButton.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_UP, InputEvent.SHIFT_DOWN_MASK), "rotate upward");
        rotateUpwardJButton.getActionMap().put("rotate upward", rotateUpwardAction);
        
        rotateDownwardJButton.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, InputEvent.SHIFT_DOWN_MASK), "rotate downward");
        rotateDownwardJButton.getActionMap().put("rotate downward", rotateDownwardAction);
        
        rotateTiltLeftJButton.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, InputEvent.SHIFT_DOWN_MASK | InputEvent.CTRL_DOWN_MASK), "rotate tilt left");
        rotateTiltLeftJButton.getActionMap().put("rotate tilt left", rotateTiltLeftAction);
        
        rotateTiltRightJButton.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, InputEvent.SHIFT_DOWN_MASK | InputEvent.CTRL_DOWN_MASK), "rotate tilt right");
        rotateTiltRightJButton.getActionMap().put("rotate tilt right", rotateTiltRightAction);
    }
}
