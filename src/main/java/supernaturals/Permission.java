package supernaturals;

import org.bukkit.command.CommandSender;


public enum Permission {
	COMMAND_TUTORIAL("commands.tutorial"),
	COMMAND_MANA("commands.mana"),
	COMMAND_LEVEL("commands.level"),
	COMMAND_EVOLVE("commands.evolve"),
	COMMAND_NEW_ITEMS("command.newitems"),

	// mod commands
	COMMAND_MODMANA("commands.modmana"),
	COMMAND_MODEXP("commands.modexp"),
	COMMAND_MODCLASS("commands.modclass"),
	COMMAND_MODRESET("commands.modreset"),
	NONE("");
	
	private String node;
	
	private Permission(String node) {
		this.node = node;
	}
	
	public String getNode() {
		return node;
	}
	
	private static String getPermission(Permission permission) {
		return "supernaturals." + permission.getNode();
	}
	
	public static Boolean has(Permission permission, CommandSender target) {
		return target.hasPermission(getPermission(permission));
	}
}
