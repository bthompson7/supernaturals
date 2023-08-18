package tasks

import org.bukkit.ChatColor
import player.SNPlayer
class ManaRegenTask extends Runnable {
  override def run(): Unit = {
    SNPlayer.getOnlinePlayers.forEach(p => regenMana(p: SNPlayer))
  }
  private def regenMana(p: SNPlayer): Unit = {
    val maxMana = p.getMaxMana
    val currentMana = p.getCurrentMana
    val diffFromMax = maxMana - currentMana
    val MANA_REGEN_AMOUNT = 5

    if (diffFromMax >= MANA_REGEN_AMOUNT) {
      p.setCurrentMana(p.getCurrentMana + MANA_REGEN_AMOUNT)
      p.getPlayer.sendMessage(ChatColor.GREEN + "+" + MANA_REGEN_AMOUNT + " Mana")
      p.updateUI()
      p.save(p.getUuid, p)
    }
    else if (diffFromMax > 0 && diffFromMax < MANA_REGEN_AMOUNT) {
      p.setCurrentMana(p.getCurrentMana + diffFromMax)
      p.getPlayer.sendMessage(ChatColor.GREEN + "+" + diffFromMax + " Mana")
      p.updateUI()
      p.save(p.getUuid, p)
    }
  }

  }
