package io.github.some_example_name.character;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import io.github.some_example_name.screen.Stage1;
import io.github.some_example_name.weapon.Weapon;
import org.w3c.dom.Text;

public class Enemy extends Character {
    private Animation<TextureRegion> animation;
    private float stateTime;
    private Weapon weapon;
    private Texture bulletTexture;

    public Enemy(int frameWidth, int frameHeight, float frameDuration, float hp, float exp, Weapon weapon, Texture spriteSheet, Texture bulletTexture, Vector2 spawnPos) {
        super(new Sprite(), hp, exp);
        //animasi di handle di FrameHandler nanti
        TextureRegion[][] tmp = TextureRegion.split(spriteSheet, frameWidth, frameHeight);
        Array<TextureRegion> frames = new Array<>();

        for (TextureRegion region : tmp[0]) {
            frames.add(region);
        }

        this.animation = new Animation<>(frameDuration, frames, Animation.PlayMode.LOOP);
        this.stateTime = 0f;

        sprite.setRegion(frames.first());
        sprite.setSize(frameWidth / Stage1.PPU, frameHeight / Stage1.PPU);
        this.setPosition(spawnPos.x, spawnPos.y);
    }

    @Override
    public void update(float delta) {
        stateTime += delta;
        sprite.setRegion(animation.getKeyFrame(stateTime));
        sprite.translateX(-2f * delta); // ganti pakai static global variable
    }

    public void setWeapon(Weapon weapon){
        this.weapon = weapon;
    }
}
