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
		
		// Wand of Apprentice
		// Define the custom item you want to craft
        ItemStack wandOfApprentice = new ItemStack(Material.BLAZE_ROD);
        ItemMeta meta = wandOfApprentice.getItemMeta();
        
        // Set display name
        meta.setDisplayName(ChatColor.AQUA + "Wand of Apprentice");
        wandOfApprentice.setItemMeta(meta);
        
        // Create the recipe with a NamespacedKey (must be unique)
        NamespacedKey recipeKey = new NamespacedKey(sn, "wand_of_apprentice");
        ShapedRecipe customRecipe = new ShapedRecipe(recipeKey, wandOfApprentice);
        customRecipe.shape(" R ", " S ", " S ");
        // Define the ingredients for the recipe
        customRecipe.setIngredient('R', Material.REDSTONE);
        customRecipe.setIngredient('S', Material.STICK);
        Bukkit.addRecipe(customRecipe);	
        
    	// Wand of Adept 


	}
}
