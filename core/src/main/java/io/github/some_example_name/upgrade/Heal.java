package io.github.some_example_name.upgrade;

import io.github.some_example_name.character.Player;

public class Heal implements Upgrade {
    @Override
    public void apply(Player player) {
        player.heal(5);
    }

    @Override
    public String getDescription() {
        return "Heal 5 HP";
    }
}
