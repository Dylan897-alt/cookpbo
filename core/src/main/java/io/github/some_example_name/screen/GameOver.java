package io.github.some_example_name.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import io.github.some_example_name.ShooterGame;

public class GameOver implements Screen {

    private final ShooterGame game;
    private Texture gameOverImage;
    private SpriteBatch batch;

    public GameOver(ShooterGame game) {
        this.game = game;
    }
    @Override
    public void show() {
        // Memuat gambar game over
        gameOverImage = new Texture("gameover.png");
        batch = new SpriteBatch();
    }

    @Override
    public void render(float delta) {
        // Membersihkan layar dengan warna hitam
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        // Menampilkan gambar game over
        batch.draw(gameOverImage, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        batch.end();
    }

    @Override
    public void resize(int width, int height) {
        // Menangani perubahan ukuran layar
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {
        // Menutup resource
        gameOverImage.dispose();
        batch.dispose();
    }

    @Override
    public void dispose() {

    }

}
