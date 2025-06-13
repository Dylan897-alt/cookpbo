package io.github.some_example_name;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.FitViewport;
import io.github.some_example_name.character.Crosshair;
import io.github.some_example_name.character.Enemy;
import io.github.some_example_name.character.Player;

public class GameScreen implements Screen {
    public static final float PPU = 48f;
    final ShooterGame game;
    Player player;
    Enemy dog1;
    Crosshair crosshair;
    PlayerController playerController;
    OrthographicCamera camera;
    FitViewport viewport;
    SpriteBatch batch;


    public GameScreen(final ShooterGame game) {
        this.game = game;
        this.batch = new SpriteBatch();
        this.camera = new OrthographicCamera();
        camera.setToOrtho(false);
        this.viewport = new FitViewport(game.VIRTUAL_WIDTH, game.VIRTUAL_HEIGHT, camera);

        this.player = new Player(new Texture("ghost.png"));
        this.playerController = new PlayerController(player, viewport);

        this.dog1 = new Enemy(new Texture("Canine_Black_Attack.png"), 48, 32, 0.1f);

        this.crosshair = new Crosshair(new Texture("crosshair.png"), viewport, player);
    }

    @Override
    public void show() {
        viewport.apply();
    }

    @Override
    public void render(float delta) {
        playerController.handleInput(delta);
        player.update(delta);
        dog1.update(delta);
        crosshair.update(delta);
        for (int i = 0; i < player.getBullets().size(); i++) {
            player.getBullets().get(i).update(delta);
        }
        draw();
    }

    private void draw(){
        camera.update();
        batch.setProjectionMatrix(camera.combined);

        Gdx.gl.glClearColor(0.68f, 0.85f, 0.9f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        player.draw(batch);
        dog1.draw(batch);
        crosshair.draw(batch);
        for (int i = 0; i < player.getBullets().size(); i++) {
            player.getBullets().get(i).draw(batch);
        }
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
    }
}
