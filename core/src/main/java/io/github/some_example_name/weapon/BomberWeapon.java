package io.github.some_example_name.weapon;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import io.github.some_example_name.Damageable;
import io.github.some_example_name.object.BulletOwner;
import io.github.some_example_name.object.BulletSpawner;

public class BomberWeapon extends Weapon {
    public BomberWeapon(float cooldown) {
        super(cooldown);
    }

    @Override
    public void fire(Texture bulletTexture, Vector2 origin, Vector2 direction, BulletSpawner spawner, BulletOwner ownerType, Damageable ownerEntity) {
        // Cardinal directions
        Vector2[] directions = new Vector2[] {
            new Vector2(1, 0),   // right
            new Vector2(-1, 0),  // left
            new Vector2(0, 1),   // up
            new Vector2(0, -1)   // down
        };

        for (Vector2 dir : directions) {
            spawner.spawnBullet(bulletTexture, origin.cpy(), dir, ownerType, ownerEntity, speedModifier);
        }
        shootSound.play(0.2f);
        resetCooldown();
    }
}
