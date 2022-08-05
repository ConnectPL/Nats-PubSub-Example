package pl.connectpl;

import io.nats.client.Connection;
import io.nats.client.Nats;
import lombok.Getter;
import lombok.SneakyThrows;
import org.bukkit.plugin.java.JavaPlugin;
import pl.connectpl.nats.NatsReceiver;

import java.io.IOException;

public final class NatsSpigot extends JavaPlugin {

    @Getter
    private Connection connection;

    @Override
    public void onEnable() {
        initNatsServer();
    }

    @SneakyThrows({IOException.class, InterruptedException.class})
    private void initNatsServer() {
        this.connection = Nats.connect("nats://localhost:4222");
        new NatsReceiver(this, connection).handle();
    }

}