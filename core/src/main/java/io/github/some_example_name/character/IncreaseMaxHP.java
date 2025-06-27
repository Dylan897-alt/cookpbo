package io.github.some_example_name.character;

public class IncreaseMaxHP implements Upgrade {
    @Override
    public void apply(Player player) {
        player.setHp(player.getHp() + 5);
    }

    @Override
    public String getDescription() {
        return "Max HP +5";
    }
}
