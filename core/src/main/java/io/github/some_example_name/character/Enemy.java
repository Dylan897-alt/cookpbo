package io.github.some_example_name.character;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.utils.Array;
import io.github.some_example_name.screen.Stage1;
import io.github.some_example_name.weapon.Weapon;

public class Enemy extends Character {
    private Animation<TextureRegion> animation;
    private float stateTime;
    private Weapon weapon;

    public Enemy(Texture spriteSheet, int frameWidth, int frameHeight, float frameDuration) {
        super(new Sprite());
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
        this.setPosition(8, 0);
    }

    @Override
    public void update(float delta) {
        stateTime += delta;
        sprite.setRegion(animation.getKeyFrame(stateTime));
        sprite.translateX(-2f * delta); // ganti pakai static global variable
    }
}
