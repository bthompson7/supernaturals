package supernaturals;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import commands.PluginCommands;
import commands.base.BaseCommandExecutor;
import commands.base.CommandManager;
import listeners.PlayerListener;
import recipes.Recipes;

/**
 * Entry point for supernaturals plugin
 */
public class Supernaturals extends JavaPlugin {
	public static Supernaturals plugin = null;

	@Override
	public void onEnable() {
		plugin = this;
		getLogger().info("Supernaturals has been enabled!");
		
		// Register recipes
		Recipes.register(plugin);
		getLogger().info("Registered recipes");

		// Register Commands:
		getCommand("supernaturals").setExecutor(new BaseCommandExecutor());
		CommandManager.register(PluginCommands.class);

		// Register listeners
		Bukkit.getPluginManager().registerEvents(new PlayerListener(), this);


		
	}

	@Override
	public void onDisable() {
		getLogger().info("Supernaturals has been disabled!");
	}

}

