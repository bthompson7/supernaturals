package commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import commands.base.BaseCommand;
import commands.base.CommandManager;
import player.SNPlayer;
import recipes.Recipes;
import supernaturals.Permission;
import supernaturals.Supernaturals;

public class PluginCommands {

	@BaseCommand(aliases = { "evolve" }, desc = "Evolve into a mage!", permission = Permission.COMMAND_EVOLVE)
	public void onEvolveCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		SNPlayer getPlayer = SNPlayer.getPlayer((Player) sender);
		Player commandSender = (Player) sender;

		if (getPlayer == null) {
			SNPlayer snPlayer = new SNPlayer(commandSender);
			ItemStack magicWand = Recipes.createWand();
			ItemStack spellBook = Recipes.createSpellBook();
			commandSender.getInventory().addItem(magicWand);
			commandSender.getInventory().addItem(spellBook);
			
			Supernaturals.players.put(snPlayer.getUuid(), snPlayer);
			commandSender.sendMessage(ChatColor.GREEN + "You are now a Mage!");
			Bukkit.getServer().broadcastMessage(ChatColor.YELLOW + commandSender.getName() + ChatColor.GREEN + " is now a Mage");
			snPlayer.updateUI();	
		} else {
			commandSender.sendMessage(ChatColor.RED + "You are already a mage!");

		}
	}

	@BaseCommand(aliases = { "newitems" }, desc = "Gives you new items", permission = Permission.COMMAND_NEW_ITEMS)
	public void onNewItemsCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		SNPlayer getPlayer = SNPlayer.getPlayer((Player) sender);
		Player commandSender = (Player) sender;
		if (getPlayer != null) {
			ItemStack magicWand = Recipes.createWand();
			ItemStack spellBook = Recipes.createSpellBook();
			commandSender.getInventory().addItem(magicWand);
			commandSender.getInventory().addItem(spellBook);
			commandSender.sendMessage(ChatColor.GREEN + "You have recieved a new wand and spellbook!");

		} else {
			commandSender.sendMessage(ChatColor.RED + "You must be a mage to run this command!");

		}
	}
	
}
