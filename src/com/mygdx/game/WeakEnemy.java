package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class WeakEnemy extends Enemy {
    private static final int HITS_TO_KILL = 1;
    private static final float SHOOT_COOLDOWN = 1.0f;
    private static final int BULLET_SPEED_Y = -2;

    private List<Bullet> bullets;
    private Texture bulletTexture;
    private float timeSinceLastShot;

    public WeakEnemy(int x, int y, int size, int xSpeed, int ySpeed, Texture tx, Texture bulletTx) {
        super(x, y, size, xSpeed, ySpeed, tx, HITS_TO_KILL);
        this.bulletTexture = bulletTx;
        this.bullets = new ArrayList<>();
        this.timeSinceLastShot = 0;
    }

    @Override
    public void update() {
                
        x += xSpeed;
        y += ySpeed;

        if (x + xSpeed < 0 || x + xSpeed + spr.getWidth() > Gdx.graphics.getWidth()) xSpeed *= -1;
        if (y + ySpeed < 0 || y + ySpeed + spr.getHeight() > Gdx.graphics.getHeight()) ySpeed *= -1;
        spr.setPosition(x, y);
        
        timeSinceLastShot += Gdx.graphics.getDeltaTime();
        if (timeSinceLastShot >= SHOOT_COOLDOWN) {
            shoot();
            timeSinceLastShot = 0;
        }

        for (Iterator<Bullet> it = bullets.iterator(); it.hasNext(); ) {
            Bullet bullet = it.next();
            bullet.update();
        }
        
        checkColllion();
    }

    @Override
    public void draw(SpriteBatch batch) {
        super.draw(batch);
        for (Bullet bullet : bullets) {
            bullet.draw(batch);
        }
    }

    private void shoot() {
        // Crear una bala que se mueva hacia abajo
        bullets.add(new Bullet(
            spr.getX() + spr.getWidth() / 2, // Posici�n x
            spr.getY(), // Posici�n y
            0, // Velocidad x
            BULLET_SPEED_Y, // Velocidad y (hacia abajo)
            bulletTexture // Textura de la bala
        ));
    }

    public List<Bullet> getBullets() {
        return bullets;
    }
}