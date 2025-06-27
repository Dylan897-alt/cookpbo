package io.github.some_example_name.character;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import io.github.some_example_name.object.BulletManager;
import io.github.some_example_name.object.BulletOwner;
import io.github.some_example_name.screen.Stage1;
import io.github.some_example_name.utils.FrameHandler;
import io.github.some_example_name.weapon.Weapon;

public class Enemy extends Character {
    private Animation<TextureRegion> animation;
    private float stateTime;
    private Weapon weapon;
    private Texture bulletTexture;
    private TextureAtlas atlas;


    public Enemy(float frameDuration, float hp, float exp, Weapon weapon, TextureAtlas atlas, String animationName, Texture bulletTexture, Vector2 spawnPos) {
        super(new Sprite(), hp, exp);
        this.atlas = atlas;
        Array<TextureAtlas.AtlasRegion> frames = atlas.findRegions(animationName);
        this.animation = new Animation<>(frameDuration, frames, Animation.PlayMode.LOOP);
        this.stateTime = 0f;

        this.bulletTexture = bulletTexture;
        this.weapon = weapon;

        TextureRegion firstFrame = animation.getKeyFrame(0f);
        sprite.setRegion(firstFrame);

        float displayHeight = 1f; // World units
        float aspectRatio = (float) firstFrame.getRegionWidth() / firstFrame.getRegionHeight();
        float displayWidth = displayHeight * aspectRatio;
        sprite.setSize(displayWidth, displayHeight);

        this.setPosition(spawnPos.x, spawnPos.y);
    }

    @Override
    public void update(float delta) {
        stateTime += delta;
        sprite.setRegion(animation.getKeyFrame(stateTime));
    }

    public void updateWeapon(float delta, BulletManager spawner, Player player){
        weapon.update(delta);
        if(weapon.canFire()){
            Vector2 origin = getCenter();
            Vector2 direction = new Vector2(player.getCenter()).sub(origin);
            weapon.fire(bulletTexture, origin, direction, spawner, BulletOwner.ENEMY, this);
        }
    }

    public boolean isAlive() {
        return getHp() > 0;
    }
}
