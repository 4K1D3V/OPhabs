package htt.ophabs;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import commands.oph;

import java.io.File;

public final class OPhabs extends JavaPlugin {

    public String configPath;

    @Override
    public void onEnable() {
        // Plugin startup logic
        registerCommands();
        //registerConfig();
        Bukkit.getConsoleSender().sendMessage("OPhabs started correctly");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        Bukkit.getConsoleSender().sendMessage("HTTrolplay closed correctly");
    }

    public void registerCommands(){
        this.getCommand("oph").setExecutor(new oph(this));
    }

    public void registerConfig(){
        File config = new File(this.getDataFolder(), "config.yml");
        configPath = config.getPath();

        if(!config.exists()){
            this.getConfig().options().copyDefaults(true);
            saveConfig();
        }

    }
}
