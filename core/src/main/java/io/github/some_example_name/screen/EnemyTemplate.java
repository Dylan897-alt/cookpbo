package io.github.some_example_name.screen;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import io.github.some_example_name.character.Enemy;
import io.github.some_example_name.weapon.Weapon;

public class EnemyTemplate {
    private float hp;
    private float exp;
    private Weapon weapon;
    private Texture bulletTexture;
    private Texture spriteSheet;
    private int frameWidth;
    private int frameHeight;
    private float frameDuration;

    public EnemyTemplate(int frameWidth, int frameHeight, float frameDuration, float hp, float exp, Weapon weapon, Texture spriteSheet, Texture bulletTexture){
        this.hp = hp;
        this.exp = exp;
        this.weapon = weapon;
        this.spriteSheet = spriteSheet;
        this.bulletTexture = bulletTexture;
        this.frameWidth = frameWidth;
        this.frameHeight = frameHeight;
        this.frameDuration = frameDuration;
    }

    public Enemy createEnemy(Vector2 spawnPos){
        return new Enemy(frameWidth, frameHeight, frameDuration, hp, exp, weapon, spriteSheet, bulletTexture, spawnPos);
    }
}
