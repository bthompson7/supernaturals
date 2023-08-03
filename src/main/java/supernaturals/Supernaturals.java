package supernaturals;

import org.bukkit.plugin.java.JavaPlugin;

import recipes.Recipes;

/**
 * Entry point for supernaturals plugin
 */
public class Supernaturals extends JavaPlugin {


	@Override
	public void onEnable() {
		getLogger().info("Supernaturals has been enabled!");
		
		// Register recipes
		Recipes.register(getPlugin());
		getLogger().info("Registered recipes");
		// register commands
		//getLogger().info("Registered commands");

		// register events
		//egetLogger().info("Registered events");

		
	}

	@Override
	public void onDisable() {
		getLogger().info("Supernaturals has been disabled!");
	}

	
	/**
	 * @return the plugin
	 */
	public Supernaturals getPlugin() {
		return Supernaturals.this;
	}
}
