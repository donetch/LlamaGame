
package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

public class PantallaMenu extends Pantalla{

    private Music mainMenuMusic;
    private Texture backgroundImage;
    private Texture startButtonTexture;
    private Rectangle startButton;

    public PantallaMenu(SpaceNavigation game) {
        super(game);
    }
   
    @Override
    protected void initializeAssets(){
        mainMenuMusic = Gdx.audio.newMusic(Gdx.files.internal("mainMenuSong.wav"));
        mainMenuMusic.setLooping(true);
        mainMenuMusic.setVolume(0.5f);
        mainMenuMusic.play();
        backgroundImage = new Texture(Gdx.files.internal("portada.jpg"));
        startButtonTexture = new Texture(Gdx.files.internal("startButton.png"));
    }

    @Override
    protected void initializeComponents() {
        initializeStartButton();
    }

    private void initializeStartButton() {
        startButton = new Rectangle(400, 100, 400, 100); // Ajusta las coordenadas y dimensiones según sea necesario
    }

    @Override
    protected void renderComponents(float delta) {
        batch.draw(backgroundImage, 0, 0, 1200, 800);
        batch.draw(startButtonTexture, startButton.x, startButton.y, startButton.width, startButton.height);
    }

    @Override
    protected void handleInput() {
        if (Gdx.input.isTouched() || Gdx.input.isKeyJustPressed(Input.Keys.ANY_KEY)) {
            if (startButton.contains(Gdx.input.getX(), Gdx.graphics.getHeight() - Gdx.input.getY())) {
                startGame();
            }
        }
    }

    private void startGame() {
        PantallaInstrucciones pantallaInstructivo = new PantallaInstrucciones(game);
        pantallaInstructivo.resize(1200, 800);
        game.setScreen(pantallaInstructivo);
        dispose();
    }

    @Override
    public void dispose() {
        super.dispose();
        mainMenuMusic.dispose(); // Liberar los recursos de la música
        backgroundImage.dispose(); // Liberar los recursos de la imagen de fondo
        startButtonTexture.dispose(); // Liberar los recursos del botón de inicio
    }
}
