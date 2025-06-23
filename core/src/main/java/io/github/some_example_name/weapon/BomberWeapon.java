package io.github.some_example_name.weapon;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import io.github.some_example_name.object.BulletOwner;
import io.github.some_example_name.object.BulletSpawner;

public class BomberWeapon extends Weapon {
    public BomberWeapon(float cooldown) {
        super(cooldown);
    }

    @Override
    public void fire(Texture bulletTexture, Vector2 origin, Vector2 direction, BulletSpawner spawner, BulletOwner owner) {
        Vector2 baseDir = new Vector2(direction).nor();

        // Arah utama ke player
        spawner.spawnBullet(bulletTexture, origin.cpy(), baseDir.cpy(), owner);

        // Tambahan arah: +90, +180, +270 derajat
        spawner.spawnBullet(bulletTexture, origin.cpy(), baseDir.cpy().rotateDeg(90), owner);
        spawner.spawnBullet(bulletTexture, origin.cpy(), baseDir.cpy().rotateDeg(180), owner);
        spawner.spawnBullet(bulletTexture, origin.cpy(), baseDir.cpy().rotateDeg(270), owner);

        resetCooldown();
    }
}
