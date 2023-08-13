package tasks;

import org.bukkit.ChatColor;

import player.SNPlayer;
import supernaturals.Supernaturals;

public class ManaRegenTask implements Runnable {
	
	
	private static int MANA_REGEN_AMOUNT = 10;
	public void run() {
		for(SNPlayer p : SNPlayer.getOnlinePlayers()) {
			int maxMana = p.getMaxMana();
			int currentMana = p.getCurrentMana();
			int diffFromMax = maxMana - currentMana;
			
			if(diffFromMax >= MANA_REGEN_AMOUNT) {
				p.setCurrentMana(p.getCurrentMana() + MANA_REGEN_AMOUNT);
				p.getPlayer().sendMessage(ChatColor.GREEN + "+" + MANA_REGEN_AMOUNT + " Mana");
				p.updateUI();
				p.save(p.getUuid(), p);		
			}else if(diffFromMax < MANA_REGEN_AMOUNT && diffFromMax > 0) {
				p.setCurrentMana(p.getCurrentMana() + diffFromMax);
				p.getPlayer().sendMessage(ChatColor.GREEN + "+" + diffFromMax + " Mana");
				p.updateUI();
				p.save(p.getUuid(), p);	
			}
		
		}
	}

}
