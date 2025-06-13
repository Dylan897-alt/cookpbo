package io.github.some_example_name;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.FitViewport;
import io.github.some_example_name.character.Player;


public class PlayerController {
    private final Player player;
    private float fireCooldown = .8f;
    private float timeSinceLastShot = 0f;
    private final FitViewport viewport;


    public PlayerController(Player player, FitViewport viewport) {
        this.player = player;
        this.viewport = viewport;
    }

    public void handleInput(float delta) {
        Vector2 moveDelta = new Vector2();
        float speed = 2f;

        if (Gdx.input.isKeyPressed(Input.Keys.D)){
            moveDelta.x += speed * delta;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.A)){
            moveDelta.x -= speed * delta;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.W)){
            moveDelta.y += speed * delta;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.S)){
            moveDelta.y -= speed * delta;
        }
        player.move(moveDelta);
        player.faceDirection(moveDelta.x);

        timeSinceLastShot += delta;
        if (Gdx.input.isButtonPressed(Input.Buttons.LEFT) && timeSinceLastShot >= fireCooldown) {
            Vector2 touchPos = new Vector2(Gdx.input.getX(), Gdx.input.getY());
            viewport.unproject(touchPos);

            Vector2 startPos = player.getCenter();
            Vector2 direction = new Vector2(touchPos).sub(startPos);

            player.addBullet(startPos, direction);
            timeSinceLastShot = 0f;
        }
        //pindah logic nembak ke Weapon
    }
}
