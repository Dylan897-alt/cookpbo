package io.github.some_example_name.object;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import io.github.some_example_name.Damageable;
import io.github.some_example_name.ShooterGame;

import java.util.ArrayList;

public class BulletManager implements BulletSpawner{
    private ArrayList<Bullet> bullets = new ArrayList<>();

    @Override
    public void spawnBullet(Texture texture, Vector2 origin, Vector2 target, BulletOwner ownerType, Damageable ownerEntity, float speedModifier){
        bullets.add(new Bullet(texture, origin, target, ownerType, ownerEntity, speedModifier));
    }

    public void updateBullets(float delta){
        for (int i = bullets.size() - 1; i >= 0; i--) {
            Bullet bullet = bullets.get(i);
            bullet.update(delta);

//            // Batasi peluru dari enemy tidak keluar batas atas/bawah
//            if (bullet.getOwnerType() == BulletOwner.ENEMY) {
//                float y = bullet.getY();
//                float vy = bullet.getVelocity().y;
//
//                // Hanya hapus jika sudah keluar margin DAN bergerak lebih jauh (bukan sedang menuju ke dalam)
//                if ((y < 1f && vy <= 0) || (y > ShooterGame.VIRTUAL_HEIGHT - 1f && vy >= 0)) {
//                    bullets.remove(i);
//                    continue;
//                }
//            }
            float y = bullet.getY();
            float vy = bullet.getVelocity().y;
            float x = bullet.getX();
            float vx = bullet.getVelocity().x;

            if (y < 0 && vy <= 0) {
                bullets.remove(i);
                continue;
            }

            if (y > ShooterGame.VIRTUAL_HEIGHT - 1f && vy >= 0) {
                bullets.remove(i);
                continue;
            }

            if (x < 0 && vx <= 0) {
                bullets.remove(i);
                continue;
            }

            if (x > ShooterGame.VIRTUAL_WIDTH && vx >= 0) {
                bullets.remove(i);
            }
        }
    }


    public void drawAll(SpriteBatch batch) {
        for (Bullet bullet : bullets){
            bullet.draw(batch);
        }
    }

    public ArrayList<Bullet> getBullets(){
        return bullets;
    }

    public void removeBullet(Bullet bullet) {
        bullets.remove(bullet);
    }
}
