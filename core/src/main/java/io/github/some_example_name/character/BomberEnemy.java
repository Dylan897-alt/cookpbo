package io.github.some_example_name.character;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;
import io.github.some_example_name.weapon.BomberWeapon;
import io.github.some_example_name.weapon.TripleShotWeapon;

public class BomberEnemy extends Enemy {
    private float angle = 0f;
    private float radius = 1.5f;
    private float rotationSpeed = 90f; // degrees per second
    private float downwardSpeed = 0.3f;

    private final Vector2 center;

    public BomberEnemy(float frameDuration, float hp, float exp, TextureAtlas atlas, String animationName, Texture bulletTexture, Vector2 spawnPos) {
        super(frameDuration, hp, exp, new BomberWeapon(2f), atlas, animationName, bulletTexture, spawnPos);
        center = new Vector2(spawnPos); // orbit around initial spawn point
    }

    @Override
    public void update(float delta) {
        super.update(delta);

        // Update angle (convert to radians for sin/cos)
        angle += rotationSpeed * delta;
        float rad = (float)Math.toRadians(angle);

        // Optional: drift the center down as it rotates
        center.y -= downwardSpeed * delta;

        // Calculate new position on the circle
        float newX = center.x + radius * (float)Math.cos(rad);
        float newY = center.y + radius * (float)Math.sin(rad);

        sprite.setPosition(newX, newY);
    }
}

