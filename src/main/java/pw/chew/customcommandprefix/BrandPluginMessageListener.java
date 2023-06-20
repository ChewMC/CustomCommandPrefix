package pw.chew.customcommandprefix;

import org.bukkit.entity.Player;
import org.bukkit.plugin.messaging.PluginMessageListener;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;

/*
 * Uses PluginMessageListener to check if Geyser is being used, just in-case
 * server does not use Floodgate.
 * 
 * Credit for this class and implementation: https://github.com/Heath123/debuginfo-be/blob/main/src/main/java/com/gmail/heathmitchell27/debuginfobe/BrandPluginMessageListener.java
 */
public class BrandPluginMessageListener implements PluginMessageListener {
	static HashMap<Player, String> playerBrands = new HashMap<>();

	@Override
	public void onPluginMessageReceived(String channel, Player p, byte[] msg) {
		try {
			playerBrands.put(p, new String(msg, "UTF-8").substring(1));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}
}