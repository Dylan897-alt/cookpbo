package io.github.some_example_name.character;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.Sprite;

public abstract class Character {
    protected Sprite sprite;
    private float hp;

    public Character(Sprite sprite) {
        this.sprite = sprite;
    }

    public void draw(SpriteBatch batch) {
        sprite.draw(batch);
    }

    public float getX() {
        return sprite.getX();
    }
    public float getY() {
        return sprite.getY();
    }
    public void setPosition(float x, float y) {
        sprite.setPosition(x, y);
    }
    public void setCenter(float x, float y) {
        sprite.setCenter(x, y);
    }

    public abstract void update(float delta);
}

