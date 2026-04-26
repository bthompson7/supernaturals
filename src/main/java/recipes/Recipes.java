package recipes;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import supernaturals.Supernaturals;

/**
 * Custom items used by players
 */
public class Recipes {

	/**
	 * Used to register custom craftable items
	 * 
	 * @param sn
	 */
	public static void register(Supernaturals sn) {
		
	}
	
	/**
	 * Creates the Magic Wand
	 * 
	 * @return the wand
	 */
	public static ItemStack createWand() {
        ItemStack magicWand = new ItemStack(Material.BLAZE_ROD);
        ItemMeta meta = magicWand.getItemMeta();

		if(meta == null){
			throw new RuntimeException("ItemMeta is null.");
		}

        meta.setDisplayName(ChatColor.AQUA + "Magic Wand");
        magicWand.setItemMeta(meta);
        return magicWand;
	}
	
	/**
	 * Creates the spell book
	 * 
	 * @return the spell book
	 */
	public static ItemStack createSpellBook() {
        ItemStack spellBook = new ItemStack(Material.BOOK);
        ItemMeta spellBookMeta = spellBook.getItemMeta();

		if(spellBookMeta == null){
			throw new RuntimeException("ItemMeta is null.");
		}

		spellBookMeta.setDisplayName(ChatColor.LIGHT_PURPLE + "Spell Book");
        spellBook.setItemMeta(spellBookMeta);
        return spellBook;
	}
}
