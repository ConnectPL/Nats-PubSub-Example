package pl.connectpl;

import io.nats.client.Connection;
import io.nats.client.Nats;
import lombok.Getter;
import net.md_5.bungee.api.plugin.Plugin;
import pl.connectpl.cmd.ExampleCommand;
import pl.connectpl.listener.PlayerDisconnectListener;
import pl.connectpl.listener.PostLoginListener;

import java.io.IOException;

public class NatsProxy extends Plugin {

    @Getter
    public Connection connection;

    @Override
    public void onEnable() {
        initNatsServer();
        new PostLoginListener(this);
        new PlayerDisconnectListener(this);
        new ExampleCommand(this);

    }

    public void initNatsServer() {
        try {
            connection = Nats.connect("nats://localhost:4222");
        } catch (IOException | InterruptedException exception) {
            exception.printStackTrace();
        }
    }
}
