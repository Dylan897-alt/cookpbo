package io.github.some_example_name.object;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

public interface BulletSpawner {
    void spawnBullet(Texture texture, Vector2 origin, Vector2 target, BulletOwner owner);
}
