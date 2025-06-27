package io.github.some_example_name.weapon;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import io.github.some_example_name.Damageable;
import io.github.some_example_name.object.BulletOwner;
import io.github.some_example_name.object.BulletSpawner;

public class FiveShotWeapon extends Weapon {
    private final float spread = 25f; // makin kecil makin rapat (dalam derajat)

    public FiveShotWeapon(float cooldown) {
        super(cooldown);
    }

    @Override
    public void fire(Texture bulletTexture, Vector2 origin, Vector2 direction, BulletSpawner spawner, BulletOwner ownerType, Damageable ownerEntity) {        Vector2 baseDir = new Vector2(direction).nor();

        // Sudut: -2*spread, -spread, 0, +spread, +2*spread
        for (int i = -2; i <= 2; i++) {
            Vector2 shotDir = baseDir.cpy().rotateDeg(i * spread);
            spawner.spawnBullet(bulletTexture, origin.cpy(), shotDir, ownerType, ownerEntity, speedModifier);
        }
        shootSound.play(0.2f);
        resetCooldown();
    }
}
