
package com.mygdx.game;

import com.badlogic.gdx.math.Rectangle;

public interface Colisionable {
    
    public void checkCollision(Colisionable other);
    public void handleCollision();
    public Rectangle getArea();
    public int getXSpeed();
    public int getYSpeed();
    public void setXSpeed(int xSpeed);
    public void setYSpeed(int xSpeed);
}
