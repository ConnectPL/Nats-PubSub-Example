package pl.connectpl.nats;

import io.nats.client.Connection;
import io.nats.client.Dispatcher;
import io.nats.client.Subscription;
import net.md_5.bungee.api.ChatColor;
import pl.connectpl.NatsSpigot;

public class NatsReceiver {

    private final NatsSpigot plugin;
    private final Connection connection;

    public NatsReceiver(NatsSpigot plugin, Connection connection) {
        this.plugin = plugin;
        this.connection = connection;
        handle();
    }

    public void handle() {
        Dispatcher d = connection.createDispatcher((msg) -> {});

        Subscription subjoin = d.subscribe("example-join", (msg) -> {
            String subbroadcast = new String(msg.getData());
            plugin.getServer().broadcastMessage(color(subbroadcast));
        });
        d.unsubscribe(subjoin, 100);

        Subscription subquit = d.subscribe("example-quit", (msg) -> {
            String subbroadcast = new String(msg.getData());
            plugin.getServer().broadcastMessage(color(subbroadcast));
        });
        d.unsubscribe(subquit, 100);

        Subscription subcmd = d.subscribe("example-cmd", (msg) -> {
            String subbroadcast = new String(msg.getData());
            plugin.getServer().broadcastMessage(color(subbroadcast));
        });
        d.unsubscribe(subcmd, 100);
    }


    public static String color(String m) {
        return ChatColor.translateAlternateColorCodes('&', m);
    }


}
