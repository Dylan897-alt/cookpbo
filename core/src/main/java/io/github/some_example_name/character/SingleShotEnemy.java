package io.github.some_example_name.character;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;
import io.github.some_example_name.ShooterGame;
import io.github.some_example_name.weapon.SingleShotWeapon;
import io.github.some_example_name.weapon.Weapon;

public class SingleShotEnemy extends Enemy{
    private float moveSpeed = .6f * ShooterGame.SCALE;
    private int directionIndex = 0; // 0 = down, 1 = left, 2 = up, 3 = right

    private static final Vector2[] DIRECTIONS = {
        new Vector2(0, -1),  // down
        new Vector2(-1, 0),  // left
        new Vector2(0, 1),   // up
        new Vector2(1, 0)    // right
    };

    public SingleShotEnemy(float frameDuration, float hp, float exp, TextureAtlas atlas, String animationName, Texture bulletTexture, Vector2 spawnPos) {
        super(frameDuration, hp, exp, new SingleShotWeapon(1.5f), atlas, animationName, bulletTexture, spawnPos);
    }

    @Override
    public void update(float delta) {
        super.update(delta);

        Vector2 direction = DIRECTIONS[directionIndex];
        float dx = direction.x * moveSpeed * delta;
        float dy = direction.y * moveSpeed * delta;

        sprite.translateX(dx);
        sprite.translateY(dy);

        float x = sprite.getX();
        float y = sprite.getY();
        float width = sprite.getWidth();
        float height = sprite.getHeight();

        switch (directionIndex) {
            case 0: // down
                if (y <= 0f) directionIndex = 1; // left
                break;
            case 1: // left
                if (x <= 0f) directionIndex = 2; // up
                break;
            case 2: // up
                if (y + height >= ShooterGame.VIRTUAL_HEIGHT - (.65f * ShooterGame.SCALE)) directionIndex = 3; // right
                break;
            case 3: // right
                if (x + width >= ShooterGame.VIRTUAL_WIDTH) directionIndex = 0; // down
                break;
        }
    }
}
