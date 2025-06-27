package io.github.some_example_name.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.FitViewport;
import io.github.some_example_name.PlayerController;
import io.github.some_example_name.ShooterGame;
import io.github.some_example_name.object.CollisionManager;
import io.github.some_example_name.object.Crosshair;
import io.github.some_example_name.character.Enemy;
import io.github.some_example_name.character.Player;
import io.github.some_example_name.object.BulletManager;
import io.github.some_example_name.utils.FrameHandler;
import org.w3c.dom.Text;

import java.util.ArrayList;

public class Stage1 implements Screen {
    public static final float PPU = 48f;
    final ShooterGame game;
    private boolean isGameOver = false; //untuk ngecek dia gameover/ga
    private BitmapFont font;
    private Animation<TextureRegion> deathAnimation;


    Player player;
    Crosshair crosshair;
    PlayerController playerController;
    BulletManager bulletManager;
    EnemyManager enemyManager;
    CollisionManager collisionManager;

    OrthographicCamera camera;
    FitViewport viewport;
    SpriteBatch batch;

    Texture background; // Background
    Music bgMusic;

    private static class ActiveAnimation {
        Vector2 position;
        float stateTime;

        ActiveAnimation(Vector2 position) {
            this.position = position;
            this.stateTime = 0f;
        }
    }
    private final ArrayList<ActiveAnimation> activeExplosions = new ArrayList<>();


    public Stage1(final ShooterGame game) {
        this.game = game;
        this.batch = new SpriteBatch();
        this.camera = new OrthographicCamera();
        camera.setToOrtho(false);
        this.viewport = new FitViewport(game.VIRTUAL_WIDTH, game.VIRTUAL_HEIGHT, camera);

        this.background = new Texture("backgroundstage1.png"); // Load background
        this.player = new Player(10, 0, new Texture("tes1.png"), new Texture("bullet3.png"));
        this.playerController = new PlayerController(player, viewport);

        this.crosshair = new Crosshair(new Texture("crosshair.png"), viewport, player);
        this.bulletManager = new BulletManager();
        this.enemyManager = new EnemyManager(player);
        enemyManager.setListener((enemy) -> {
            player.addExp(enemy.getExp()); // or score += ...
            //sound.playExplosion(); // optional
            playDeathAnimation(enemy.getCenter());
        });

        this.collisionManager = new CollisionManager();
    }

    @Override
    public void show() {
        viewport.apply();
        font = new BitmapFont();
        font.getData().setScale(0.07f);
        this.deathAnimation = FrameHandler.createAnimation(new Texture("enemy-explosion.png"), 80, 80, .1f, false);
        this.bgMusic = Gdx.audio.newMusic(Gdx.files.internal("08. Quiz! (DELTARUNE Chapter 3+4 Soundtrack) - Toby Fox.mp3"));
        bgMusic.setLooping(false);
        bgMusic.setVolume(0.5f);
        bgMusic.play();
    }

    @Override
    public void render(float delta) {
        if (isGameOver) {
            // Jika game-over, pindah ke layar GameOver
            game.setScreen(new GameOver(game));
            return;
        }
        playerController.handleInput(delta, bulletManager);
        player.update(delta);
        crosshair.update(delta);
        bulletManager.updateBullets(delta);
        enemyManager.handleSpawnStage1(delta);
        enemyManager.updateEnemies(delta, bulletManager, player);
        collisionManager.handleAllCollisions(player, bulletManager, enemyManager);

        // Pengecekan Game-Over berdasarkan kondisi kesehatan pemain
        if (player.getHp() <= 0) {
            isGameOver = true;  // Menandakan bahwa permainan selesai
        }

        for (int i = activeExplosions.size() - 1; i >= 0; i--) {
            ActiveAnimation anim = activeExplosions.get(i);
            anim.stateTime += delta;

            if (deathAnimation.isAnimationFinished(anim.stateTime)) {
                activeExplosions.remove(i);
            }
        }
        draw();
    }

    private void draw(){
        camera.update();
        batch.setProjectionMatrix(camera.combined);

        Gdx.gl.glClearColor(0.68f, 0.85f, 0.9f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        batch.draw(background, 0, 0, game.VIRTUAL_WIDTH, game.VIRTUAL_HEIGHT); // Draw background
        font.draw(batch, "EXP: " + (int)player.getExp(), 0.2f, ShooterGame.VIRTUAL_HEIGHT - 0.2f);
        font.draw(batch, "LVL: " + player.getLevel(), 3f, ShooterGame.VIRTUAL_HEIGHT - 0.4f);
        player.draw(batch); // your existing draw call

        player.draw(batch);
        bulletManager.drawAll(batch);
        enemyManager.drawAll(batch);
        crosshair.draw(batch);

        for (ActiveAnimation anim : activeExplosions) {
            TextureRegion frame = deathAnimation.getKeyFrame(anim.stateTime);
            float size = 1f; // adjust for proper scale
            batch.draw(frame, anim.position.x - size / 2, anim.position.y - size / 2, size, size);
        }

        batch.end();
    }

    public void playDeathAnimation(Vector2 spawnPos){
        activeExplosions.add(new ActiveAnimation(spawnPos));
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height, true);
        viewport.apply();
    }

    @Override public void pause() {}
    @Override public void resume() {}
    @Override public void hide() {}

    @Override
    public void dispose() {
        batch.dispose();
        background.dispose(); // Dispose background
        bgMusic.stop();
        bgMusic.dispose();
    }
}
