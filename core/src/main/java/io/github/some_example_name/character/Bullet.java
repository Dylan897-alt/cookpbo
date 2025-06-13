package io.github.some_example_name.character;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.FitViewport;

public class Bullet extends Character{
    private Vector2 startPos;
    private Vector2 velocity;
    private float speed;

    public Bullet(Texture texture, Vector2 startPos, Vector2 direction){
        super(new Sprite(texture));
        this.speed = 3f;
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
}
