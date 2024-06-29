package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;

public class EnemyBuilder {
    private int x;
    private int y;
    private int size;
    private int xSpeed;
    private int ySpeed;
    private Texture texture;
    private int health;

    public EnemyBuilder setX(int x) {
        this.x = x;
        return this;
    }

    public EnemyBuilder setY(int y) {
        this.y = y;
        return this;
    }

    public EnemyBuilder setSize(int size) {
        this.size = size;
        return this;
    }

    public EnemyBuilder setXSpeed(int xSpeed) {
        this.xSpeed = xSpeed;
        return this;
    }

    public EnemyBuilder setYSpeed(int ySpeed) {
        this.ySpeed = ySpeed;
        return this;
    }

    public EnemyBuilder setTexture(Texture texture) {
        this.texture = texture;
        return this;
    }

    public EnemyBuilder setHealth(int health) {
        this.health = health;
        return this;
    }

    public Enemy buildWeakEnemy() {
        return new WeakEnemy(x, y, size, xSpeed, ySpeed, texture, health);
    }

    public Enemy buildStrongEnemy() {
        return new StrongEnemy(x, y, size, xSpeed, ySpeed, texture, health);
    }
}
