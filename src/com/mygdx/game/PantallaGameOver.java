package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;

public class PantallaGameOver extends Pantalla {

    private Music gameOverMusic;
    private Texture backgroundImage;

    public PantallaGameOver(SpaceNavigation game) {
        super(game);
    }

    @Override
    protected void initializeAssets() {
        gameOverMusic = Gdx.audio.newMusic(Gdx.files.internal("gameover-sound.wav"));
        gameOverMusic.setLooping(true);
        gameOverMusic.setVolume(0.5f);
        gameOverMusic.play();
        backgroundImage = new Texture(Gdx.files.internal("gameOverScreen.jpg"));
    }

    @Override
    protected void initializeComponents() {
        // Any additional components initialization
    }

    @Override
    protected void renderComponents(float delta) {
        batch.draw(backgroundImage, 0, 0, 1200, 800);
    }

    @Override
    protected void handleInput() {
        if (Gdx.input.isTouched() || Gdx.input.isKeyJustPressed(Input.Keys.ANY_KEY)) {
            PantallaJuego pantallaJuego = new PantallaJuego(game, 1, 3, 0, 1, 1, 10);
            pantallaJuego.resize(1200, 800);
            game.setScreen(pantallaJuego);
            dispose();
        }
    }

    @Override
    public void dispose() {
        super.dispose();
        gameOverMusic.dispose();
        backgroundImage.dispose();
    }
}
