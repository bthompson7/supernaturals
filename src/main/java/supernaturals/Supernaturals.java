package supernaturals;

import java.util.HashMap;
import java.util.UUID;

import listeners.WorldListener;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import commands.PluginCommands;
import commands.base.BaseCommandExecutor;
import commands.base.CommandManager;
import listeners.PlayerListener;
import player.SNPlayer;
import tasks.ManaRegenTask;

/**
 * Entry point for supernaturals plugin
 */
public class Supernaturals extends JavaPlugin {
	public static Supernaturals plugin = null;
	public static HashMap<UUID, SNPlayer> players = new HashMap<UUID, SNPlayer>();; // replace with actual persistence

	@Override
	public void onEnable() {
		plugin = this;
		getLogger().info("Supernaturals has been enabled!");

		// Register commands:
		getCommand("supernaturals").setExecutor(new BaseCommandExecutor());
		CommandManager.register(PluginCommands.class);

		// Register listeners:
		Bukkit.getPluginManager().registerEvents(new PlayerListener(), this);
		Bukkit.getPluginManager().registerEvents(new WorldListener(), this);

		// Register tasks:
		Bukkit.getScheduler().runTaskTimer(this, new ManaRegenTask(), 200, 200); // Every 10 seconds
	}

	@Override
	public void onDisable() {
		getLogger().info("Supernaturals has been disabled!");
		Bukkit.getScheduler().cancelTasks(plugin);
	}

}

