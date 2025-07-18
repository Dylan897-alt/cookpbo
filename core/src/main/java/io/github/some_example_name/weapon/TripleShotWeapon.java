//package io.github.some_example_name.weapon;
//
//public class TripleShotWeapon extends Weapon{
//}

package io.github.some_example_name.weapon;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import io.github.some_example_name.Damageable;
import io.github.some_example_name.object.BulletOwner;
import io.github.some_example_name.object.BulletSpawner;

public class TripleShotWeapon extends Weapon {
    private final float spreadAngleDegrees = 30f; // derajat penyebaran kiri/kanan

    public TripleShotWeapon(float cooldown) {
        super(cooldown);
    }

    public TripleShotWeapon(float cooldown, float speedModifier){
        super(cooldown, speedModifier);
    }

    @Override
    public void fire(Texture bulletTexture, Vector2 origin, Vector2 direction, BulletSpawner spawner, BulletOwner ownerType, Damageable ownerEntity) {
        Vector2 baseDirection = new Vector2(direction).nor();
        spawner.spawnBullet(bulletTexture, origin.cpy(), baseDirection.cpy(), ownerType, ownerEntity, speedModifier);

        // Peluru kiri
        Vector2 leftDirection = baseDirection.cpy().rotateDeg(spreadAngleDegrees*2);
        spawner.spawnBullet(bulletTexture, origin.cpy(), leftDirection, ownerType, ownerEntity, speedModifier);

        // Peluru kanan
        Vector2 rightDirection = baseDirection.cpy().rotateDeg(-spreadAngleDegrees * 2); // Total deviasi kanan
        spawner.spawnBullet(bulletTexture, origin.cpy(), rightDirection, ownerType, ownerEntity, speedModifier);

        shootSound.play(0.2f);
        resetCooldown();
    }
}
