package io.github.some_example_name.character;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import io.github.some_example_name.object.Bullet;
import io.github.some_example_name.object.BulletOwner;
import io.github.some_example_name.utils.FrameHandler;
import io.github.some_example_name.weapon.SingleShotWeapon;
import io.github.some_example_name.weapon.Weapon;
import org.w3c.dom.Text;

import java.util.ArrayList;

public class Player extends Character {
    private Animation<TextureRegion> walkRightAnim;
    private Animation<TextureRegion> walkLeftAnim;
    private Animation<TextureRegion> walkUpAnim;
    private Animation<TextureRegion> walkDownAnim;
    private Animation<TextureRegion> currentAnimation;
    private float animationTime = 0f;
    private Weapon weapon;
    private Texture bulletTexture;
    ArrayList<Bullet> bullets = new ArrayList<>(); //hapus, ganti di bullet manager

    public Player(float hp, float exp, Texture texture, Texture bulletTexture) {
        super(new Sprite(texture), hp, exp);
        sprite.setSize(1f, 1f);
        float frameDuration = 0.1f;

        int frameCols = 8;
        int frameRows = 4;
        Texture spriteSheet = new Texture("mc.png");


        int frameWidth = spriteSheet.getWidth() / frameCols;
        int frameHeight = spriteSheet.getHeight() / frameRows;

        walkLeftAnim = FrameHandler.createAnimation(spriteSheet, frameWidth, frameHeight, frameDuration, 1, true);
        walkRightAnim = FrameHandler.createAnimation(spriteSheet, frameWidth, frameHeight, frameDuration, 0, true);
        walkDownAnim = FrameHandler.createAnimation(spriteSheet, frameWidth, frameHeight, frameDuration, 3, true);
        walkUpAnim = FrameHandler.createAnimation(spriteSheet, frameWidth, frameHeight, frameDuration, 2, true);

        setCurrentAnimation(walkDownAnim);
        sprite.setRegion(currentAnimation.getKeyFrame(0));

        this.bulletTexture = bulletTexture;
        this.weapon = new SingleShotWeapon(.8f);
    }

    public void setWeapon(Weapon weapon){
        this.weapon = weapon;
    }

    public void move(Vector2 delta) {
        sprite.translate(delta.x, delta.y);
    }

    @Override
    public void update(float delta){
        animationTime += delta;
        if (currentAnimation != null) {
            sprite.setRegion(currentAnimation.getKeyFrame(animationTime, true));
        }
        weapon.update(delta);
    }

    public Animation<TextureRegion> getWalkRightAnim() { return walkRightAnim; }
    public Animation<TextureRegion> getWalkLeftAnim() { return walkLeftAnim; }
    public Animation<TextureRegion> getWalkUpAnim() { return walkUpAnim; }
    public Animation<TextureRegion> getWalkDownAnim() { return walkDownAnim; }

    public void resetAnimation() {
        animationTime = 0;
        if (currentAnimation != null)
            sprite.setRegion(currentAnimation.getKeyFrame(0));
    }

    public void setCurrentAnimation(Animation<TextureRegion> animation) {
        if (currentAnimation != animation) {
            currentAnimation = animation;
            animationTime = 0f; // reset to start of new animation
        }
    }

    public Weapon getWeapon(){
        return weapon;
    }

    public Texture getBulletTexture(){
        return bulletTexture;
    }
}
