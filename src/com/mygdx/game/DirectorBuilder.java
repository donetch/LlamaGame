package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import java.util.Random;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author javie
 */
public class DirectorBuilder {
    
    public void constructWeakEnemy (EnemyBuilder builder, Random random, int velXEnemy, int velYEnemy){
        builder.setX(random.nextInt(Gdx.graphics.getWidth()));
        builder.setY(50 + random.nextInt(Gdx.graphics.getHeight() - 50));
        builder.setSize(20 + random.nextInt(10));
        builder.setXSpeed(velXEnemy);
        builder.setYSpeed(velYEnemy);
        builder.setTexture(new Texture(Gdx.files.internal("weakEnemy.png")));
        builder.setHealth(1);
    }
    
    public void constructStrongEnemy(EnemyBuilder builder, Random random, int velXEnemy, int velYEnemy){
        builder.setX(random.nextInt(Gdx.graphics.getWidth()));
        builder.setY(50 + random.nextInt(Gdx.graphics.getHeight() - 50));
        builder.setSize(20 + random.nextInt(10));
        builder.setXSpeed(velXEnemy);
        builder.setYSpeed(velYEnemy);
        builder.setTexture(new Texture(Gdx.files.internal("strongEnemy.png"))); 
        builder.setHealth(2);
    }
}
