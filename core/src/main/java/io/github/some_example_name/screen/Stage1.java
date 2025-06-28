package io.github.some_example_name.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
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
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.badlogic.gdx.utils.Align;
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

import java.util.ArrayList;// ... (kode impor tetap sama)

public class Stage1 implements Screen {
    final ShooterGame game;
    private boolean isGameOver = false;
    private boolean isStageCleared = false;
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

    Texture background;
    Texture levelButton; // ✅ Tambahkan ini
    Music bgMusic;

    private static class ActiveAnimation {
        Vector2 position;
        float stateTime;

        ActiveAnimation(Vector2 position) {
            this.position = position;
            this.stateTime = 0f;
        }
    }
    private ArrayList<ActiveAnimation> activeExplosions = new ArrayList<>();

    public Stage1(final ShooterGame game) {
        this.game = game;
        this.batch = new SpriteBatch();
        this.camera = new OrthographicCamera();
        camera.setToOrtho(false);
        this.viewport = new FitViewport(game.VIRTUAL_WIDTH, game.VIRTUAL_HEIGHT, camera);

        this.background = new Texture("backgroundstage1.png");
        this.player = new Player(10, 0, new Texture("tes1.png"), new Texture("bullet3.png"));
        this.playerController = new PlayerController(player, viewport);

        this.crosshair = new Crosshair(new Texture("crosshair.png"), viewport, player);
        this.bulletManager = new BulletManager();
        this.enemyManager = new EnemyManager(player);
        enemyManager.setListener((enemy) -> {
            player.addExp(enemy.getExp());
            playDeathAnimation(enemy.getCenter());
        });

        this.collisionManager = new CollisionManager();
    }

    @Override
    public void show() {
        viewport.apply();

        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("VT323-Regular.ttf"));
        FreeTypeFontParameter parameter = new FreeTypeFontParameter();
        parameter.size = 40;
        parameter.characters = FreeTypeFontGenerator.DEFAULT_CHARS;
        parameter.minFilter = Texture.TextureFilter.Linear;
        parameter.magFilter = Texture.TextureFilter.Linear;

        font = generator.generateFont(parameter);
        generator.dispose();
        font.getData().setScale(0.007f * ShooterGame.SCALE);

        this.deathAnimation = FrameHandler.createAnimation(
            new Texture("enemy-explosion.png"), 80, 80, .1f, false
        );
        this.bgMusic = Gdx.audio.newMusic(Gdx.files.internal("gun-shot.mp3"));

       // this.bgMusic = Gdx.audio.newMusic(Gdx.files.internal("08. Quiz! (DELTARUNE Chapter 3+4 Soundtrack) - Toby Fox.mp3"));
        bgMusic.setLooping(false);
        bgMusic.setVolume(0.5f);
        bgMusic.play();

        // ✅ Load gambar tombol
        this.levelButton = new Texture("levelhexagon.png");
    }

    @Override
    public void render(float delta) {
        if (isGameOver) {
            dispose();
            game.setScreen(new GameOver(game));
            return;
        }
        if (isStageCleared && Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
            dispose();
            game.setScreen(new Stage2(game, this.player));
            return;
        }
        if (!bgMusic.isPlaying() && !isStageCleared) {
            isStageCleared = true;
            System.out.println("Stage Cleared!");
        }


        playerController.handleInput(delta, bulletManager);
        player.update(delta);
        crosshair.update(delta);
        bulletManager.updateBullets(delta);
        enemyManager.handleSpawnStage1(delta, isStageCleared);
        enemyManager.updateEnemies(delta, bulletManager, player);
        collisionManager.handleAllCollisions(player, bulletManager, enemyManager);

        if (player.getHp() <= 0) {
            isGameOver = true;
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
        batch.draw(background, 0, 0, game.VIRTUAL_WIDTH, game.VIRTUAL_HEIGHT);

        font.getData().setScale(0.007f * ShooterGame.SCALE);
        font.draw(batch, "EXP: " + (int)player.getExp() + " / " + (int)player.getExpToNextLevel(),
            (0.2f * ShooterGame.SCALE), ShooterGame.VIRTUAL_HEIGHT - (0.1f * ShooterGame.SCALE));
        font.draw(batch, "LVL: " + (int)player.getLevel(),
            (5f * ShooterGame.SCALE), ShooterGame.VIRTUAL_HEIGHT - (0.1f * ShooterGame.SCALE));
        font.draw(batch, "HP: " + (int)player.getHp(),
            (0.2f * ShooterGame.SCALE), ShooterGame.VIRTUAL_HEIGHT - (0.4f * ShooterGame.SCALE));
        font.draw(batch, "DMG: " + (int)player.getDamage(),
            (5f * ShooterGame.SCALE), ShooterGame.VIRTUAL_HEIGHT - (0.4f * ShooterGame.SCALE));

        if (isStageCleared) {
            font.getData().setScale(0.02f * ShooterGame.SCALE);

            float textX = 4f * ShooterGame.SCALE;
            float textY = 3f * ShooterGame.SCALE;

        font.draw(batch, "Stage Cleared\nPress Space to Continue", textX, textY,0, Align.center, false);
        }


        // ✅ Gambar tombol di kanan atas, tinggi setara teks
        float buttonHeight = 0.8f * ShooterGame.SCALE;
        float aspectRatio = (float) levelButton.getWidth() / levelButton.getHeight();
        float buttonWidth = buttonHeight * aspectRatio;
        float x = ShooterGame.VIRTUAL_WIDTH - buttonWidth - (0.2f * ShooterGame.SCALE);
        float y = ShooterGame.VIRTUAL_HEIGHT - buttonHeight;

        batch.draw(levelButton, x, y, buttonWidth, buttonHeight);

        player.draw(batch);
        bulletManager.drawAll(batch);
        enemyManager.drawAll(batch);
        crosshair.draw(batch);

        for (ActiveAnimation anim : activeExplosions) {
            TextureRegion frame = deathAnimation.getKeyFrame(anim.stateTime);
            float size = 1f * ShooterGame.SCALE;
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
        background.dispose();
        bgMusic.stop();
        bgMusic.dispose();
        levelButton.dispose();
        font.dispose();
    }
}
