package pw.chew.customcommandprefix;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.messaging.Messenger;

public final class CustomCommandPrefix extends JavaPlugin {
    @Override
    public void onEnable() {
        // Plugin startup logic
        FileConfiguration config = getConfig();
        config.addDefault("prefix", "/");
        config.addDefault("replace_slash_prefix", false);
        config.options().copyDefaults(true);
        saveDefaultConfig();
        
        Messenger messenger = Bukkit.getMessenger();
        messenger.registerIncomingPluginChannel(this, "minecraft:brand", new BrandPluginMessageListener());
        
        // Register Events
        getServer().getPluginManager().registerEvents(new ChatListener(this), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
