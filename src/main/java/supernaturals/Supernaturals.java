package supernaturals;

import org.bukkit.plugin.java.JavaPlugin;

import commands.base.BaseCommandExecutor;
import commands.base.CommandManager;
import common.PluginCommands;
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
		
		// register actual coma
		CommandManager.register(PluginCommands.class);


		
	}

	@Override
	public void onDisable() {
		getLogger().info("Supernaturals has been disabled!");
	}

}

