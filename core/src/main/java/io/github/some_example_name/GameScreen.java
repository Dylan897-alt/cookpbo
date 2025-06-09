package io.github.some_example_name;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;

public class GameScreen implements Screen {
    final GhostWalk game;
    Texture image;
    Texture image2;
    SpriteBatch batch;
    OrthographicCamera camera;
    FitViewport viewport;
    Sprite chara;
    Sprite dest;
    Vector2 mousePos;
    Vector2 touchPos;
    Vector2 velocity;
    ShapeRenderer shapeRenderer;
    boolean isTravelling;
    boolean right;

    public GameScreen(final GhostWalk game) {
        this.game = game;
        this.batch = new SpriteBatch();
        this.image = new Texture("ghost.png");
        this.image2 = new Texture("circle.png");
        this.chara = new Sprite(image);
        chara.setSize(1f, 1f);
        this.dest = new Sprite(image2);
        dest.setSize(image2.getWidth()/250f, image2.getHeight()/250f);
        this.touchPos = new Vector2();
        this.mousePos = new Vector2();
        this.velocity = new Vector2();
        this.shapeRenderer = new ShapeRenderer();
        this.camera = new OrthographicCamera();
        camera.setToOrtho(false);
        this.viewport = new FitViewport(game.VIRTUAL_WIDTH, game.VIRTUAL_HEIGHT, camera);
        this.isTravelling = false;
        this.right = true;
    }

    @Override
    public void show() {}

    @Override
    public void render(float delta) {
        input(delta);
        logic(delta);
        draw();
    }

    private void input(float delta){
        float speed = 2f;
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            chara.translateX(speed * delta);
            isTravelling = false;
            if(!right){
                chara.flip(true, false);
                right = true;
            }
        }
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            chara.translateX(-speed * delta);
            isTravelling = false;
            if(right){
                chara.flip(true, false);
                right = false;
            }
        }
        if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
            chara.translateY(speed * delta);
            isTravelling = false;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            chara.translateY(-speed * delta);
            isTravelling = false;
        }
        if (Gdx.input.justTouched()) {
            touchPos.set(Gdx.input.getX(), Gdx.input.getY());
            viewport.unproject(touchPos);
            Vector2 startPos = new Vector2(chara.getX() + chara.getWidth() / 2f, chara.getY() + chara.getHeight() / 2f);
            Vector2 direction = new Vector2(touchPos).sub(startPos);
            velocity.set(direction.nor().scl(2f));
            isTravelling = true;
        }
    }

    private void logic(float delta){
        chara.setX(MathUtils.clamp(chara.getX(), 0, game.VIRTUAL_WIDTH - chara.getWidth()));
        chara.setY(MathUtils.clamp(chara.getY(), 0, game.VIRTUAL_HEIGHT - chara.getHeight()));
        mousePos.set(Gdx.input.getX(), Gdx.input.getY());
        viewport.unproject(mousePos);
        dest.setCenter(mousePos.x, mousePos.y);
        if (isTravelling) {
            if(right && velocity.x < 0){
                chara.flip(true, false);
                right = false;
            } else if(!right && velocity.x > 0){
                chara.flip(true, false);
                right = true;
            }
            chara.translate(velocity.x * delta, velocity.y * delta);
            Vector2 charaCenter = new Vector2(
                chara.getX() + chara.getWidth() / 2f,
                chara.getY() + chara.getHeight() / 2f
            );
            if (charaCenter.dst(touchPos) < 0.1f) {
                isTravelling = false;
                velocity.setZero();
            }
        }

    }

    private void draw(){
        camera.update();
        batch.setProjectionMatrix(camera.combined);

        Gdx.gl.glClearColor(0.68f, 0.85f, 0.9f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        chara.draw(batch);
        dest.draw(batch);
        batch.end();

        shapeRenderer.setProjectionMatrix(camera.combined);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(Color.BLACK);

        drawDashedLine(shapeRenderer, .2f, .1f); // 5px dash, 3px gap
        shapeRenderer.end();

    }

    private void drawDashedLine(ShapeRenderer shapeRenderer, float dashLength, float gapLength) {
        Vector2 start = new Vector2(
            chara.getX() + chara.getWidth() / 2f,
            chara.getY() + chara.getHeight() / 2f
        );
        Vector2 end = new Vector2(
            dest.getX() + dest.getWidth() / 2f,
            dest.getY() + dest.getHeight() / 2f
        );
        Vector2 direction = new Vector2(end).sub(start);
        float totalLength = direction.len();
        direction.nor();

        float drawn = 0;
        while (drawn < totalLength) {
            float dash = Math.min(dashLength, totalLength - drawn);
            Vector2 dashStart = new Vector2(start).mulAdd(direction, drawn);
            Vector2 dashEnd = new Vector2(start).mulAdd(direction, drawn + dash);

            shapeRenderer.rectLine(dashStart, dashEnd, .1f);
            drawn += dashLength + gapLength;
        }
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
        image.dispose();
        shapeRenderer.dispose();
    }
}
