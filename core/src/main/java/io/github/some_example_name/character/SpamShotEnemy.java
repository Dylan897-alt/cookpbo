package io.github.some_example_name.character;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import io.github.some_example_name.ShooterGame;
import io.github.some_example_name.weapon.SpamShotWeapon;

public class SpamShotEnemy extends Enemy {
    private Vector2 targetPos;
    private float moveSpeed = .7f;

    public SpamShotEnemy(float frameDuration, float hp, float exp, TextureAtlas atlas, String animationName, Texture bulletTexture, Vector2 spawnPos) {
        super(frameDuration, hp, exp, new SpamShotWeapon(2f), atlas, animationName, bulletTexture, spawnPos);
        pickNewTarget();
    }

    @Override
    public void update(float delta) {
        super.update(delta);

        if (targetPos == null) {
            pickNewTarget();
            return;
        }

        Vector2 currentPos = new Vector2(sprite.getX(), sprite.getY());
        Vector2 direction = new Vector2(targetPos).sub(currentPos);

        if (direction.len2() < 0.01f) {
            pickNewTarget();
            return;
        }

        direction.nor();
        float dx = direction.x * moveSpeed * delta;
        float dy = direction.y * moveSpeed * delta;

        sprite.translateX(dx);
        sprite.translateY(dy);
    }

    private void pickNewTarget() {
        float spriteWidth = sprite.getWidth();
        float spriteHeight = sprite.getHeight();

        float minX = 0f;
        float maxX = ShooterGame.VIRTUAL_WIDTH - spriteWidth;

        float minY = 0f;
        float maxY = ShooterGame.VIRTUAL_HEIGHT - .65f - spriteHeight;

        float x = MathUtils.random(minX, maxX);
        float y = MathUtils.random(minY, maxY);

        targetPos = new Vector2(x, y);
    }
}
