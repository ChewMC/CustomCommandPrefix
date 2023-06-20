package pw.chew.customcommandprefix;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ChatListener implements Listener {
	private final CustomCommandPrefix plugin;

	public ChatListener(CustomCommandPrefix plugin) {
		this.plugin = plugin;
	}

	@EventHandler
	public void onCommand(AsyncPlayerChatEvent event) {
		String prefix = plugin.getConfig().getString("prefix");
		if (event.getMessage().startsWith(prefix)) {
			event.setCancelled(true);
			// logic for if prefix is set to be empty in the config, so we
			// permit command debugging here.
			if (prefix.isEmpty()) {
				Bukkit.getScheduler().runTask(plugin, () -> event.getPlayer().performCommand(event.getMessage()));
				event.setCancelled(false);
				return;
			}
			Bukkit.getScheduler().runTask(plugin, () -> event.getPlayer().performCommand(event.getMessage().replace(prefix, "")));
		}
	}
}