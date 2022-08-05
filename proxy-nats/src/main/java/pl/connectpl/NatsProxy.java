package pl.connectpl;

import io.nats.client.Connection;
import io.nats.client.Nats;
import lombok.Getter;
import lombok.SneakyThrows;
import net.md_5.bungee.api.plugin.Plugin;
import pl.connectpl.command.ExampleCommand;
import pl.connectpl.listener.PlayerDisconnectListener;
import pl.connectpl.listener.PostLoginListener;

import java.io.IOException;
import java.util.Arrays;

public final class NatsProxy extends Plugin {

    @Getter
    private Connection connection;

    @Override
    public void onEnable() {
        initNatsServer();
        Arrays.asList(
                new PlayerDisconnectListener(this),
                new PostLoginListener(this)
        ).forEach(listener -> getProxy().getPluginManager().registerListener(this, listener));
        getProxy().getPluginManager().registerCommand(this, new ExampleCommand(this));
    }

    @SneakyThrows({IOException.class, InterruptedException.class})
    public void initNatsServer() {
        connection = Nats.connect("nats://localhost:4222");
    }

}