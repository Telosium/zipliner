package com.telosium.zipliner;

import com.telosium.zipliner.events.ZiplinerEvents;
import com.telosium.zipliner.utils.ZiplinerUtils;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

public final class Zipliner extends JavaPlugin {

    private final Logger logger = Logger.getLogger("Zipliner");
    private static Zipliner plugin;

    @Override
    public void onEnable() {
        plugin = this;
        Bukkit.getPluginManager().registerEvents(new ZiplinerEvents(), this);
        ZiplinerUtils.registerPermissions();
        ZiplinerUtils.registerZiplineBowRecipe();
    }

    @Override
    public void onDisable() {
        ZiplinerUtils.unregisterZiplineBowRecipe();
        ZiplinerUtils.unregisterPermission();
    }

    @Override
    public Logger getLogger() {
        return logger;
    }

    public static Zipliner getInstance() {
        return plugin;
    }
}
