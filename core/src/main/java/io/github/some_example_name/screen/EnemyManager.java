package io.github.some_example_name.screen;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import io.github.some_example_name.ShooterGame;
import io.github.some_example_name.character.Enemy;
import io.github.some_example_name.character.Player;
import io.github.some_example_name.object.BulletManager;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Random;

public class EnemyManager {
    private ArrayList<EnemyTemplate> templateStage1 = new ArrayList<>();
    private ArrayList<EnemyTemplate> templateStage2 = new ArrayList<>();
    private ArrayList<Enemy> enemies = new ArrayList<>();
    private Random random = new Random(); //pake cara lain gpp
    private float cooldown = 1f;
    private int totalSpawneds1 = 0;//batas spawn
    private final int MAX_SPAWNs1 = 20;


    public EnemyManager(){
//        templateStage1.add(new EnemyTemplate(713, 541, .2f, 10, 2, 1, new Texture("stage1_single_shot.png"), new Texture("2.png")));
        templateStage1.add(new EnemyTemplate(.1f, 10, 2, 2, "stage2_triple_shot.atlas", "0_Monster_Fly", new Texture("3.png")));
//        templateStage1.add(new EnemyTemplate(.1f, 10, 2, 3, "stage2_triple_shot.atlas", "0_Monster_Fly", new Texture("2.png")));
//        templateStage1.add(new EnemyTemplate(48, 32, .1f, 10, 2,4, new Texture("Canine_Black_Attack.png"), new Texture("2.png")));
//        templateStage1.add(new EnemyTemplate(48, 32, .1f, 10, 2, 5, new Texture("Canine_Black_Attack.png"), new Texture("2.png")));
    }

    public void handleSpawnStage1(float delta){
        if (totalSpawneds1 >= MAX_SPAWNs1) return; //

        cooldown -= delta;

        if (cooldown <= 0) {
            cooldown = 1f + random.nextFloat() * 2f;

            float enemyWidth = 0.8f;
            float enemyHeight = 0.6f;
            final float margin = 0.75f;

            int side = random.nextInt(4);
            float x = 0, y = 0;

            switch(side) {
                case 0: // kiri
                    x = 0;
                    y = margin + random.nextFloat() * (ShooterGame.VIRTUAL_HEIGHT - 2 * margin - enemyHeight);
                    break;
                case 1: // kanan
                    x = ShooterGame.VIRTUAL_WIDTH - enemyWidth;
                    y = margin + random.nextFloat() * (ShooterGame.VIRTUAL_HEIGHT - 2 * margin - enemyHeight);
                    break;
                case 2: // atas
                    x = margin + random.nextFloat() * (ShooterGame.VIRTUAL_WIDTH - 2 * margin);
                    y = ShooterGame.VIRTUAL_HEIGHT - enemyHeight - margin;
                    break;
                case 3: // bawah
                    x = margin + random.nextFloat() * (ShooterGame.VIRTUAL_WIDTH - 2 * margin);
                    y = 0;
                    break;
            }

            Vector2 spawnPos = new Vector2(x, y);
            spawnEnemy(spawnPos);
            totalSpawneds1++;
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

    public ArrayList<Enemy> getEnemies(){
        return enemies;
    }
}
