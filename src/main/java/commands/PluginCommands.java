package commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import commands.base.BaseCommand;
import commands.base.CommandManager;
import supernaturals.Permission;

public class PluginCommands {

	@BaseCommand(aliases = { "test" }, desc = "Test command", permission = Permission.NONE)
	public void onTestCommand(CommandSender  sender, Command cmd, String commandLabel, String[] args) {
		sender.sendMessage(CommandManager.getLight() + "Welcome to Supernaturals!");
	}
	
	@BaseCommand(aliases = { "evolve" }, desc = "Evolve into a mage!", permission = Permission.COMMAND_EVOLVE)
	public void onEvolveCommand(CommandSender  sender, Command cmd, String commandLabel, String[] args) {
		
	}
	
}
