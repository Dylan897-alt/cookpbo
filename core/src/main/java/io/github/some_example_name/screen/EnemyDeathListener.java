package io.github.some_example_name.screen;

import io.github.some_example_name.character.Enemy;
import io.github.some_example_name.character.Player;

public interface EnemyDeathListener {
    void onEnemyDied(Enemy enemy);
}
