package com.mygdx.game;
import java.util.Random;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class StrongEnemy extends Enemy {

    private static final int STRONG_ENEMY_HITS_TO_KILL = 2;
    private static final Texture texture = new Texture(Gdx.files.internal("strongEnemy.png"));
    private static final Random random = new Random();

    public StrongEnemy(int xSpeed, int ySpeed) {
        super(
            random.nextInt(Gdx.graphics.getWidth()), // x
            50 + random.nextInt(Gdx.graphics.getHeight() - 50), // y
            20 + random.nextInt(10), // size
            xSpeed,
            ySpeed,
            texture,
            STRONG_ENEMY_HITS_TO_KILL
        );
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