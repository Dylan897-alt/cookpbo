package io.github.some_example_name.object;

import io.github.some_example_name.Collidable;
import io.github.some_example_name.Damageable;
import io.github.some_example_name.character.Enemy;
import io.github.some_example_name.character.Player;

import java.util.ArrayList;
import java.util.Iterator;

public class CollisionManager {
    public void handleAllCollisions(ArrayList<Bullet> bullets, ArrayList<Collidable> collidables) {
        checkBulletCollisions(bullets, collidables);
        checkPlayerEnemyCollision(collidables);
    }

    private void checkBulletCollisions(ArrayList<Bullet> bullets, ArrayList<Collidable> targets) {
        Iterator<Bullet> bulletIterator = bullets.iterator();

        while (bulletIterator.hasNext()) {
            Bullet bullet = bulletIterator.next();

            for (Collidable target : targets) {
                if (bullet.getOwner() == BulletOwner.PLAYER && target instanceof Player) continue;
                if (bullet.getOwner() == BulletOwner.ENEMY && target instanceof Enemy) continue;

                if (bullet.getHitbox().overlaps(target.getHitbox())) {
                    if (target instanceof Damageable) {
                        ((Damageable) target).takeDamage(1); //sesuaikan lagi
                        bulletIterator.remove();
                        break;
                    }
                }
            }
        }
    }

    private void checkPlayerEnemyCollision(ArrayList<Collidable> collidables) {
        Player player = null;
        ArrayList<Enemy> enemies = new ArrayList<>();

        for (Collidable c : collidables) {
            if (c instanceof Player) {
                player = (Player) c;
            } else if (c instanceof Enemy) {
                enemies.add((Enemy) c);
            }
        }

        if (player == null) return;

        for (Enemy enemy : enemies) {
            if (player.getHitbox().overlaps(enemy.getHitbox())) {
                player.takeDamage(1); //sesuaikan lagi
            }
        }
    }
}

