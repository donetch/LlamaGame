/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

/**
 *
 * @author javie
 */
public abstract class Pantalla implements Screen {

    protected final SpaceNavigation game;
    protected final OrthographicCamera camera;
    protected final SpriteBatch batch;

    public Pantalla(SpaceNavigation game) {
        this.game = game;
        this.camera = new OrthographicCamera();
        this.batch = game.getBatch();
        initialize();
    }

    private void initialize() {
        initializeCamera();
        initializeAssets();
        initializeComponents();
    }
    
    protected abstract void initializeAssets();
    protected abstract void initializeComponents();
    
    @Override
    public void render(float delta) {
        clearContent();
        cameraUpdate();
        batchIniFin(1);
        renderComponents(delta);
        batchIniFin(2);
        handleInput();
    }
    
    private void clearContent(){
        ScreenUtils.clear(0, 0, 0.2f, 1);
    }
    
        
    private void cameraUpdate(){
        camera.update();
        batch.setProjectionMatrix(camera.combined);
    }
    
    private void batchIniFin(int n){
        if(n == 1){
            batch.begin();
        }
        if(n == 2){
            batch.end();
        }
    }
   
    protected abstract void renderComponents(float delta);
    protected abstract void handleInput();
    
    private void initializeCamera() {
        camera.setToOrtho(false, 1200, 800);
    }
     
    @Override
    public void show(){
    
    }

    @Override
    public void resize(int i, int i1){
        
    }

    @Override
    public void pause(){
        
    }

    @Override
    public void resume(){
        
    }

    @Override
    public void hide(){
        
    }

    @Override
    public void dispose(){
        
    }
}
