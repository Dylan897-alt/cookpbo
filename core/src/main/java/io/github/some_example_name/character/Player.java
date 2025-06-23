package io.github.some_example_name.character;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import io.github.some_example_name.object.Bullet;
import io.github.some_example_name.object.BulletOwner;
import io.github.some_example_name.weapon.Weapon;
import java.util.ArrayList;

public class Player extends Character {
    private Vector2 velocity;
    private boolean isTravelling;
    private boolean facingRight;
    private Weapon weapon;
    ArrayList<Bullet> bullets = new ArrayList<>(); //hapus, ganti di bullet manager

    public Player(float hp, float exp, Texture texture) {
        super(new Sprite(texture), hp, exp);
        sprite.setSize(1f, 1f);
        this.velocity = new Vector2();
        this.isTravelling = false;
        this.facingRight = true;
    }

    public void move(Vector2 delta) {
        sprite.translate(delta.x, delta.y);
    }

    public void faceDirection(float xVelocity) {
        if ((xVelocity > 0 && !facingRight) || (xVelocity < 0 && facingRight)) {
            sprite.flip(true, false);
            facingRight = !facingRight;
        }
    }

    public void setVelocity(Vector2 velocity) {
        this.velocity.set(velocity);
        this.isTravelling = true;
    }

    @Override
    public void update(float delta){
        if (isTravelling) {
            faceDirection(velocity.x);
            sprite.translate(velocity.x * delta, velocity.y * delta);
        }
    }

    public boolean isTravelling() {
        return isTravelling;
    }

    public void stop() {
        isTravelling = false;
        velocity.setZero();
    }

    //ganti di bullet manager
    public void addBullet(Vector2 startPos, Vector2 direction){
        bullets.add(new Bullet(new Texture("2.png"), startPos, direction, BulletOwner.PLAYER));
    }

    //ga perlu
    public ArrayList<Bullet> getBullets(){
        return bullets;
    }
}
