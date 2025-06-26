package io.github.some_example_name.utils;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;

public class FrameHandler {
    public static Animation<TextureRegion> createAnimation(Texture spriteSheet, int frameWidth, int frameHeight, float frameDuration, boolean loop) {
        TextureRegion[][] tmp = TextureRegion.split(spriteSheet, frameWidth, frameHeight);
        Array<TextureRegion> frames = new Array<>();

        for (TextureRegion region : tmp[0]) {
            frames.add(region);
        }

        Animation<TextureRegion> animation = new Animation<>(frameDuration, frames);
        animation.setPlayMode(loop ? Animation.PlayMode.LOOP : Animation.PlayMode.NORMAL);
        return animation;
    }

    public static Animation<TextureRegion> createAnimation(Texture spriteSheet, int frameWidth, int frameHeight, float frameDuration, int rowIndex, boolean loop) {
        TextureRegion[][] tmp = TextureRegion.split(spriteSheet, frameWidth, frameHeight);
        Array<TextureRegion> frames = new Array<>();

        for (TextureRegion region : tmp[rowIndex]) {
            frames.add(region);
        }

        Animation<TextureRegion> animation = new Animation<>(frameDuration, frames);
        animation.setPlayMode(loop ? Animation.PlayMode.LOOP : Animation.PlayMode.NORMAL);
        return animation;
    }
}

