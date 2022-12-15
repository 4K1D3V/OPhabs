package skin.changer;

import org.bukkit.Bukkit;

import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.CommandExecutor;


public final class skinsChanger {
    public void changeSkin( Player player, String skinName ) {
        String name = player.getName();
        Bukkit.dispatchCommand( Bukkit.getConsoleSender(), "skin set " + name + " " + skinName );
    }

    public void resetSkin( Player player ) {
        Bukkit.dispatchCommand( Bukkit.getConsoleSender(), "skin clear " + player.getName() );
    }
}