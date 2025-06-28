package io.github.some_example_name.upgrade;

import io.github.some_example_name.character.Player;

public class Heal implements Upgrade {
    @Override
    public void apply(Player player) {
        if(player.getHp() + 5 > player.getMaxHp()){
            player.setHp(player.getMaxHp());
        } else{
            player.setHp(player.getHp() + 5);
        }
    }

    @Override
    public String getDescription() {
        return "Heal 5 HP";
    }
}
