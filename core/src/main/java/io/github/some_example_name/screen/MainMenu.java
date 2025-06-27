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
    final ShooterGame game;

    BitmapFont font;
    SpriteBatch batch;
    OrthographicCamera camera;
    FitViewport viewport;
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

    }

    private void draw() {
        camera.update();
        batch.setProjectionMatrix(camera.combined);

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        // Draw background
        batch.draw(background, 0, 0, game.VIRTUAL_WIDTH, game.VIRTUAL_HEIGHT);

        // Draw "Press Space" text
        font.draw(batch, "Press [Space] to start", (2f * ShooterGame.SCALE), (1f * ShooterGame.SCALE));
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
    }
}
