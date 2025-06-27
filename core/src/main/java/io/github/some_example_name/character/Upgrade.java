package io.github.some_example_name.character;

public interface Upgrade {
    void apply(Player player);
    String getDescription(); // Optional: for UI
}


