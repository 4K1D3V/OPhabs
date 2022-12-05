package htt.ophabs;

import castSystem.dropCaster;
import fruitSystem.fruitAssociation;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import commands.oph;
import habilities.*;

import java.io.File;

public final class OPhabs extends JavaPlugin {

    public String configPath;

    @Override
    public void onEnable() {
        // Plugin startup logic
        registerCommands();



        getServer().getPluginManager().registerEvents(new yami_yami(this), this);
        getServer().getPluginManager().registerEvents(new mera_mera(this), this);
        getServer().getPluginManager().registerEvents(new gura_gura(),this);
        getServer().getPluginManager().registerEvents(new moku_moku(this),this);
        getServer().getPluginManager().registerEvents(new fruitAssociation(), this);
        getServer().getPluginManager().registerEvents(new dropCaster(), this);


        //---------------
        //files

        getConfig().options().copyDefaults();
        saveDefaultConfig();


        //--------------


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
