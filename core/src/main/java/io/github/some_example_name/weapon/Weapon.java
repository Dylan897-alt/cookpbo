package io.github.some_example_name.weapon;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import io.github.some_example_name.Damageable;
import io.github.some_example_name.object.BulletOwner;
import io.github.some_example_name.object.BulletSpawner;

public abstract class Weapon {
    protected float cooldown;
    protected float timer = 0f;
    protected float speedModifier = 1f;
    protected Sound shootSound;

    public Weapon(float cooldown) {
        this.cooldown = cooldown;
        this.shootSound = Gdx.audio.newSound(Gdx.files.internal("gun-shot.mp3")); //yang di spam shot beda sendiri
    }

    public Weapon(float cooldown, float speedModifier) {
        this.cooldown = cooldown;
        this.speedModifier = speedModifier;
        this.shootSound = Gdx.audio.newSound(Gdx.files.internal("gun-shot.mp3")); //yang di spam shot beda sendiri
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

    public void setCooldown(float cooldown) {
        this.cooldown = cooldown;
    }

    public float getCooldown(){
        return cooldown;
    }

    public abstract void fire(Texture bulletTexture, Vector2 origin, Vector2 direction, BulletSpawner spawner, BulletOwner ownerType, Damageable ownerEntity);
}

