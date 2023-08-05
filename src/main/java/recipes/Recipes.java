package recipes;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;

import supernaturals.Supernaturals;

public class Recipes {

	public static void register(Supernaturals sn) {
        ItemStack wandOfApprentice = new ItemStack(Material.BLAZE_ROD);
        ItemMeta meta = wandOfApprentice.getItemMeta();
        meta.setDisplayName(ChatColor.AQUA + "Mage Wand");
        wandOfApprentice.setItemMeta(meta);
        NamespacedKey recipeKey = new NamespacedKey(sn, "mage_wand");
        ShapedRecipe customRecipe = new ShapedRecipe(recipeKey, wandOfApprentice);
        customRecipe.shape(" R ", " S ", " S ");
        customRecipe.setIngredient('R', Material.REDSTONE);
        customRecipe.setIngredient('S', Material.STICK);
        Bukkit.addRecipe(customRecipe);	


	}
}
