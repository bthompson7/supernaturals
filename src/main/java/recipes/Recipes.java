package recipes;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import supernaturals.Supernaturals;

public class Recipes {

	public static void register(Supernaturals sn) {
		
		// Wand
        ItemStack wandOfApprentice = new ItemStack(Material.BLAZE_ROD);
        ItemMeta meta = wandOfApprentice.getItemMeta();
        meta.setDisplayName(ChatColor.AQUA + "Mage Wand");
        wandOfApprentice.setItemMeta(meta);
        NamespacedKey mageWandKey = new NamespacedKey(sn, "mage_wand");
        ShapedRecipe wandRecipe = new ShapedRecipe(mageWandKey, wandOfApprentice);
        wandRecipe.shape(" R ", " S ", " S ");
        wandRecipe.setIngredient('R', Material.REDSTONE);
        wandRecipe.setIngredient('S', Material.STICK);
        Bukkit.addRecipe(wandRecipe);	
        
        // Spell Chest
        ItemStack spellChest = new ItemStack(Material.CHEST);
        ItemMeta spellChestMeta = spellChest.getItemMeta();
        meta.setDisplayName(ChatColor.AQUA + "Spell Chest");
        NamespacedKey spellChestKey = new NamespacedKey(sn, "spell_chest");
        ShapedRecipe spellChestRecipe = new ShapedRecipe(mageWandKey, wandOfApprentice);
        spellChestRecipe.shape(" W ", " W ", " W ");
        spellChestRecipe.shape(" W ", " R ", " W ");
        spellChestRecipe.shape(" W ", " W ", " W ");

        spellChestRecipe.setIngredient('W', Material.OAK_WOOD);
        spellChestRecipe.setIngredient('W', Material.BIRCH_WOOD);
        spellChestRecipe.setIngredient('W', Material.JUNGLE_WOOD);
        spellChestRecipe.setIngredient('W', Material.DARK_OAK_WOOD);
        spellChestRecipe.setIngredient('W', Material.CHERRY_WOOD);

        spellChestRecipe.setIngredient('R', Material.REDSTONE);
        Bukkit.addRecipe(spellChestRecipe);	


	}
}
