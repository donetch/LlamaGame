package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class WeakEnemy extends Enemy {
    private static final int HITS_TO_KILL = 1;
    private static final float SHOOT_COOLDOWN = 1.0f;
    private static final int BULLET_SPEED_Y = -2;
    
    private List<Bullet> bulletsWE;
    private float timeSinceLastShot;
    
    private static final Texture weakEnemyTexture = new Texture(Gdx.files.internal("weakEnemy.png"));
    private static final Texture bulletTexture = new Texture(Gdx.files.internal("rocket3.png"));
    private static final Random random = new Random();

    public WeakEnemy(int xSpeed, int ySpeed) {
        super(
            random.nextInt(Gdx.graphics.getWidth()), // x
            50 + random.nextInt(Gdx.graphics.getHeight() - 50), // y
            20 + random.nextInt(10), // size
            xSpeed,
            ySpeed,
            weakEnemyTexture,
            HITS_TO_KILL
        );
        this.bulletsWE = new ArrayList<>();
        this.timeSinceLastShot = 0;
    }


    @Override
    public void update(Llama llama) {
                
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

        for (Iterator<Bullet> it = bulletsWE.iterator(); it.hasNext(); ) {
            Bullet bullet = it.next();
            bullet.update();
            if(!llama.isInvulnerable()){
                llama.checkCollision(bullet);
            }
        }
        
        if(!llama.isInvulnerable()){
           checkCollision(llama);   
        }
        
    }

    @Override
    public void draw(SpriteBatch batch) {
        super.draw(batch);
        for (Bullet bullet : bulletsWE) {
            bullet.draw(batch);
        }
    }

    private void shoot() {
        // Crear una bala que se mueva hacia abajo
        bulletsWE.add(new Bullet(
            spr.getX() + spr.getWidth() / 2, // Posición x
            spr.getY(), // Posición y
            0, // Velocidad x
            BULLET_SPEED_Y, // Velocidad y (hacia abajo)
            bulletTexture // Textura de la bala
        ));
    }
    
    public List<Bullet> getBullets() {
        return bulletsWE;
    }
}
