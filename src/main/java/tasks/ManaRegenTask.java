package tasks;

import player.SNPlayer;
import supernaturals.Supernaturals;

public class ManaRegenTask implements Runnable {
	
	public void run() {
		for(SNPlayer p : SNPlayer.getOnlinePlayers()) {
			p.setMana(p.getMana() + 10);
			p.getPlayer().sendMessage("You have " + p.getCurrentMana() + " Mana");
			p.save(p.getUuid(), p);
		}
	}

}
