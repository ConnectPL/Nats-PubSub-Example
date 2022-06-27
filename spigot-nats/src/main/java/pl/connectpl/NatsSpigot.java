package pl.connectpl;

import io.nats.client.Connection;
import io.nats.client.Nats;
import lombok.Getter;
import org.bukkit.plugin.java.JavaPlugin;
import pl.connectpl.listeners.PlayerJoinQuitListener;
import pl.connectpl.nats.NatsReceiver;

import java.io.IOException;

public class NatsSpigot extends JavaPlugin {

    @Getter
    public Connection connection;

    @Override
    public void onEnable() {
        initNatsServer();
        new PlayerJoinQuitListener(this);
        new NatsReceiver(this, connection);
    }

    public void initNatsServer() {
        try {
            connection = Nats.connect("nats://localhost:4222");
        } catch (IOException | InterruptedException exception) {
            exception.printStackTrace();
        }
    }
}
