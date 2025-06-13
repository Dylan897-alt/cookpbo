package io.github.some_example_name;

import com.badlogic.gdx.Game;

public class ShooterGame extends Game {
    public final float VIRTUAL_WIDTH = 8;
    public final float VIRTUAL_HEIGHT = 5;

    @Override
    public void create() {
        this.setScreen(new GameScreen(this));
    }
}
