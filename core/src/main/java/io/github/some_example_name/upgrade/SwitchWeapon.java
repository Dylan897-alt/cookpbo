package io.github.some_example_name.upgrade;

import io.github.some_example_name.character.Player;
import io.github.some_example_name.weapon.TripleShotWeapon;

public class SwitchWeapon implements Upgrade{
    @Override
    public void apply(Player player) {
        player.setWeapon(new TripleShotWeapon(.5f, 1.8f));
    }

    @Override
    public String getDescription() {
        return "Switch to Triple Shot Weapon";
    }
}
