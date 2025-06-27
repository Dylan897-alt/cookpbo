package io.github.some_example_name.character;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import io.github.some_example_name.ShooterGame;
import io.github.some_example_name.object.Bullet;
import io.github.some_example_name.object.BulletOwner;
import io.github.some_example_name.utils.FrameHandler;
import io.github.some_example_name.weapon.SingleShotWeapon;
import io.github.some_example_name.weapon.Weapon;
import org.w3c.dom.Text;

import java.util.ArrayList;

public class Player extends Character {
    private int level = 1;
    private float expToNextLevel = 10f;
    private final ArrayList<Upgrade> upgrades = new ArrayList<>();

    private Animation<TextureRegion> walkRightAnim;
    private Animation<TextureRegion> walkLeftAnim;
    private Animation<TextureRegion> walkUpAnim;
    private Animation<TextureRegion> walkDownAnim;
    private Animation<TextureRegion> currentAnimation;
    private float animationTime = 0f;
    private Weapon weapon;
    private Texture bulletTexture;
    private float invincibilityCooldown = 0f;
    private static final float INVINCIBILITY_FRAME = 1f; // 1 second

    public Player(float hp, float exp, Texture spriteSheet, Texture bulletTexture) {
        super(new Sprite(), hp, exp);
        float frameDuration = 0.1f;

        int frameCols = 8;
        int frameRows = 4;


        int frameWidth = spriteSheet.getWidth() / frameCols;
        int frameHeight = spriteSheet.getHeight() / frameRows;

        walkLeftAnim = FrameHandler.createAnimation(spriteSheet, frameWidth, frameHeight, frameDuration, 1, true);
        walkRightAnim = FrameHandler.createAnimation(spriteSheet, frameWidth, frameHeight, frameDuration, 0, true);
        walkDownAnim = FrameHandler.createAnimation(spriteSheet, frameWidth, frameHeight, frameDuration, 3, true);
        walkUpAnim = FrameHandler.createAnimation(spriteSheet, frameWidth, frameHeight, frameDuration, 2, true);

        TextureRegion firstFrame = walkDownAnim.getKeyFrame(0);

        setCurrentAnimation(walkDownAnim);
        sprite.setRegion(currentAnimation.getKeyFrame(0));


        float displayHeight = .6f;
        float aspectRatio = (float) firstFrame.getRegionWidth() / firstFrame.getRegionHeight();
        float displayWidth = displayHeight * aspectRatio;
        sprite.setSize(displayWidth, displayHeight);

        this.bulletTexture = bulletTexture;
        this.weapon = new SingleShotWeapon(.8f, 1.5f);
    }

    @Override
    public float getHp() {
        return super.getHp();
    }

    public void setWeapon(Weapon weapon){
        this.weapon = weapon;
    }

    public void move(Vector2 delta) {
        sprite.translate(delta.x, delta.y);
        float clampedX = MathUtils.clamp(sprite.getX(), 0, ShooterGame.VIRTUAL_WIDTH - sprite.getWidth());
        float clampedY = MathUtils.clamp(sprite.getY(), 0, ShooterGame.VIRTUAL_HEIGHT - .65f - sprite.getHeight());

        sprite.setPosition(clampedX, clampedY);
    }

    @Override
    public void update(float delta){
        animationTime += delta;
        if (currentAnimation != null) {
            sprite.setRegion(currentAnimation.getKeyFrame(animationTime, true));
        }
        weapon.update(delta);
        if (invincibilityCooldown > 0f) {
            invincibilityCooldown -= delta;
        }
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

    @Override
    public void takeDamage(float damage){
        if (canTakeDamage()) {
            super.takeDamage(damage);
            invincibilityCooldown = INVINCIBILITY_FRAME;
            // Optional: play damage sound, flash sprite, etc.
        }
    }

    public boolean canTakeDamage(){
        return invincibilityCooldown <= 0f;
    }

    @Override
    public void draw(SpriteBatch batch){
        if (canTakeDamage() || ((int)(invincibilityCooldown * 10) % 2 == 0)) {
            sprite.draw(batch); // flashes while invincible
        }
    }

    public void addExp(float value){
        setExp(getExp() + value);
        while (getExp() >= expToNextLevel) {
            levelUp();
        }
    }

    public float getLevel(){
        return level;
    }

    private void levelUp() {
        level++;
        float excess = getExp() - expToNextLevel;
        expToNextLevel *= 1.5f;
        setExp(excess);

        if (level <= 10 && level - 1 < upgrades.size()) {
            upgrades.get(level - 1).apply(this);
            System.out.println("Level " + level + " upgrade: " + upgrades.get(level - 1).getDescription());
        } else {
            new IncreaseDamage().apply(this);
            System.out.println("Level " + level + ": Permanent damage increase");
        }
    }

}
