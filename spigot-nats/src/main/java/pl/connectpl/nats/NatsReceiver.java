package pl.connectpl.nats;

import io.nats.client.Connection;
import io.nats.client.Dispatcher;
import io.nats.client.Subscription;
import lombok.RequiredArgsConstructor;
import net.md_5.bungee.api.ChatColor;
import pl.connectpl.NatsSpigot;

@RequiredArgsConstructor
public final class NatsReceiver {

    private final NatsSpigot plugin;
    private final Connection connection;

    private static String color(String message) {
        return ChatColor.translateAlternateColorCodes('&', message);
    }

    public void handle() {
        Dispatcher dispatcher = connection.createDispatcher((msg) -> {
        });

        Subscription subJoin = dispatcher.subscribe("example-join", (msg) -> {
            String subBroadcast = new String(msg.getData());
            plugin.getServer().broadcastMessage(color(subBroadcast));
        });
        dispatcher.unsubscribe(subJoin, 100);

        Subscription subQuit = dispatcher.subscribe("example-quit", (msg) -> {
            String subBroadcast = new String(msg.getData());
            plugin.getServer().broadcastMessage(color(subBroadcast));
        });
        dispatcher.unsubscribe(subQuit, 100);

        Subscription subCommand = dispatcher.subscribe("example-command", (msg) -> {
            String subBroadcast = new String(msg.getData());
            plugin.getServer().broadcastMessage(color(subBroadcast));
        });
        dispatcher.unsubscribe(subCommand, 100);
    }

}