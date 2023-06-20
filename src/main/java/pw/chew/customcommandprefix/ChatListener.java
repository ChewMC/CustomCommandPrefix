package pw.chew.customcommandprefix;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.geysermc.floodgate.api.FloodgateApi;

public class ChatListener implements Listener {
	private final CustomCommandPrefix plugin;

	public ChatListener(CustomCommandPrefix plugin) {
		this.plugin = plugin;
	}

	@EventHandler
	public void onCommand(AsyncPlayerChatEvent event) {
		Player player = event.getPlayer();
		String message = event.getMessage();
		
		//Check if player is not Geyser or Not Floodgate. Example, if player is Geyser and not Floodgate, it will not enter the if statement.
		//In Conclusion, only java players will be returned and commandprefix only applies to Bedrock Players.
		if ((BrandPluginMessageListener.playerBrands.get(player) == null || !BrandPluginMessageListener.playerBrands.get(player).equals("Geyser"))
				&& (!FloodgateApi.getInstance().isFloodgatePlayer(player.getUniqueId()))) {
			return;
		}
		
		String prefix = plugin.getConfig().getString("prefix");
		if (message.startsWith(prefix)) {
			event.setCancelled(true);
			// logic for if prefix is set to be empty in the config, so we
			// permit command debugging here.
			if (prefix.isEmpty()) {
				Bukkit.getScheduler().runTask(plugin, () -> player.performCommand(message));
				event.setCancelled(false);
				return;
			}
			Bukkit.getScheduler().runTask(plugin, () -> player.performCommand(message.replace(prefix, "")));
		}
	}
}