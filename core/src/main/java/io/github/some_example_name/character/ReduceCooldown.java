package io.github.some_example_name.character;

public class ReduceCooldown implements Upgrade {
    @Override
    public void apply(Player player) {

        float newCooldown = Math.max(0.1f, player.getWeapon().getCooldown() - 0.1f);
        player.getWeapon().setCooldown(newCooldown);
    }

    @Override
    public String getDescription() {
        return "Fire Rate +10%";
    }
}
