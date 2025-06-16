package io.github.some_example_name.character;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import io.github.some_example_name.Collidable;
import io.github.some_example_name.Damageable;

public abstract class Character implements Collidable, Damageable {
    protected Sprite sprite;
    private float hp;
    private float exp;

    @Override
    public Rectangle getHitbox(){
        return new Rectangle(
            sprite.getX(),
            sprite.getY(),
            sprite.getWidth(),
            sprite.getHeight()
        );
    }
    @Override
    public void takeDamage(float damage){

    }

    public Character(Sprite sprite, float hp, float exp) {
        this.sprite = sprite;
        this.hp = hp;
        this.exp = exp;
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

