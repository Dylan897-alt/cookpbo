package io.github.some_example_name.object;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import io.github.some_example_name.Collidable;

public abstract class GameObject implements Collidable {
    protected Sprite sprite;

    @Override
    public Rectangle getHitbox(){
        return new Rectangle(
            sprite.getX(),
            sprite.getY(),
            sprite.getWidth(),
            sprite.getHeight()
        );
    }

    public GameObject(Sprite sprite) {
        this.sprite = sprite;
    }

    public float getX() {
        return sprite.getX();
    }

    public float getY() {
        return sprite.getY();
    }

    public void draw(SpriteBatch batch) {
        sprite.draw(batch);
    }

    public void setPosition(float x, float y) {
        sprite.setPosition(x, y);
    }
    public void setCenter(float x, float y) {
        sprite.setCenter(x, y);
    }

    public abstract void update(float delta);
}
