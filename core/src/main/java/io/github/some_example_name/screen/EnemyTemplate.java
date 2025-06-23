package io.github.some_example_name.screen;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import io.github.some_example_name.character.Enemy;
import io.github.some_example_name.weapon.*;

import java.security.cert.CertificateParsingException;

public class EnemyTemplate {
    private float hp;
    private float exp;
    private int type;
    private Texture bulletTexture;
    private Texture spriteSheet;
    private int frameWidth;
    private int frameHeight;
    private float frameDuration;

    public EnemyTemplate(int frameWidth, int frameHeight, float frameDuration, float hp, float exp, int type, Texture spriteSheet, Texture bulletTexture){
        this.hp = hp;
        this.exp = exp;
        this.type = type;
        this.spriteSheet = spriteSheet;
        this.bulletTexture = bulletTexture;
        this.frameWidth = frameWidth;
        this.frameHeight = frameHeight;
        this.frameDuration = frameDuration;
    }

    public Enemy createEnemy(Vector2 spawnPos){
        Weapon weapon = null;
        if(type == 1){
            weapon = new SingleShotWeapon(1.2f);
        } else if(type == 2){
            weapon = new TripleShotWeapon(2.0f);
        } else if(type == 3){
            weapon = new SpamShotWeapon(4.0f);
        } else if(type == 4){
            weapon =  new FiveShotWeapon(3f);
        } else if(type == 5){
            weapon = new BomberWeapon(2.0f);
        }
        return new Enemy(frameWidth, frameHeight, frameDuration, hp, exp, weapon, spriteSheet, bulletTexture, spawnPos);
    }
}
