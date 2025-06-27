package io.github.some_example_name.weapon;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import io.github.some_example_name.object.BulletOwner;
import io.github.some_example_name.object.BulletSpawner;

public abstract class Weapon {
    protected float cooldown;
    protected float timer = 0f;
    protected float speedModifier = 1f;

    public Weapon(float cooldown) {
        this.cooldown = cooldown;
    }

    public Weapon(float cooldown, float speedModifier) {
        this.cooldown = cooldown;
        this.speedModifier = speedModifier;
    }

    public void update(float delta) {
        timer += delta;
    }

    public boolean canFire() {
        return timer >= cooldown;
    }

    public void resetCooldown() {
        timer = 0f;
    }

    public abstract void fire(Texture bulletTexture, Vector2 origin, Vector2 direction, BulletSpawner spawner, BulletOwner owner);
}

