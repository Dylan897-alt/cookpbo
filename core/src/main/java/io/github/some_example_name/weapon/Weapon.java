package io.github.some_example_name.weapon;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import io.github.some_example_name.object.BulletSpawner;

public abstract class Weapon {
    //Weapon cuma control berapa bullet yang di spawn, angle nya, dan kapan nembak
    protected float cooldown;
    protected float timer = 0f;

    public Weapon(float cooldown) {
        this.cooldown = cooldown;
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

    public abstract void fire(Vector2 origin, Vector2 target, BulletSpawner spawner);
}

