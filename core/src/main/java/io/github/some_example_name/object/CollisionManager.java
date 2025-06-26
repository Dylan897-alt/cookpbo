package io.github.some_example_name.object;

import io.github.some_example_name.Collidable;
import io.github.some_example_name.Damageable;
import io.github.some_example_name.character.Enemy;
import io.github.some_example_name.character.Player;
import io.github.some_example_name.screen.EnemyManager;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CollisionManager {
    public void handleAllCollisions(Player player, BulletManager bulletManager, EnemyManager enemyManager) {
        checkBulletCollisions(player, bulletManager, enemyManager);
        checkPlayerEnemyCollision(player, enemyManager.getEnemies());
    }

    private void checkBulletCollisions(Player player, BulletManager bulletManager, EnemyManager enemyManager) {
        ArrayList<Bullet> bullets = bulletManager.getBullets();
        ArrayList<Enemy> enemies = enemyManager.getEnemies();
        Iterator<Bullet> bulletIterator = bullets.iterator();
        List<Bullet> toRemove = new ArrayList<>();

        while (bulletIterator.hasNext()) {
            Bullet bullet = bulletIterator.next();

            if (bullet.getOwner() == BulletOwner.PLAYER) {
                for (Enemy enemy : enemies) {
                    if (bullet.getHitbox().overlaps(enemy.getHitbox())) {
                        enemy.takeDamage(1); // adjust damage as needed
                        bulletIterator.remove();
                        break;
                    }
                }
            } else if (bullet.getOwner() == BulletOwner.ENEMY) {
                if (bullet.getHitbox().overlaps(player.getHitbox())) {
                    player.takeDamage(1); // adjust damage as needed
                    bulletIterator.remove();
                }
            }
        }
    }

    private void checkPlayerEnemyCollision(Player player, ArrayList<Enemy> enemies) {
        for (Enemy enemy : enemies) {
            if (player.getHitbox().overlaps(enemy.getHitbox())) {
                player.takeDamage(1); // adjust damage as needed
            }
        }
    }
}

