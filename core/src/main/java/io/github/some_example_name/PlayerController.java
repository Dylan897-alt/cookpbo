package io.github.some_example_name;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.FitViewport;
import io.github.some_example_name.character.Player;
import io.github.some_example_name.object.BulletManager;
import io.github.some_example_name.object.BulletOwner;
import io.github.some_example_name.object.BulletSpawner;


public class PlayerController {
    private final Player player;
    private final FitViewport viewport;


    public PlayerController(Player player, FitViewport viewport) {
        this.player = player;
        this.viewport = viewport;
    }

    public void handleInput(float delta, BulletSpawner spawner) {
        Vector2 moveDelta = new Vector2();
        float baseSpeed = 3f;
        float focusSpeed = 1.5f;
        float speed;

        if(Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT)){
            speed = focusSpeed;
        } else{
            speed = baseSpeed;
        }


        boolean isMoving = false;

        if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            moveDelta.x += speed * delta;
            player.setCurrentAnimation(player.getWalkRightAnim());
            isMoving = true;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            moveDelta.x -= speed * delta;
            player.setCurrentAnimation(player.getWalkLeftAnim());
            isMoving = true;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.W)) {
            moveDelta.y += speed * delta;
            player.setCurrentAnimation(player.getWalkUpAnim());
            isMoving = true;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.S)) {
            moveDelta.y -= speed * delta;
            player.setCurrentAnimation(player.getWalkDownAnim());
            isMoving = true;
        }

        if (isMoving) {
            player.move(moveDelta);
        } else {
            player.resetAnimation();
        }


        if (Gdx.input.isButtonPressed(Input.Buttons.LEFT)) {
            if(player.getWeapon().canFire()){
                Vector2 origin = player.getCenter();

                Vector2 touchPos = new Vector2(Gdx.input.getX(), Gdx.input.getY());
                viewport.unproject(touchPos);
                Vector2 direction = new Vector2(touchPos).sub(origin);

                player.getWeapon().fire(player.getBulletTexture(), origin, direction, spawner, BulletOwner.PLAYER, player);
            }
        }
    }
}
