package io.github.some_example_name.weapon;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import io.github.some_example_name.object.BulletOwner;
import io.github.some_example_name.object.BulletSpawner;

public class SpamShotWeapon extends Weapon {
    private static final int TOTAL_SHOTS = 10;
    private static final float SHOT_INTERVAL = 0.1f; // waktu antar peluru (detik)
    private static final float FULL_COOLDOWN = 5f;

    private int shotsFired = 0;
    private float shotTimer = 0f;
    private boolean isFiring = false;

    public SpamShotWeapon(float dummyCooldown) {
        super(dummyCooldown); // cooldown bawaan ga dipakai, pakai logika sendiri
    }

    @Override
    public void update(float delta) {
        if (isFiring) {
            shotTimer -= delta;
            if (shotTimer <= 0 && shotsFired < TOTAL_SHOTS) {
                // Tembak satu peluru arah random setiap interval
                fireRandom();
                shotsFired++;
                shotTimer = SHOT_INTERVAL;
            }

            // Selesai semua peluru
            if (shotsFired >= TOTAL_SHOTS) {
                isFiring = false;
                this.cooldown = FULL_COOLDOWN;
            }
        } else {
            super.update(delta);
        }
    }

    @Override
    public void fire(Texture bulletTexture, Vector2 origin, Vector2 directionIgnored, BulletSpawner spawner, BulletOwner owner) {
        if (!isFiring && canFire()) {
            this.spawner = spawner;
            this.bulletTexture = bulletTexture;
            this.origin = origin;
            this.owner = owner;

            isFiring = true;
            shotsFired = 0;
            shotTimer = 0f; // langsung tembak peluru pertama
        }
    }

    // Data disimpan sementara
    private BulletSpawner spawner;
    private Texture bulletTexture;
    private Vector2 origin;
    private BulletOwner owner;

    private void fireRandom() {
        float angle = MathUtils.random(0f, 360f);
        Vector2 dir = new Vector2(1, 0).setAngleDeg(angle);
        spawner.spawnBullet(bulletTexture, origin.cpy(), dir, owner, speedModifier);
    }
}
