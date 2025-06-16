package io.github.some_example_name.object;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public abstract class GameObject {
    //nanti bullet, crosshair pindah sini (extend GameObject)
//    public void draw(SpriteBatch batch) {
//        sprite.draw(batch);
//    }
    public abstract void update(float delta);
}
