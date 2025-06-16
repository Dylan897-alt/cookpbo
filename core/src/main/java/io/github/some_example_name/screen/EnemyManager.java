package io.github.some_example_name.screen;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import io.github.some_example_name.character.Enemy;
import io.github.some_example_name.object.Bullet;

import java.util.ArrayList;
import java.util.Random;

public class EnemyManager {
    private ArrayList<EnemyTemplate> templateStage1 = new ArrayList<>();
    private ArrayList<EnemyTemplate> templateStage2 = new ArrayList<>();
    private ArrayList<Enemy> enemies = new ArrayList<>();
    private Random random = new Random(); //pake cara lain gpp
    private float cooldown;

    public EnemyManager(){
        //templateStage1.add(new EnemyTemplate(48, 32, .1f, 10, 2, new Weapon(1.2f), new Texture("Canine_Black_Attack.png"), new Texture("2.png")));
        //templateStage1.add(new EnemyTemplate(48, 32, .1f, 10, 2, new Weapon(1.2f), new Texture("Canine_Black_Attack.png"), new Texture("2.png")));
        //templateStage2.add(new EnemyTemplate(48, 32, .1f, 10, 2, new Weapon(1.2f), new Texture("Canine_Black_Attack.png"), new Texture("2.png")));
        //templateStage2.add(new EnemyTemplate(48, 32, .1f, 10, 2, new Weapon(1.2f), new Texture("Canine_Black_Attack.png"), new Texture("2.png")));
        //templateStage2.add(new EnemyTemplate(48, 32, .1f, 10, 2, new Weapon(1.2f), new Texture("Canine_Black_Attack.png"), new Texture("2.png")));
    }

    public void handleSpawnStage1(float delta){

    }

    public void handleSpawnStage2(float delta){

    }

    public void drawAll(SpriteBatch batch){
        for(Enemy enemy: enemies){
            enemy.draw(batch);
        }
    }

    public void updateEnemies(float delta){
        for(Enemy enemy: enemies){
            enemy.update(delta);
        }
    }

    public void spawnEnemy(Vector2 spawnPos){
         //EnemyTemplate template =
        //enemies.add(template.createEnemy(spawnPos));
    }
}
