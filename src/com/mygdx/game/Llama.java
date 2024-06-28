package com.mygdx.game;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;

public class PantallaJuego extends Pantalla {

    private int score;
    private int ronda;
    private int velXEnemy; 
    private int velYEnemy; 
    private int cantEnemy;
    private Texture backgroundImage;
    private Music gameMusic;
    private Llama llama;
    private List<Enemy> enemies = new ArrayList<>();
    private ArrayList<Bullet> balas = new ArrayList<>();

    public PantallaJuego(SpaceNavigation game, int ronda, int vidas, int score,  
        int velXEnemy, int velYEnemy, int cantEnemy) {
        super(game);
        this.ronda = ronda;
        this.score = score;
        this.velXEnemy = velXEnemy;
        this.velYEnemy = velYEnemy;
        this.cantEnemy = cantEnemy;
        initializeEnemies(vidas);
        Gdx.app.log("PantallaJuego", "PantallaJuego creada");
    }

    @Override
    protected void initializeAssets() {
        backgroundImage = new Texture(Gdx.files.internal("lava.png"));
        gameMusic = Gdx.audio.newMusic(Gdx.files.internal("piano-loops.wav"));
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
        batch.draw(backgroundImage, 0, 0, 1200, 800); // Ajusta las dimensiones según sea necesario
        dibujaEncabezado();
        if (!llama.estaHerido()) {
            updateGameLogic();
            checkLevelCompletion();
        }
        drawEntities();
        checkGameOver();
    }

    private void initializeEnemies(int vidas) {
        llama = Llama.getInstance();
        llama.setInicio();

        Random random = new Random();
        for (int i = 0; i < cantEnemy; i++) {
            if (random.nextBoolean()) {
                enemies.add(new WeakEnemy(velXEnemy, velYEnemy));
            } else {
                enemies.add(new StrongEnemy(velXEnemy, velYEnemy));
            }
        }
    }

    private void dibujaEncabezado() {
        CharSequence str = "Vidas: " + llama.getVidas() + " Ronda: " + ronda;
        game.getFont().getData().setScale(2f);
        game.getFont().draw(batch, str, 10, 30);
        game.getFont().draw(batch, "Score:" + this.score, Gdx.graphics.getWidth() - 150, 30);
        game.getFont().draw(batch, "HighScore:" + game.getHighScore(), Gdx.graphics.getWidth() / 2 - 100, 30);
    }

    private void updateGameLogic() {
        updateBullets();
        updateEnemies(llama);
    }

    private void updateBullets() {
        List<Bullet> bulletsToRemove = new ArrayList<>();
        List<Enemy> enemiesToRemove = new ArrayList<>();

        for (Bullet bullet : balas) {
            bullet.update();
            for (Enemy enemy : enemies) {
                bullet.checkCollision(enemy);
                if (bullet.isDestroyed()) {
                    bulletsToRemove.add(bullet);
                }
                enemy.checkCollision(bullet);
                if (enemy.isDead()) {
                    enemiesToRemove.add(enemy);
                    score += 10;
                }
            }
        }

        balas.removeAll(bulletsToRemove);
        enemies.removeAll(enemiesToRemove);
    }

    private void updateEnemies(Llama llama) {
        List<Enemy> enemiesToRemove = new ArrayList<>();

        for (Enemy enemy : enemies) {
            enemy.update(llama);
            llama.checkCollision(enemy);
            if (enemy.isDead()) {
                enemiesToRemove.add(enemy);
                score += 10;
            }
        }

        enemies.removeAll(enemiesToRemove);
    }
    
    public boolean agregarBala(Bullet bullet) {
        return balas.add(bullet);
    }
    
    private void checkLevelCompletion() {
        if (enemies.isEmpty()) {
            ronda++;
            llama.setVidas(llama.getVidas() + 3);
            velXEnemy++;
            velYEnemy++;
            cantEnemy += 5;

            Gdx.app.log("PantallaJuego", "Nivel completado, avanzando al siguiente nivel");
            PantallaJuego nextScreen = new PantallaJuego(game, ronda, llama.getVidas(), score, velXEnemy, velYEnemy, cantEnemy);
            nextScreen.resize(1200, 800);
            game.setScreen(nextScreen);
            dispose();
        }
    }

    private void drawEntities() {
        for (Enemy enemy : enemies) {
            enemy.draw(batch);
        }
        for (Bullet bullet : balas) {
            bullet.draw(batch);
        }
        llama.draw(batch, this);
    }

    private void checkGameOver() {
        if (llama.estaDestruido()) {
            Gdx.app.log("PantallaJuego", "Juego terminado, mostrando pantalla de Game Over");
            if (score > game.getHighScore()) {
                game.setHighScore(score);
            }
            PantallaGameOver gameOverScreen = new PantallaGameOver(game);
            gameOverScreen.resize(1200, 800);
            game.setScreen(gameOverScreen);
            dispose();
        }
    }

    @Override
    protected void handleInput() {
        // Implementación de manejo de entrada si es necesaria
        if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
            Gdx.app.log("PantallaJuego", "Escape key pressed");
            game.setScreen(new PantallaMenu(game));
            dispose();
        }
    }

    @Override
    public void dispose() {
        Gdx.app.log("PantallaJuego", "Disposing PantallaJuego");
        super.dispose();
        gameMusic.dispose();
        backgroundImage.dispose();
    }
}
