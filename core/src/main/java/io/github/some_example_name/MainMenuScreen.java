package io.github.some_example_name;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;

import java.awt.*;

public class MainMenuScreen implements Screen{
    final GhostWalk game;
    BitmapFont font;
    SpriteBatch batch;
    OrthographicCamera camera;
    FitViewport viewport;
    public MainMenuScreen(final GhostWalk game){
        this.game = game;
        this.batch = new SpriteBatch();
        this.font = new BitmapFont();
        this.camera = new OrthographicCamera();
        camera.setToOrtho(false);
        this.viewport = new FitViewport(game.VIRTUAL_WIDTH, game.VIRTUAL_HEIGHT, camera);
    }
    @Override
    public void show() {}

    @Override
    public void render(float delta) {
        input();
        logic();
        draw();
    }
    private void input(){
        if (Gdx.input.justTouched()) {
            game.setScreen(new GameScreen(game));
        }
    }

    private void logic(){

    }

    private void draw(){
        camera.update();
        batch.setProjectionMatrix(camera.combined);

        Gdx.gl.glClearColor(0, 0, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        font.draw(batch, "Welcome to My Game!", 2, 4);
        font.draw(batch, "Tap anywhere to begin...", 2, 2);
        batch.end();
    }




    @Override
    public void resize(int width, int height) {
        viewport.update(width, height, true);
        viewport.apply();
    }

    @Override
    public void pause() {}

    @Override
    public void resume() {}

    @Override
    public void hide() {}

    @Override
    public void dispose() {
        batch.dispose();
        font.dispose();
    }
}
