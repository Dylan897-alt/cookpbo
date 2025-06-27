package io.github.some_example_name.weapon;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import io.github.some_example_name.object.BulletOwner;
import io.github.some_example_name.object.BulletSpawner;

public class SingleShotWeapon extends Weapon{
    public SingleShotWeapon(float cooldown){
        super(cooldown);
    }

    public SingleShotWeapon(float cooldown, float speedModifier){
        super(cooldown, speedModifier);
    }

    @Override
    public void fire(Texture bulletTexture, Vector2 origin, Vector2 direction, BulletSpawner spawner, BulletOwner owner){
        spawner.spawnBullet(bulletTexture, origin, direction, owner, speedModifier);
        resetCooldown();
    }
}
