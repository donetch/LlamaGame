package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;

public class PantallaInstrucciones extends Pantalla {

    private Texture backgroundImage;
    private Music gameMusic;
    public PantallaInstrucciones(SpaceNavigation game) {
        super(game);
    }

    @Override
    protected void initializeAssets() {
        backgroundImage = new Texture(Gdx.files.internal("pantallaInst.png"));
        gameMusic = Gdx.audio.newMusic(Gdx.files.internal("instructiveMusic.wav"));
        gameMusic.setLooping(true);
        gameMusic.setVolume(0.5f);
        gameMusic.play();
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
        if (Gdx.input.isKeyJustPressed(Input.Keys.ANY_KEY)) {
            PantallaJuego nextScreen = new PantallaJuego(game, 1, 3, 0, 1, 1, 10);
            nextScreen.resize(1200, 800);
            game.setScreen(nextScreen);
            dispose();
        }
    }

    @Override
    public void dispose() {
        super.dispose();
        gameMusic.dispose();
        backgroundImage.dispose();
    }
}