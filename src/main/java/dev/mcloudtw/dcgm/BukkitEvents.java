package dev.mcloudtw.dcgm;

import github.scarsz.discordsrv.DiscordSRV;
import io.papermc.paper.event.player.AsyncChatEvent;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;

public class BukkitEvents implements Listener {
    public static boolean isPlayerLinked(Player player) {
        return DiscordSRV.getPlugin().getAccountLinkManager().getDiscordId(player.getUniqueId()) != null;
    }

    @EventHandler
    public void PlayerJoinEvent(PlayerJoinEvent event) {
        if (isPlayerLinked(event.getPlayer())) return;
        event.getPlayer().performCommand("/discordmenu");
    }

    @EventHandler
    public void PlayerMoveEvent(PlayerMoveEvent event) {
        if (isPlayerLinked(event.getPlayer())) return;
        event.setCancelled(true);

    }

    @EventHandler
    public void InventoryCloseEvent(InventoryCloseEvent event) {
        if (isPlayerLinked((Player) event.getPlayer())) return;
        ((Player) event.getPlayer()).performCommand("/discordmenu");
    }


    @EventHandler
    public void PlayerCommandPreprocessEvent(PlayerCommandPreprocessEvent event) {
        if (isPlayerLinked(event.getPlayer())) return;
        if (event.getMessage().startsWith("/discord")) return;
        event.setCancelled(true);
        event.getPlayer().sendMessage(MiniMessage.miniMessage().deserialize(
                "<gray>[</gray><aqua>Discord連結</aqua><gray>]</gray> " +
                "<red>你尚未綁定 Discord 帳號，請先綁定 Discord 帳號後再使用指令。"
        ));
    }

    @EventHandler
    public void AsyncChatEvent(AsyncChatEvent event) {
        if (isPlayerLinked(event.getPlayer())) return;
        event.setCancelled(true);

        event.getPlayer().sendMessage(MiniMessage.miniMessage().deserialize(
                "<gray>[</gray><aqua>Discord連結</aqua><gray>]</gray> " +
                "<red>你尚未綁定 Discord 帳號，請先綁定 Discord 帳號後再聊天。"
        ));
    }



}
