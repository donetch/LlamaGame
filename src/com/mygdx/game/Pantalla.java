package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

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

    protected void initializeCamera() {
        camera.setToOrtho(false, 1200, 800);
    }

    protected abstract void initializeAssets();
    protected abstract void initializeComponents();
    protected abstract void renderComponents(float delta);

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0.2f, 1);
        camera.update();
        batch.setProjectionMatrix(camera.combined);

        batch.begin();
        renderComponents(delta);
        batch.end();

        handleInput();
    }

    protected abstract void handleInput();

    @Override
    public void show() {
        // Método opcional
    }

    @Override
    public void resize(int width, int height) {
        // Método opcional
    }

    @Override
    public void pause() {
        // Método opcional
    }

    @Override
    public void resume() {
        // Método opcional
    }

    @Override
    public void hide() {
        // Método opcional
    }

    @Override
    public void dispose() {
        // Método opcional
    }
}
