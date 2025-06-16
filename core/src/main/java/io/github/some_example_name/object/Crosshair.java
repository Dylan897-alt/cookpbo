package io.github.some_example_name.object;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.FitViewport;
import io.github.some_example_name.character.Character;
import io.github.some_example_name.character.Player;

public class Crosshair extends Character { //pindah ke package object, terus ganti extend GameObject
    private final FitViewport viewport;
    private final Player player;
    private final Vector2 touchPos;
    private final Vector2 mousePos;

    public Crosshair(Texture texture, FitViewport viewport, Player player){
        super(new Sprite(texture));
        sprite.setSize(sprite.getWidth()/200f, sprite.getHeight()/200f);
        this.viewport = viewport;
        this.player = player;
        this.touchPos = new Vector2();
        this.mousePos = new Vector2();
    }

    @Override
    public void update(float delta){
        mousePos.set(Gdx.input.getX(), Gdx.input.getY());
        viewport.unproject(mousePos);
        this.setCenter(mousePos.x, mousePos.y);
    }
}
