package castSystem;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;


/**
* @brief Class that avoids to drop a caster.
* @author RedRiotTank.
*/
public class noDropCaster implements Listener {

    /**
     * @brief Avoids to drop a caster at death.
     * @param event PlayerDeathEvent.
     * @author RedRiotTank.
     */
    @EventHandler(ignoreCancelled = true)
    public void onPlayerDeath(PlayerDeathEvent event) {
        event.getEntity().setScoreboard(Bukkit.getScoreboardManager().getNewScoreboard());

        for(ItemStack drop : event.getDrops())
            if(castIdentification.itemIsCaster(drop,event.getEntity()))
                    drop.setAmount(0);
    }
}
