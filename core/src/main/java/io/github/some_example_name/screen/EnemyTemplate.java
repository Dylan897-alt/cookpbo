package io.github.some_example_name.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;
import io.github.some_example_name.character.*;
import io.github.some_example_name.weapon.*;

import java.security.cert.CertificateParsingException;

public class EnemyTemplate {
    private float hp;
    private float exp;
    private int type;
    private Texture bulletTexture;
    private String filePath;
    private String animationName;
    private float frameDuration;

    public EnemyTemplate(float frameDuration, float hp, float exp, int type, String filePath, String animationName, Texture bulletTexture){
        this.hp = hp;
        this.exp = exp;
        this.type = type;
        this.bulletTexture = bulletTexture;
        this.frameDuration = frameDuration;
        this.filePath = filePath;
        this.animationName = animationName;
    }

    public Enemy createEnemy(Vector2 spawnPos){
        Weapon weapon = new NoWeapon();
        if(type == 1){
            return new SingleShotEnemy(frameDuration, hp, exp, new TextureAtlas(Gdx.files.internal(filePath)), animationName, bulletTexture, spawnPos);
        } else if(type == 2){
            return new TripleShotEnemy(frameDuration, hp, exp, new TextureAtlas(Gdx.files.internal(filePath)), animationName, bulletTexture, spawnPos);
        } else if(type == 3){
            return new SpamShotEnemy(frameDuration, hp, exp, new TextureAtlas(Gdx.files.internal(filePath)), animationName, bulletTexture, spawnPos);
        } else if(type == 4){
            return new FiveShotEnemy(frameDuration, hp, exp, new TextureAtlas(Gdx.files.internal(filePath)), animationName, bulletTexture, spawnPos);
        } else if(type == 5){
            return new BomberEnemy(frameDuration, hp, exp, new TextureAtlas(Gdx.files.internal(filePath)), animationName, bulletTexture, spawnPos);
        } else if(type == 6){
            return new MeleeEnemy(frameDuration, hp, exp, new TextureAtlas(Gdx.files.internal(filePath)), animationName, bulletTexture, spawnPos);
        }
        return new Enemy(frameDuration, hp, exp, weapon, new TextureAtlas(Gdx.files.internal(filePath)), animationName, bulletTexture, spawnPos);
    }
}
