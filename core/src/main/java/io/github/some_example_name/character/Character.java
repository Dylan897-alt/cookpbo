package io.github.some_example_name.character;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import io.github.some_example_name.Collidable;
import io.github.some_example_name.Damageable;

public abstract class Character implements Collidable, Damageable {
    protected Sprite sprite;
    private float hp;
    private float exp;
    private float damage;

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
        hp-=damage; //!!!!!!!!tolong check
    }

    public Character(Sprite sprite, float hp, float exp) {
        this.sprite = sprite;
        this.hp = hp;
        this.exp = exp;
        this.damage = 1;
    }

    public void draw(SpriteBatch batch) {
        sprite.draw(batch);
    }

    public Vector2 getCenter() {
        return new Vector2(
            sprite.getX() + sprite.getWidth() / 2f,
            sprite.getY() + sprite.getHeight() / 2f
        );
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

    public float getHp() {
        return hp;
    }

    public void setHp(float value) {
        this.hp = value;
    }

    public float getExp(){
        return exp;
    }
    public void setExp(float value) {
        this.exp = value;
    }

    public float getDamage(){
        return damage;
    }

    public void setDamage(float value){
        this.damage = damage;
    }


    public abstract void update(float delta);


}

