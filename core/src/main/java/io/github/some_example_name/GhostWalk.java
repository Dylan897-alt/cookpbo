package io.github.some_example_name;

import com.badlogic.gdx.Game;

public class GhostWalk extends Game {
    public final float VIRTUAL_WIDTH = 6.4f;
    public final float VIRTUAL_HEIGHT = 4.80f;

    @Override
    public void create() {
        this.setScreen(new MainMenuScreen(this));
    }
}
