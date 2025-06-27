package io.github.some_example_name.character;

public class IncreaseDamage implements Upgrade {
    @Override
    public void apply(Player player) {
        player.setDamage(player.getDamage() + 1); // Assume this exists
        System.out.println(player.getDamage());
    }

    @Override
    public String getDescription() {
        return "Damage +1";
    }
}
