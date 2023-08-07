package commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import commands.base.BaseCommand;
import commands.base.CommandManager;
import player.SNPlayer;
import supernaturals.Permission;
import supernaturals.Supernaturals;

public class PluginCommands {

	@BaseCommand(aliases = { "test" }, desc = "Test command", permission = Permission.NONE)
	public void onTestCommand(CommandSender  sender, Command cmd, String commandLabel, String[] args) {
		sender.sendMessage(CommandManager.getLight() + "Welcome to Supernaturals!");
	}
	
	@BaseCommand(aliases = { "evolve" }, desc = "Evolve into a mage!", permission = Permission.COMMAND_EVOLVE)
	public void onEvolveCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		SNPlayer player = SNPlayer.getPlayer((Player) sender);
		Player p = (Player) sender;

		if (player == null) {
			SNPlayer snPlayer = new SNPlayer(p);
			Supernaturals.players.put(snPlayer.getUuid(), snPlayer);
			p.sendMessage(ChatColor.GREEN + "You are now a Mage!");
			Bukkit.getServer().broadcastMessage(ChatColor.YELLOW + p.getName() + ChatColor.GREEN + " is now a Mage");
			snPlayer.updateUI();
		} else {
			p.sendMessage(ChatColor.RED + "You are already a mage!");

		}

	}

}
