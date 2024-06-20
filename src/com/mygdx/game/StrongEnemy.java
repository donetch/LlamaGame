package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;

public class StrongEnemy extends Enemy {

    private static final int STRONG_ENEMY_HITS_TO_KILL = 2;

    public StrongEnemy(int x, int y, int size, int xSpeed, int ySpeed, Texture texture) {
        super(x, y, size, xSpeed, ySpeed, texture, STRONG_ENEMY_HITS_TO_KILL);
    }

    @Override
    public void attack() {
        // StrongEnemy no dispara, pero tiene da�o por colisi�n
        // En futuras entregas se planea hacer que StrongEnemy tambi�n realice ataques
    }
}