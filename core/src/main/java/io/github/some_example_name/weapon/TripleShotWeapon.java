//package io.github.some_example_name.weapon;
//
//public class TripleShotWeapon extends Weapon{
//}

package io.github.some_example_name.weapon;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import io.github.some_example_name.object.BulletOwner;
import io.github.some_example_name.object.BulletSpawner;

public class TripleShotWeapon extends Weapon {
    private final float spreadAngleDegrees = 15f; // derajat penyebaran kiri/kanan

    public TripleShotWeapon(float cooldown) {
        super(cooldown);
    }

    @Override
    public void fire(Texture bulletTexture, Vector2 origin, Vector2 direction, BulletSpawner spawner, BulletOwner owner) {
        Vector2 baseDirection = new Vector2(direction).nor();
        spawner.spawnBullet(bulletTexture, origin.cpy(), baseDirection.cpy(), owner, speedModifier);

        // Peluru kiri
        Vector2 leftDirection = baseDirection.cpy().rotateDeg(spreadAngleDegrees*2);
        spawner.spawnBullet(bulletTexture, origin.cpy(), leftDirection, owner, speedModifier);

        // Peluru kanan
        Vector2 rightDirection = baseDirection.cpy().rotateDeg(-spreadAngleDegrees * 2); // Total deviasi kanan
        spawner.spawnBullet(bulletTexture, origin.cpy(), rightDirection, owner, speedModifier);

        resetCooldown();
    }
}
