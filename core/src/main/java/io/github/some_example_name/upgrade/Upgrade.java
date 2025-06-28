package io.github.some_example_name.upgrade;

import io.github.some_example_name.character.Player;

public interface Upgrade {
    void apply(Player player);
    String getDescription(); // Optional: for UI
}


