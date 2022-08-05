package pl.connectpl.listener;

import lombok.RequiredArgsConstructor;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.PostLoginEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;
import pl.connectpl.NatsProxy;

@RequiredArgsConstructor
public class PostLoginListener implements Listener {

    private final NatsProxy plugin;

    @EventHandler
    private void onPostLogin(PostLoginEvent event) {
        ProxiedPlayer player = event.getPlayer();

        String joinMessage = "&bGracz &3" + player.getName() + " &bpolaczyl sie z serwerem!";
        plugin.getConnection().publish("example-join", joinMessage.getBytes());
    }

}