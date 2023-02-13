package castSystem;


import htt.ophabs.OPhabs;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.*;
import org.bukkit.event.player.*;
import abilitieSystem.*;
import org.bukkit.potion.PotionEffectType;
import skin.skinsChanger;

import org.bukkit.event.inventory.InventoryClickEvent;


import java.util.HashMap;
import java.util.Map;


public class caster implements Listener {
    private Map<String, abilityUser> users = new HashMap<>();
    private OPhabs plugin;

    public caster(coolDown cooldown, Map<String, abilityUser> users, OPhabs plugin){
        this.users = users;
        this.plugin = plugin;
    }

    @EventHandler
    public void onInteract(PlayerInteractEvent event){
        if(users.containsKey(event.getPlayer().getName())) {
            abilityUser user = users.get(event.getPlayer().getName());
            Action action = event.getAction();
            if (event.getItem() != null && castIdentification.itemIsCaster(event.getItem(), event.getPlayer()) && user.hasFruit()) {

                String casterItemName = event.getItem().getItemMeta().getDisplayName();
                Material casterMaterial = event.getMaterial();

                if ((action == Action.RIGHT_CLICK_AIR || action == Action.RIGHT_CLICK_BLOCK))
                    user.abilityActive();


                //else
                //user.switchAbility();

            }else{
                user.onPlayerInteract(event);
            }
        }
    }

    @EventHandler(ignoreCancelled = true)
    public void onPlayerDropItem(PlayerDropItemEvent event) {
        if(castIdentification.itemIsCaster(event.getItemDrop().getItemStack(), event.getPlayer())){
            event.setCancelled(true);

            if(users.containsKey(event.getPlayer().getName())) {
                abilityUser user = users.get(event.getPlayer().getName());
                user.switchAbility();
            }
        }

    }

    @EventHandler
    public void onEntityDamage(EntityDamageEvent event){
        if(users.containsKey(event.getEntity().getName())) {
            abilityUser user = users.get(event.getEntity().getName());
            user.onEntityDamage(event);
            user.onFall(event);
        }
    }
    @EventHandler
    public void onEntityDamageByEntityEvent(EntityDamageByEntityEvent event){
        if(event.getDamager() instanceof Player &&  users.containsKey(event.getDamager().getName())) {
            abilityUser user = users.get(event.getDamager().getName());
            user.onUserDamageAnotherEntity(event);
        }
    }
    @EventHandler
    public void onPlayerToggleSneak(PlayerToggleSneakEvent e){
        if(users.containsKey(e.getPlayer().getName())) {
            abilityUser user = users.get(e.getPlayer().getName());
            user.onPlayerToggleSneak(e);
        }
    }

    @EventHandler
    public void onPlayerToggleSprint(PlayerToggleSprintEvent e){
        if(users.containsKey(e.getPlayer().getName())) {
            abilityUser user = users.get(e.getPlayer().getName());
            user.onPlayerToggleSprint(e);
        }
    }

    @EventHandler
    public void onPlayerToggleFlight(PlayerToggleFlightEvent e){
        if(users.containsKey(e.getPlayer().getName())) {
            abilityUser user = users.get(e.getPlayer().getName());
            user.onPlayerToggleFlight(e);
        }
    }

    @EventHandler
    public void onPlayerItemConsume(PlayerItemConsumeEvent event){
        if(users.containsKey(event.getPlayer().getName())) {
            abilityUser user = users.get(event.getPlayer().getName());
            user.onPlayerItemConsume(event);
        }
    }
    @EventHandler
    public void onEntityPickupItem(EntityPickupItemEvent event){
        if(users.containsKey(event.getEntity().getName())) {
            abilityUser user = users.get(event.getEntity().getName());
            user.onEntityPickupItem(event);
        }
    }
    @EventHandler
    public void onPlayerEggThrow(PlayerEggThrowEvent event){
        if(users.containsKey(event.getPlayer().getName())) {
            abilityUser user = users.get(event.getPlayer().getName());
            user.onPlayerEggThrow(event);
        }
    }
    @EventHandler
    public void onEntityShootBow(EntityShootBowEvent event){
        if(users.containsKey(event.getEntity().getName())) {
            abilityUser user = users.get(event.getEntity().getName());
            user.onEntityShootBow(event);
        }
    }
    @EventHandler
    public void onEntityChangeBlock(EntityChangeBlockEvent event){
        yami_yami.onEntityChangeBlock(event);

    }
    @EventHandler
    public void onInventoryClick(InventoryClickEvent event){
        if(users.containsKey(event.getWhoClicked().getName())) {
            abilityUser user = users.get(event.getWhoClicked().getName());
            user.onInventoryClick(event);
        }
    }

    @EventHandler(ignoreCancelled = true)
    public void onBlockBreak(BlockBreakEvent event) {
        ope_ope.onBlockBreak(event);
    }

    @EventHandler
    public void onPlayerRespawn(PlayerRespawnEvent event){
        skinsChanger.resetSkin(event.getPlayer());
        if(users.containsKey(event.getPlayer().getName())) {
            abilityUser user = users.get(event.getPlayer().getName());
            user.onPlayerRespawn(event);
        }
    }

    @EventHandler
    public void onItemDamage(PlayerItemDamageEvent event){
        if(users.containsKey(event.getPlayer().getName())) {
            abilityUser user = users.get(event.getPlayer().getName());
            user.onItemDamage(event);
        }
    }

    @EventHandler(ignoreCancelled = true)
    public void onEntityToggleGlide(EntityToggleGlideEvent event) {
        zushi_zushi.onEntityToggleGlide(event);
    }

    @EventHandler(ignoreCancelled = true)
    public void onPlayerMove(PlayerMoveEvent event) {
        zushi_zushi.onPlayerMove(event);

    }

    @EventHandler(ignoreCancelled = true)
    public void onEntityDamageByEntity(EntityDamageByEntityEvent event) {
        ope_ope.onEntityDamageByEntity(event);

        String sukeUserName;
        Player sukeUser;

        if(suke_suke.exploration){

            sukeUserName = plugin.getConfig().getString("FruitAssociations.suke_suke");

            if(event.getDamager().getName().equals(sukeUserName)){
                sukeUser = Bukkit.getPlayer(sukeUserName);
                suke_suke.cancelStopInvisibleTask = true;
                sukeUser.removePotionEffect(PotionEffectType.INVISIBILITY);
                ((suke_suke)users.get(sukeUserName).getDFAbilities()).finishInvisibility(sukeUser);
            }
        }
    }

    @EventHandler(ignoreCancelled = true)
    public void onPlayerJoin(PlayerJoinEvent event) {
        if(users.containsKey(event.getPlayer().getName())) {
            users.get(event.getPlayer().getName()).onPlayerJoin(event);
        }

        String sukeUserName;
        Player sukeUser, other = event.getPlayer();

        sukeUserName = plugin.getConfig().getString("FruitAssociations.suke_suke");

        if(!sukeUserName.equals("none")) {

            sukeUser = Bukkit.getPlayer(sukeUserName);

            if(suke_suke.getInvisible())
                other.hidePlayer(plugin, sukeUser);
            else if(sukeUser != null)
                if(sukeUser.isOnline())
                    other.showPlayer(plugin, sukeUser);



            if(event.getPlayer().getName().equals(sukeUserName)){
                sukeUser = Bukkit.getPlayer(sukeUserName);
                sukeUser.removePotionEffect(PotionEffectType.INVISIBILITY);
            }


        }
    }


}
