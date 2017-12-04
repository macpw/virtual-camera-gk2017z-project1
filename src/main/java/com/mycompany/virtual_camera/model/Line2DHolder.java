/*
 * Here comes the text of your license
 * Each line should be prefixed with  * 
 */
package com.mycompany.virtual_camera.model;

import java.awt.geom.Line2D;

/**
 *
 * @author Paweł Mac
 */
public class Line2DHolder {
    
    final Line2D line2D;
    private boolean inFrontOfViewportFirst;
    private boolean inFrontOfViewportSecond;

    public Line2DHolder(Line2D line2D) {
        this.line2D = line2D;
        this.inFrontOfViewportFirst = true;
        this.inFrontOfViewportSecond = true;
    }

    public Line2D getLine2D() {
        return line2D;
    }

    public boolean isInFrontOfViewportFirst() {
        return inFrontOfViewportFirst;
    }

    public void setInFrontOfViewportFirst(boolean inFrontOfViewportFirst) {
        this.inFrontOfViewportFirst = inFrontOfViewportFirst;
    }

    public boolean isInFrontOfViewportSecond() {
        return inFrontOfViewportSecond;
    }

    public void setInFrontOfViewportSecond(boolean inFrontOfViewportSecond) {
        this.inFrontOfViewportSecond = inFrontOfViewportSecond;
    }
}
