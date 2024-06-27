package com.mygdx.game;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class PantallaJuego implements Screen {

    private SpaceNavigation game;
    private OrthographicCamera camera;
    private SpriteBatch batch;
    private Music gameMusic;
    private int score;
    private int ronda;
    private int velXEnemy; 
    private int velYEnemy; 
    private int cantEnemy;
    private Texture backgroundImage;


    private Llama llama;
    private List<Enemy> enemies = new ArrayList<>();
    private ArrayList<Bullet> balas = new ArrayList<>();

    public PantallaJuego(SpaceNavigation game, int ronda, int vidas, int score,  
        int velXEnemy, int velYEnemy, int cantEnemy) {
        this.game = game;
        this.ronda = ronda;
        this.score = score;
        this.velXEnemy = velXEnemy;
        this.velYEnemy = velYEnemy;
        this.cantEnemy = cantEnemy;

        this.batch = game.getBatch();
        this.camera = new OrthographicCamera();    
        this.camera.setToOrtho(false, 1200, 800);

        // Initialize assets
        this.gameMusic = Gdx.audio.newMusic(Gdx.files.internal("piano-loops.wav"));
        setupAudio();

        // Load textures and initialize game objects
        backgroundImage = new Texture(Gdx.files.internal("lava.png"));
        this.llama = initializeLlama(vidas);
        initializeEnemies();
    }

    private void setupAudio() {
        gameMusic.setLooping(true);
        gameMusic.setVolume(0.5f);
        gameMusic.play();
    }

    private Llama initializeLlama(int vidas) {
        this.llama = Llama.getInstance(); // Use Singleton instance
        this.llama.setVidas(vidas); // Set vidas
        return llama;
    }

    private void initializeEnemies() {
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

    @Override
    public void render(float delta) {
        game.getBatch().begin();
        game.getBatch().draw(backgroundImage, 0, 0, 1200, 800); // Ajusta las dimensiones según sea necesario
        game.getBatch().end();

        camera.update();
        batch.setProjectionMatrix(camera.combined);

        batch.begin();
        dibujaEncabezado();
        if (!llama.estaHerido()) {
            updateGameLogic();
            checkLevelCompletion();
        }
        drawEntities();
        checkGameOver();
        batch.end();
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


    private void checkLevelCompletion() {
        if (enemies.isEmpty()) {
            ronda++;
            llama.setVidas(llama.getVidas() + 3);
            velXEnemy++;
            velYEnemy++;
            cantEnemy += 5;

            Screen nextScreen = new PantallaJuego(game, ronda, llama.getVidas(), score, velXEnemy, velYEnemy, cantEnemy);
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
            if (score > game.getHighScore()) {
                game.setHighScore(score);
            }
            Screen gameOverScreen = new PantallaGameOver(game);
            gameOverScreen.resize(1200, 800);
            game.setScreen(gameOverScreen);
            dispose();
        }
    }

    public boolean agregarBala(Bullet bullet) {
        return balas.add(bullet);
    }

    @Override
    public void show() {
        gameMusic.play();
    }

    @Override
    public void resize(int width, int height) {
        camera.setToOrtho(false, width, height);
    }

    @Override
    public void pause() {}

    @Override
    public void resume() {}

    @Override
    public void hide() {}

    @Override
    public void dispose() {
        gameMusic.dispose();
    }
}
