package pl.connectpl.command;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;
import pl.connectpl.NatsProxy;

public class ExampleCommand extends Command {

    private final NatsProxy plugin;

    public ExampleCommand(NatsProxy plugin) {
        super("nats");
        this.plugin = plugin;
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if (!(sender instanceof ProxiedPlayer)) {
            sender.sendMessage(new TextComponent("Komenda tylko dla graczy!"));
            return;
        }
        if (args.length <= 0) {
            sender.sendMessage(new TextComponent("Wpisz tresc wiadomosci!"));
            return;
        }

        ProxiedPlayer player = (ProxiedPlayer) sender;
        StringBuilder builder = new StringBuilder();

        for (String arg : args) {
            builder.append(arg).append(" ");
        }
        String string = builder.toString();
        String broadcastMessage = "&cOGLOSZENIE " + "&7" + string;

        plugin.getConnection().publish("example-command", broadcastMessage.getBytes());
        player.sendMessage(new TextComponent(color("&aPomyslnie wyslano wiadomosc!")));
    }

    private static String color(String message) {
        return ChatColor.translateAlternateColorCodes('&', message);
    }

}