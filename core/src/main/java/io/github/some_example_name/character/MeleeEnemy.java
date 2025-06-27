package io.github.some_example_name.character;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;
import io.github.some_example_name.ShooterGame;
import io.github.some_example_name.weapon.NoWeapon;

public class MeleeEnemy extends Enemy {
    private Player targetPlayer;
    private float moveSpeed = 1f * ShooterGame.SCALE;

    public MeleeEnemy(float frameDuration, float hp, float exp, TextureAtlas atlas, String animationName, Texture bulletTexture, Vector2 spawnPos) {
        super(frameDuration, hp, exp, new NoWeapon(), atlas, animationName, bulletTexture, spawnPos);
    }

    public void setTargetPlayer(Player player) {
        this.targetPlayer = player;
    }

    @Override
    public void update(float delta) {
        super.update(delta);

        if (targetPlayer != null) {
            // Get enemy and player centers
            Vector2 myCenter = getCenter(); // assuming this exists in Enemy
            Vector2 playerCenter = targetPlayer.getCenter();

            // Calculate direction to player
            Vector2 direction = new Vector2(playerCenter).sub(myCenter);
            if (direction.len2() > 0.01f) { // Avoid moving if very close
                direction.nor();
                float dx = direction.x * moveSpeed * delta;
                float dy = direction.y * moveSpeed * delta;
                sprite.translateX(dx);
                sprite.translateY(dy);
            }
        }
    }
}

