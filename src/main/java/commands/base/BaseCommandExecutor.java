package commands.base;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang.ArrayUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginDescriptionFile;

import supernaturals.Permission;
import supernaturals.Supernaturals;


public class BaseCommandExecutor implements CommandExecutor, TabCompleter {
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		if(args.length == 0) {
			PluginDescriptionFile desc = Supernaturals.plugin.getDescription();
			
			sender.sendMessage(CommandManager.getExtra() + "__________________.[ " + CommandManager.getHighlight() + Supernaturals.plugin.getName() + CommandManager.getExtra() + " ].__________________");
			sender.sendMessage(CommandManager.getDark() + "Description: " + CommandManager.getLight() + desc.getDescription());
			sender.sendMessage(CommandManager.getDark() + "Author: " + CommandManager.getLight() + desc.getAuthors().get(0));
			sender.sendMessage(CommandManager.getDark() + "Version: " + CommandManager.getLight() + desc.getVersion());
			sender.sendMessage(CommandManager.getDark() + "Website: " + CommandManager.getLight() + desc.getWebsite());
			sender.sendMessage(CommandManager.getExtra() + "---------------------------------------------------");
			return true;
		}
	
		Supernaturals.plugin.getLogger().info(args[0]);
		if(CommandManager.getCommand(args[0]) == null) {
			sender.sendMessage(CommandManager.getError() + "The specified command was not found!");
			return true;
		}
		
		BaseCommand command = CommandManager.getCommand(args[0]);
		Object[] commandArgs = ArrayUtils.remove(args, 0);

		if(sender instanceof Player && !(command.player())) {
			sender.sendMessage(CommandManager.getError() + "This command cannot be ran as a player!");
			return true;
		}
		
		if(sender instanceof ConsoleCommandSender && !(command.console())) {
			sender.sendMessage(CommandManager.getError() + "This command cannot be ran from the console!");
			return true;
		}
		
		if(command.permission() != null && !(command.permission().equals(Permission.NONE)) && !(Permission.has(command.permission(), sender))) {
			sender.sendMessage(CommandManager.getError() + "You do not have permission for this command!");
			return true;
		}
		
		if((commandArgs.length < command.min()) || (commandArgs.length > command.max() && command.max() != -1)) {
			sender.sendMessage(CommandManager.getError() + "Usage: /" + commandLabel + " " + command.aliases()[0] + " " + command.usage());
			return true;
		}
		
		CommandManager.execute(command, sender, cmd, commandLabel, commandArgs);                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                        
		return true;
	}
	
	public List<String> onTabComplete(CommandSender sender, Command cmd, String commandLabel, String[] args) {	
		ArrayList<String> list = new ArrayList<String>();
		
		for(BaseCommand command : CommandManager.getCommands()) {
			for(String alias : command.aliases()) {
				list.add(alias);
			}
		}
		
		return list;		
	}
	
}
