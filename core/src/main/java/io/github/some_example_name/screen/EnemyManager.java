package io.github.some_example_name.screen;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import io.github.some_example_name.character.Enemy;
import io.github.some_example_name.character.Player;
import io.github.some_example_name.object.Bullet;
import io.github.some_example_name.object.BulletManager;
import io.github.some_example_name.weapon.SingleShotWeapon;
import io.github.some_example_name.weapon.TripleShotWeapon;

import java.util.ArrayList;
import java.util.Random;

public class EnemyManager {
    private ArrayList<EnemyTemplate> templateStage1 = new ArrayList<>();
    private ArrayList<EnemyTemplate> templateStage2 = new ArrayList<>();
    private ArrayList<Enemy> enemies = new ArrayList<>();
    private Random random = new Random(); //pake cara lain gpp
    private float cooldown = 1f;

    public EnemyManager(){
        templateStage1.add(new EnemyTemplate(48, 32, .1f, 10, 2, new SingleShotWeapon(1.2f), new Texture("Canine_Black_Attack.png"), new Texture("2.png")));
        templateStage1.add(new EnemyTemplate(48, 32, .1f, 10, 2, new TripleShotWeapon(1.2f), new Texture("Canine_Black_Attack.png"), new Texture("2.png")));
        templateStage2.add(new EnemyTemplate(48, 32, .1f, 10, 2, new TripleShotWeapon(1.2f), new Texture("Canine_Black_Attack.png"), new Texture("2.png")));
        templateStage2.add(new EnemyTemplate(48, 32, .1f, 10, 2, new TripleShotWeapon(1.2f), new Texture("Canine_Black_Attack.png"), new Texture("2.png")));
        templateStage2.add(new EnemyTemplate(48, 32, .1f, 10, 2, new SingleShotWeapon(1.2f), new Texture("Canine_Black_Attack.png"), new Texture("2.png")));
    }

    public void handleSpawnStage1(float delta){
        cooldown -= delta;
        //koordinatnya benerin ward
        if (cooldown <= 0) {
            cooldown = 1f + random.nextFloat() * 2f;
            float xPosition = random.nextFloat() * 8;
            float yPosition = random.nextFloat() * 5;;
            Vector2 spawnPos = new Vector2(xPosition, yPosition);
            spawnEnemy(spawnPos);
        }
    }

    public void handleSpawnStage2(float delta){

    }

    public void drawAll(SpriteBatch batch){
        for(Enemy enemy: enemies){
            enemy.draw(batch);
        }
    }

    public void updateEnemies(float delta, BulletManager bulletManager, Player player){
        for(Enemy enemy: enemies){
            enemy.update(delta);
            enemy.updateWeapon(delta, bulletManager, player);
        }
    }

    public void spawnEnemy(Vector2 spawnPos){
        EnemyTemplate template = templateStage1.get(random.nextInt(templateStage1.size()));
        enemies.add(template.createEnemy(spawnPos));
    }
}
