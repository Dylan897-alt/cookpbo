package io.github.some_example_name.object;

import io.github.some_example_name.character.Enemy;
import io.github.some_example_name.character.Player;
import io.github.some_example_name.screen.EnemyManager;

import java.util.ArrayList;
import java.util.Iterator;

public class CollisionManager {
    public void handleAllCollisions(Player player, BulletManager bulletManager, EnemyManager enemyManager) {
        checkBulletCollisions(player, bulletManager, enemyManager);
        checkPlayerEnemyCollision(player, enemyManager.getEnemies());
    }

    private void checkBulletCollisions(Player player, BulletManager bulletManager, EnemyManager enemyManager) {
        ArrayList<Bullet> bullets = bulletManager.getBullets();
        ArrayList<Enemy> enemies = enemyManager.getEnemies();
        Iterator<Bullet> bulletIterator = bullets.iterator();

        while (bulletIterator.hasNext()) {
            Bullet bullet = bulletIterator.next();

            if (bullet.getOwnerType() == BulletOwner.PLAYER) {
                for (Enemy enemy : enemies) {
                    if (bullet.getHitbox().overlaps(enemy.getHitbox())) {
                        enemy.takeDamage(bullet.getOwnerEntity().getDamage());
                        bulletIterator.remove();
                        break;
                    }
                }
            } else if (bullet.getOwnerType() == BulletOwner.ENEMY) {
                if (bullet.getHitbox().overlaps(player.getHitbox())) {
                    player.takeDamage(bullet.getOwnerEntity().getDamage());
                    bulletIterator.remove();
                }
            }
        }
    }

    private void checkPlayerEnemyCollision(Player player, ArrayList<Enemy> enemies) {
        for (Enemy enemy : enemies) {
            if (player.getHitbox().overlaps(enemy.getHitbox())) {
                player.takeDamage(enemy.getDamage());
            }
        }
    }
}

