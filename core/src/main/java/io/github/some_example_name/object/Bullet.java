package io.github.some_example_name.object;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import io.github.some_example_name.Collidable;
import io.github.some_example_name.Damageable;
import io.github.some_example_name.ShooterGame;

public class Bullet extends GameObject implements Collidable { //pindah ke package object, terus ganti extend GameObject
    private Vector2 startPos;
    private Vector2 velocity;
    private BulletOwner ownerType;
    private Damageable ownerEntity;
    private float speed = 2f * ShooterGame.SCALE;

    public Bullet(Texture texture, Vector2 startPos, Vector2 direction, BulletOwner ownerType, Damageable ownerEntity, float speedModifier){
        super(new Sprite(texture));
        this.ownerType = ownerType;
        this.ownerEntity = ownerEntity;
        this.speed *= speedModifier;
        this.startPos = new Vector2(startPos);
        this.velocity = new Vector2(direction).set(direction.nor().scl(speed));

        float displayHeight = .15f * ShooterGame.SCALE; // World units
        float aspectRatio = (float) sprite.getRegionWidth() / sprite.getRegionHeight();
        float displayWidth = displayHeight * aspectRatio;

        sprite.setSize(displayWidth, displayHeight);
        sprite.setOriginCenter();
        setPosition(startPos.x, startPos.y);
        sprite.setRotation(velocity.angleDeg());
    }


    @Override
    public void update(float delta){
        sprite.translate(velocity.x * delta, velocity.y * delta);
    }

    public float getY() {
        return sprite.getY();
    }

    public float getX() {
        return sprite.getX();//diget buat batas yg di bulman
    }

    public Vector2 getVelocity() {
        return velocity; //bawah bs ke atas
    }


    public BulletOwner getOwnerType(){
        return ownerType;
    }

    public Damageable getOwnerEntity(){
        return ownerEntity;
    }
}
