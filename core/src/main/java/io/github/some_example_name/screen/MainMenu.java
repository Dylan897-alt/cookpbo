package io.github.some_example_name.screen;
import io.github.some_example_name.screen.Stage1;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.utils.viewport.FitViewport;
import io.github.some_example_name.ShooterGame;

public class MainMenu implements Screen {
    public static final float PPU = 48f;
    final ShooterGame game;

    BitmapFont font;
    SpriteBatch batch;
    OrthographicCamera camera;
    FitViewport viewport;

    Texture img;
    TextureRegion[] frames;
    Animation<TextureRegion> animation;
    float stateTime;
    Sprite sprite;

    Texture background;

    public MainMenu(final ShooterGame game) {
        this.game = game;
        this.batch = new SpriteBatch();
        this.font = new BitmapFont();
        this.camera = new OrthographicCamera();
        camera.setToOrtho(false);
        this.viewport = new FitViewport(game.VIRTUAL_WIDTH, game.VIRTUAL_HEIGHT, camera);

        // Background
        this.background = new Texture("etgmainmenu.png");

        // Animation setup
        this.img = new Texture("Canine_Black_Attack.png");
        TextureRegion[][] tmpFrames = TextureRegion.split(img, 48, 32);
        frames = new TextureRegion[tmpFrames[0].length];
        for (int i = 0; i < tmpFrames[0].length; i++) {
            frames[i] = tmpFrames[0][i];
        }
        animation = new Animation<>(0.1f, frames);
        stateTime = 0f;
        sprite = new Sprite(frames[0]);
        sprite.setSize(48 / PPU, 32 / PPU);
        sprite.setPosition(0, 0);
    }

    @Override
    public void show() {
        viewport.apply();
    }

    @Override
    public void render(float delta) {
        input();
        logic();
        draw();
    }

    private void input() {
        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
            try {
                System.out.println("Trying to switch to Stage1");
                game.setScreen(new Stage1(game));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    private void logic() {
        float delta = Gdx.graphics.getDeltaTime();
        stateTime += delta;

        TextureRegion currentFrame = animation.getKeyFrame(stateTime, true);
        sprite.setRegion(currentFrame);

        float newX = sprite.getX() + 2f * delta;
        sprite.setX(newX);

        if (sprite.getX() > game.VIRTUAL_WIDTH) {
            sprite.setX(-sprite.getWidth());
        }
    }

    private void draw() {
        camera.update();
        batch.setProjectionMatrix(camera.combined);

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        // Draw background
        batch.draw(background, 0, 0, game.VIRTUAL_WIDTH, game.VIRTUAL_HEIGHT);

        // Draw animated sprite
        sprite.draw(batch);

        // Draw "Press Space" text
        font.draw(batch, "Press [Space] to start", 2f, 1f);
        batch.end();
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
        font.dispose();
        background.dispose();
        img.dispose();
    }
}
