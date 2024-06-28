package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class StrongEnemy extends Enemy {

    public StrongEnemy(int x, int y, int size, int xSpeed, int ySpeed, Texture texture, int health) {
        super(x, y, size, xSpeed, ySpeed, texture, health);
    }

    @Override
    public void update(Llama llama) {   
        x += xSpeed;
        y += ySpeed;

        if (x + xSpeed < 0 || x + xSpeed + spr.getWidth() > Gdx.graphics.getWidth()) xSpeed *= -1;
        if (y + ySpeed < 0 || y + ySpeed + spr.getHeight() > Gdx.graphics.getHeight()) ySpeed *= -1;
        spr.setPosition(x, y);
        
        if(!llama.isInvulnerable()){
            checkCollision(llama);
        }
    }
}
