package pl.connectpl.listener;

import lombok.RequiredArgsConstructor;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.PlayerDisconnectEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;
import pl.connectpl.NatsProxy;

@RequiredArgsConstructor
public final class PlayerDisconnectListener implements Listener {

    private final NatsProxy plugin;

    @EventHandler
    private void onPlayerDisconnect(PlayerDisconnectEvent event) {
        ProxiedPlayer player = event.getPlayer();

        String joinMessage = "&3Gracz &3" + player.getName() + " &bopuscil serwer!";
        plugin.getConnection().publish("example-quit", joinMessage.getBytes());
    }

}
