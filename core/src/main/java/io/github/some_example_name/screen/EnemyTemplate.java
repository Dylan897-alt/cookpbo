package io.github.some_example_name.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;
import io.github.some_example_name.character.Enemy;
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
        return new Enemy(frameDuration, hp, exp, weapon, new TextureAtlas(Gdx.files.internal(filePath)), animationName, bulletTexture, spawnPos);
    }
}
