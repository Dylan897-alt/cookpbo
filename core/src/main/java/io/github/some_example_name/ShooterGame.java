package io.github.some_example_name;

import com.badlogic.gdx.Game;
import io.github.some_example_name.screen.MainMenu;
import io.github.some_example_name.screen.Stage1;

public class ShooterGame extends Game {
    public static final float SCALE = 10;
    public static final float VIRTUAL_WIDTH = 8 * SCALE;
    public static final float VIRTUAL_HEIGHT = 5 * SCALE;

    @Override
    public void create() {
        this.setScreen(new MainMenu(this));
    }
}
