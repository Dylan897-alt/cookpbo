package io.github.some_example_name.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.utils.viewport.FitViewport;
import io.github.some_example_name.ShooterGame;

public class MainMenu implements Screen{
    public static final float PPU = 48f; // e.g., 48 pixels = 1 world unit
    final ShooterGame game;
    BitmapFont font;
    SpriteBatch batch;
    OrthographicCamera camera;
    FitViewport viewport;
    Texture img;
    TextureRegion region;
    Animation<TextureRegion> animation;
    float stateTime;
    TextureRegion[] frames;
    float posX = 0f;       // Initial X position
    float speedX = 2f;     // Speed in world units per second
    Sprite sprite;




    public MainMenu(final ShooterGame game){
        this.game = game;
        this.batch = new SpriteBatch();
        this.font = new BitmapFont();
        this.camera = new OrthographicCamera();
        camera.setToOrtho(false);
        this.viewport = new FitViewport(game.VIRTUAL_WIDTH, game.VIRTUAL_HEIGHT, camera);

        this.img = new Texture("Canine_Black_Attack.png");
        TextureRegion[][] tmpFrames = TextureRegion.split(img, 48, 32);
        frames = new TextureRegion[tmpFrames[0].length];
        for (int i = 0; i < tmpFrames[0].length; i++) {
            frames[i] = tmpFrames[0][i];
        }
        animation = new Animation<TextureRegion>(0.1f, frames);
        stateTime = 0f;
        sprite = new Sprite(frames[0]); // Start with the first frame
        sprite.setSize(48 / PPU, 32 / PPU); // Set size in world units
        sprite.setPosition(0, 0);           // Initial position

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
    private void input(){

    }

    private void logic(){
        float delta = Gdx.graphics.getDeltaTime();
        stateTime += delta;

        TextureRegion currentFrame = animation.getKeyFrame(stateTime, true);
        sprite.setRegion(currentFrame); // Update texture for current frame
// Move sprite (for example, rightward)
        float newX = sprite.getX() + speedX * delta;
        sprite.setX(newX);

// Optional boundary check
        if (sprite.getX() > game.VIRTUAL_WIDTH) {
            sprite.setX(-sprite.getWidth());
        }

    }


    private void draw(){
        camera.update();
        batch.setProjectionMatrix(camera.combined);

        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();

        sprite.draw(batch);

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
