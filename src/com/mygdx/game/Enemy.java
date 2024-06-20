package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public abstract class Enemy implements Colisionable {
    protected int x;
    protected int y;
    protected int xSpeed;
    protected int ySpeed;
    private boolean destroyed = false;
    protected Sprite spr;
    protected int health;

    /**
     * Constructor para la clase Enemy.
     *
     * @param x      La posici�n x inicial del enemigo.
     * @param y      La posici�n y inicial del enemigo.
     * @param size   El tama�o del enemigo.
     * @param xSpeed La velocidad en el eje x del enemigo.
     * @param ySpeed La velocidad en el eje y del enemigo.
     * @param tx     La textura del enemigo.
     * @param health La salud del enemigo.
     */
    public Enemy(int x, int y, int size, int xSpeed, int ySpeed, Texture tx, int health) {
        spr = new Sprite(tx);
        this.x = x;
        this.y = y;
        this.xSpeed = xSpeed;
        this.ySpeed = ySpeed;
        this.health = health;

        if (x - size < 0) this.x = x + size;
        if (x + size > Gdx.graphics.getWidth()) this.x = x - size;
        if (y - size < 0) this.y = y + size;
        if (y + size > Gdx.graphics.getHeight()) this.y = y - size;

        spr.setPosition(this.x, this.y);
    }

    /**
     * Actualiza la posici�n del enemigo.
     */
    public abstract void update();

    /**
     * Dibuja el enemigo en la pantalla.
     *
     * @param batch El SpriteBatch utilizado para dibujar el enemigo.
     */
    public void draw(SpriteBatch batch) {
        spr.draw(batch);
    }

    /**
     * Verifica si el enemigo ha colisionado con otro objeto.
     *
     * @param other El objeto con el que se est� verificando la colisi�n.
     * @return true si ha ocurrido una colisi�n, false en caso contrario.
     */
    @Override
    public boolean checkCollision(Colisionable other) {
        if(spr.getBoundingRectangle().overlaps(other.getArea())){
            this.takeDamage(1);
            if(this.isDead()){
                this.destroyed = true;
            }
            return true;
        }
     return false;
    }
    
    /**
     * Obtiene el �rea del enemigo.
     *
     * @return El �rea del enemigo como un Rectangle.
     */
    @Override
    public Rectangle getArea() {
        return spr.getBoundingRectangle();
    }

    /**
     * Aplica da�o al enemigo.
     *
     * @param damage La cantidad de da�o a aplicar.
     */
    public void takeDamage(int damage) {
        health -= damage;
    }

    /**
     * Verifica si el enemigo est� muerto.
     *
     * @return true si la salud del enemigo es menor o igual a 0, false en caso contrario.
     */
    public boolean isDead() {
        return health <= 0;
    }

    // Getters y Setters

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public int getXSpeed() {
        return xSpeed;
    }

    @Override
    public void setXSpeed(int xSpeed) {
        this.xSpeed = xSpeed;
    }

    @Override
    public int getYSpeed() {
        return ySpeed;
    }

    @Override
    public void setYSpeed(int ySpeed) {
        this.ySpeed = ySpeed;
    }
}