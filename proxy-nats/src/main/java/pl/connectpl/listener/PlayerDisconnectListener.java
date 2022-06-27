package pl.connectpl.listener;

import io.nats.client.Connection;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.PlayerDisconnectEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;
import pl.connectpl.NatsProxy;

public class PlayerDisconnectListener implements Listener {

    private final NatsProxy plugin;

    public PlayerDisconnectListener(NatsProxy plugin) {
        this.plugin = plugin;
        plugin.getProxy().getPluginManager().registerListener(plugin,this);
    }

    @EventHandler
    public void onPlayerDisconnect(PlayerDisconnectEvent event) {
        ProxiedPlayer player = event.getPlayer();
        Connection connection = plugin.getConnection();

        String joinMessage = "&3Gracz &3" + player.getName() + " &bopuscil serwer!";
        connection.publish("example-quit", joinMessage.getBytes());
    }
}
