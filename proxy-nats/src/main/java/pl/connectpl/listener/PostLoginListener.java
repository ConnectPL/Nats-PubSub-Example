package pl.connectpl.listener;

import io.nats.client.Connection;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.PostLoginEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;
import pl.connectpl.NatsProxy;

public class PostLoginListener implements Listener {

    private final NatsProxy plugin;

    public PostLoginListener(NatsProxy plugin) {
        this.plugin = plugin;
        plugin.getProxy().getPluginManager().registerListener(plugin,this);
    }

    @EventHandler
    public void onPostLogin(PostLoginEvent event) {
        ProxiedPlayer player = event.getPlayer();
        Connection connection = plugin.getConnection();

        String joinMessage = "&bGracz &3" + player.getName() + " &bpolaczyl sie z serwerem!";
        connection.publish("example-join", joinMessage.getBytes());
    }

}
