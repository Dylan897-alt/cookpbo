package io.github.some_example_name.screen;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import io.github.some_example_name.ShooterGame;
import io.github.some_example_name.character.Enemy;
import io.github.some_example_name.character.MeleeEnemy;
import io.github.some_example_name.character.Player;
import io.github.some_example_name.object.BulletManager;
import java.util.ArrayList;
import java.util.Random;

public class EnemyManager {
    private ArrayList<EnemyTemplate> templateStage1 = new ArrayList<>();
    private ArrayList<EnemyTemplate> templateStage2 = new ArrayList<>();
    private ArrayList<Enemy> enemies = new ArrayList<>();
    private Random random = new Random(); //pake cara lain gpp
    private float cooldown = 3f;
    private int totalSpawneds1 = 0;//batas spawn
    private final int MAX_SPAWNs1 = 20;
    private final Player player;
    private EnemyDeathListener listener;

    public EnemyManager(Player player){
        this.player = player;
//        templateStage1.add(new EnemyTemplate(.1f, 3, 50, 3, "stage2_triple_shot.atlas", "0_Monster_Fly", new Texture("bullet1.png")));
//        templateStage1.add(new EnemyTemplate(.1f, 3, 5, 5, "stage2_triple_shot.atlas", "0_Monster_Fly", new Texture("bullet1.png")));
//        templateStage1.add(new EnemyTemplate(.1f, 3, 2, 4, "stage2_triple_shot.atlas", "0_Monster_Fly", new Texture("bullet1.png")));
//        templateStage1.add(new EnemyTemplate(.1f, 3, 2, 2, "stage2_triple_shot.atlas", "0_Monster_Fly", new Texture("bullet1.png")));
//        templateStage1.add(new EnemyTemplate(.1f, 3, 10, 1, "stage4_single.atlas", "0_Monster_Walking", new Texture("bullet1.png")));
        templateStage1.add(new EnemyTemplate(.1f, 3, 50, 6, "stage2_single_shot.atlas", "0_Monster_Fly", new Texture("bullet1.png")));
    }

    public void setListener(EnemyDeathListener listener){
        this.listener = listener;
    }

    public void handleSpawnStage1(float delta, boolean isStageCleared){
        if (isStageCleared){
            enemies.clear();
            return;
        }

        cooldown -= delta;

        if (cooldown <= 0) {
            cooldown = 1f + random.nextFloat() * 2f;

            float enemyWidth = 0.8f * ShooterGame.SCALE;
            float enemyHeight = 0.6f * ShooterGame.SCALE;
            final float margin = 0.75f * ShooterGame.SCALE;

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
            //totalSpawneds1++;
        }
    }


    public void handleSpawnStage2(float delta, boolean isStageCleared){

    }

    public void handleSpawnStage3(float delta){

    }

    public void handleSpawnStage4(float delta){

    }

    public void drawAll(SpriteBatch batch){
        for(Enemy enemy: enemies){
            enemy.draw(batch);
        }
    }

    public void updateEnemies(float delta, BulletManager bulletManager, Player player){
        for(int i = enemies.size() -1; i >= 0; i--){
            Enemy enemy = enemies.get(i);
            enemy.update(delta);
            enemy.updateWeapon(delta, bulletManager, player);

            if (!enemy.isAlive()) {
                listener.onEnemyDied(enemy);
                // Optional: play explosion animation, sound, or grant EXP
                enemies.remove(i);
            }
        }
    }

    public void spawnEnemy(Vector2 spawnPos){
        EnemyTemplate template = templateStage1.get(random.nextInt(templateStage1.size()));
        Enemy enemy = template.createEnemy(spawnPos);
        if(enemy instanceof MeleeEnemy){
            ((MeleeEnemy) enemy).setTargetPlayer(player);
        }
        enemies.add(enemy);
    }

    public ArrayList<Enemy> getEnemies(){
        return enemies;
    }
}
