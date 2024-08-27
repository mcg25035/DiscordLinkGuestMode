package dev.mcloudtw.dcgm;

import github.scarsz.discordsrv.api.Subscribe;
import github.scarsz.discordsrv.api.events.AccountLinkedEvent;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffectType;

public class DiscordEvents {
    @Subscribe
    public void AccountLinkedEvent(AccountLinkedEvent event) {
        Bukkit.getScheduler().runTask(Main.plugin, () -> {
            Player player = ((Player) event.getPlayer());
            player.removePotionEffect(PotionEffectType.BLINDNESS);
            player.setGameMode(GameMode.SURVIVAL);
            player.performCommand("/spawn");
        });
    }
}
