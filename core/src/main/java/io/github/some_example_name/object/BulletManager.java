package io.github.some_example_name.object;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;

public class BulletManager implements BulletSpawner{
    private ArrayList<Bullet> bullets = new ArrayList<>();

    @Override
    public void spawnBullet(Texture texture, Vector2 origin, Vector2 target, BulletOwner owner){
        bullets.add(new Bullet(texture, origin, target, owner));
    }

    public void updateBullets(float delta){
        for(Bullet bullet: bullets){
            bullet.update(delta);
        }
    }

    public void drawAll(SpriteBatch batch) {
        for (Bullet bullet : bullets){
            bullet.draw(batch);
        }
    }
}
