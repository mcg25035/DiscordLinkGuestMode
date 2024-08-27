package dev.mcloudtw.dcgm;

import github.scarsz.discordsrv.DiscordSRV;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public final class Main extends JavaPlugin {
    public static Main plugin;

//    public static boolean isPlayerGuest(Player player) {
//        PersistentDataContainer pdc = player.getPersistentDataContainer();
//        return (pdc.getOrDefault(NamespacedKey.minecraft("guest_mode"), PersistentDataType.BOOLEAN, false));
//    }
//
//    public static void setPlayerGuest(Player player, boolean isGuest) {
//        PersistentDataContainer pdc = player.getPersistentDataContainer();
//        pdc.set(NamespacedKey.minecraft("guest_mode"), PersistentDataType.BOOLEAN, isGuest);
//    }

    @Override
    public void onEnable() {
        // Plugin startup logic
        plugin = this;
        getServer().getPluginManager().registerEvents(new BukkitEvents(), this);
        Bukkit.getScheduler().runTaskTimer(this, () -> {
            Bukkit.getOnlinePlayers().forEach(player -> {
                if (BukkitEvents.isPlayerLinked(player)) return;
                player.addPotionEffect(
                        new PotionEffect(
                                PotionEffectType.BLINDNESS, Integer.MAX_VALUE, 1
                        )
                );
                player.setGameMode(GameMode.SPECTATOR);
            });
        }, 0, 10);
        DiscordSRV.api.subscribe(new DiscordEvents());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
