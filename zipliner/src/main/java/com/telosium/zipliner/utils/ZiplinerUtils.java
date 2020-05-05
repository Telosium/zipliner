package com.telosium.zipliner.utils;

import com.telosium.zipliner.Zipliner;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.permissions.Permission;
import org.bukkit.permissions.PermissionDefault;

import java.util.ArrayList;

public class ZiplinerUtils {
    public static final NamespacedKey ZIPLINEBOW_KEY = new NamespacedKey(Zipliner.getInstance(), "ziplinebow");
    public static final String ZIPLINEBOW_USE_PERM = "ziplinebow.use";

    private ZiplinerUtils() {}

    public static ItemStack getZiplineBow() {
        ItemStack bow = new ItemStack(Material.BOW);
        ItemMeta bowMeta = bow.getItemMeta();

        bowMeta.setDisplayName(ChatColor.ITALIC + "" + ChatColor.AQUA + "Zipline bow");

        bow.setItemMeta(bowMeta);

        return bow;
    }

    public static String getZiplineBowName() {
        return getZiplineBow().getItemMeta().getDisplayName();
    }

    public static boolean isZiplineBow(ItemStack item) {
        if(null == item || item.getType() != Material.BOW || !item.hasItemMeta() || !item.getItemMeta().hasDisplayName()) {
            return false;
        } else {
            return (item.getItemMeta().getDisplayName().equals(getZiplineBowName()));
        }
    }

    public static void registerZiplineBowRecipe() {
        ItemStack ziplineBow = getZiplineBow();

        ShapedRecipe ziplineBowRecipe = new ShapedRecipe(ZIPLINEBOW_KEY, ziplineBow);

        ziplineBowRecipe.shape(" SA",
                               " S ",
                               "BS ");

        ziplineBowRecipe.setIngredient('S', Material.STRING);
        ziplineBowRecipe.setIngredient('A', Material.ARROW);
        ziplineBowRecipe.setIngredient('B', Material.BOW);

        boolean res = Bukkit.addRecipe(ziplineBowRecipe);

        if (res) {
            Zipliner.getInstance().getLogger().fine("Registered recipe for Zipline bow");
        } else {
            Zipliner.getInstance().getLogger().fine("Failed to register recipe for Zipline bow");
        }

    }

    public static void unregisterZiplineBowRecipe() {
        boolean res = Bukkit.removeRecipe(ZIPLINEBOW_KEY);
        if (res) {
            Zipliner.getInstance().getLogger().fine("Removed recipe for zipline bow");
        } else {
            Zipliner.getInstance().getLogger().fine("Failed to remove recipe for Zipline bow");
        }
    }

    public static void registerPermissions() {
        Bukkit.getPluginManager().addPermission( new Permission(ZIPLINEBOW_USE_PERM,
                "Allows player to use zipline bow", PermissionDefault.TRUE));
        Zipliner.getInstance().getLogger().fine("Register use permission for Zipline bow");
    }

    public static void unregisterPermission() {
        Bukkit.getPluginManager().removePermission(ZIPLINEBOW_USE_PERM);
        Zipliner.getInstance().getLogger().fine("Removed use permission for Zipline bow");
    }
}
