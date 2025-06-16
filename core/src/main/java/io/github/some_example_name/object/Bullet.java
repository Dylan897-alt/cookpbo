package io.github.some_example_name.object;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import io.github.some_example_name.Collidable;
import io.github.some_example_name.character.Character;

public class Bullet extends Character implements Collidable { //pindah ke package object, terus ganti extend GameObject
    private Vector2 startPos;
    private Vector2 velocity;
    private BulletOwner owner;
    private float speed; //ga perlu

    public Bullet(Texture texture, Vector2 startPos, Vector2 direction, BulletOwner owner){
        super(new Sprite(texture));
        this.owner = owner;
        this.speed = 3f; //ganti static global variable
        this.startPos = new Vector2(startPos);
        this.velocity = new Vector2(direction).set(direction.nor().scl(speed));
        sprite.setSize(sprite.getWidth()/10f, sprite.getHeight()/10f);
        sprite.setOriginCenter();
        setPosition(startPos.x, startPos.y);
        sprite.setRotation(velocity.angleDeg());
    }

    @Override
    public void update(float delta){
        sprite.translate(velocity.x * delta, velocity.y * delta);
    }

    public BulletOwner getOwner(){
        return owner;
    }
}
