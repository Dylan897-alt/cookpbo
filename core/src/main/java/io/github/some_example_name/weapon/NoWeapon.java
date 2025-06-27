package io.github.some_example_name.weapon;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import io.github.some_example_name.Damageable;
import io.github.some_example_name.object.BulletOwner;
import io.github.some_example_name.object.BulletSpawner;

public class NoWeapon extends Weapon{
    public NoWeapon() {
        super(0);
    }

    @Override
    public void fire(Texture bulletTexture, Vector2 origin, Vector2 direction, BulletSpawner spawner, BulletOwner ownerType, Damageable ownerEntity) {
    }
}
