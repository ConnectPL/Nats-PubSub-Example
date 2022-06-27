package pl.connectpl.cmd;

import io.nats.client.Connection;
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
        plugin.getProxy().getPluginManager().registerCommand(plugin, this);
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if(!(sender instanceof ProxiedPlayer)) {
            sender.sendMessage(new TextComponent("Komenda tylko dla graczy!"));
            return;
        }
        if(args.length <= 0) {
            sender.sendMessage(new TextComponent("Wpisz tresc wiadomosci!"));
            return;
        }
        ProxiedPlayer player = (ProxiedPlayer) sender;
        Connection connection = plugin.getConnection();
        StringBuilder builder = new StringBuilder();

        for(int i=0; i<args.length; i++){
            builder.append(args[i]).append(" ");
        }
        String s = builder.toString();

        String bcmsg = "&cOGLOSZENIE " + "&7" + s;

        connection.publish("example-cmd", bcmsg.getBytes());
        player.sendMessage(new TextComponent(ChatColor.translateAlternateColorCodes('&',"&aPomyslnie wyslano wiadomosc!")));
    }

}
