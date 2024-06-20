package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import java.util.Iterator;

public class StrongEnemy extends Enemy {

    private static final int STRONG_ENEMY_HITS_TO_KILL = 2;

    public StrongEnemy(int x, int y, int size, int xSpeed, int ySpeed, Texture texture) {
        super(x, y, size, xSpeed, ySpeed, texture, STRONG_ENEMY_HITS_TO_KILL);
    }

    @Override
    public void update() {   
        x += xSpeed;
        y += ySpeed;

        if (x + xSpeed < 0 || x + xSpeed + spr.getWidth() > Gdx.graphics.getWidth()) xSpeed *= -1;
        if (y + ySpeed < 0 || y + ySpeed + spr.getHeight() > Gdx.graphics.getHeight()) ySpeed *= -1;
        spr.setPosition(x, y);
    }
}