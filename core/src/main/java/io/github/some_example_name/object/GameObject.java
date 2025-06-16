package io.github.some_example_name.object;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import io.github.some_example_name.Collidable;

public abstract class GameObject implements Collidable {
    //nanti bullet, crosshair pindah sini (extend GameObject)
//    public void draw(SpriteBatch batch) {
//        sprite.draw(batch);
//    }
//    @Override
//    public Rectangle getHitbox(){
//        return new Rectangle(
//            sprite.getX(),
//            sprite.getY(),
//            sprite.getWidth(),
//            sprite.getHeight()
//        );
//    }
    public abstract void update(float delta);
}
