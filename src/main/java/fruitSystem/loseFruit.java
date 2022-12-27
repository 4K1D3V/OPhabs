package fruitSystem;

import htt.ophabs.OPhabs;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import scoreboardSystem.abilitiesScoreboard;

import java.util.Map;

public class loseFruit implements Listener {
    private final OPhabs plugin;
    public Map<String, devilFruitUser> dfPlayers;
    public abilitiesScoreboard scoreboard = null;

    public loseFruit(OPhabs plugin, Map<String, devilFruitUser> dfPlayers) {
        this.plugin = plugin;
        this.dfPlayers = dfPlayers;
    }
    @EventHandler(ignoreCancelled = true)
    public void onPlayerDeath(PlayerDeathEvent event) {
        Player player = event.getEntity();
        String
                yamiValue = plugin.getConfig().getString("FruitAssociations.yami_yami"),
                meraValue = plugin.getConfig().getString("FruitAssociations.mera_mera"),
                guraValue = plugin.getConfig().getString("FruitAssociations.gura_gura"),
                mokuValue = plugin.getConfig().getString("FruitAssociations.moku_moku"),
                nekoReoparudoValue = plugin.getConfig().getString("FruitAssociations.neko_neko_reoparudo"),
                maguValue = plugin.getConfig().getString("FruitAssociations.magu_magu");;

        if(fruitAssociation.dfPlayers.containsKey(player.getName())){
            fruitAssociation.dfPlayers.get(player.getName()).onPlayerDeath(event);
            fruitAssociation.dfPlayers.remove(player.getName());

            if(yamiValue.equals(event.getEntity().getName())) yamiValue = "none";
            if(meraValue.equals(event.getEntity().getName())) meraValue = "none";
            if(guraValue.equals(event.getEntity().getName())) guraValue = "none";
            if(mokuValue.equals(event.getEntity().getName())) mokuValue = "none";
            if(maguValue.equals(event.getEntity().getName())) maguValue = "none";
            if(nekoReoparudoValue.equals(event.getEntity().getName())) nekoReoparudoValue = "none";

            plugin.getConfig().set("FruitAssociations.yami_yami",yamiValue);
            plugin.getConfig().set("FruitAssociations.mera_mera",meraValue);
            plugin.getConfig().set("FruitAssociations.gura_gura",guraValue);
            plugin.getConfig().set("FruitAssociations.moku_moku",mokuValue);
            plugin.getConfig().set("FruitAssociations.neko_neko_reoparudo",nekoReoparudoValue);
            plugin.getConfig().set("FruitAssociations.magu_magu", maguValue);
            plugin.saveConfig();

            scoreboard.removeScoreboard(player);
        }	
    }

    public void setScoreboard(abilitiesScoreboard scoreboard){
        this.scoreboard = scoreboard;
    }
}
