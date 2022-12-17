package scoreboardSystem;

import castSystem.castIdentification;
import htt.ophabs.OPhabs;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scoreboard.*;
import abilitieSystem.*;
import castSystem.caster;

import java.util.List;

public class abilitiesScoreboard {

    OPhabs plugin;

    public abilitiesScoreboard(OPhabs plugin){
        this.plugin = plugin;
    }


    public void ini(){

        new BukkitRunnable(){

            @Override
            public void run() {

                String
                        yamiValue = plugin.getConfig().getString("FruitAssociations.yami_yami"),
                        meraValue = plugin.getConfig().getString("FruitAssociations.mera_mera"),
                        guraValue = plugin.getConfig().getString("FruitAssociations.gura_gura"),
                        mokuValue = plugin.getConfig().getString("FruitAssociations.moku_moku"),
                        nekoReoparudoValue = plugin.getConfig().getString("FruitAssociations.neko_neko_reoparudo");

                ScoreboardManager manager = Bukkit.getScoreboardManager();

                Scoreboard scoreboardYami = manager.getNewScoreboard();
                Scoreboard scoreboardMera = manager.getNewScoreboard();
                Scoreboard scoreboardGura = manager.getNewScoreboard();
                Scoreboard scoreboardMoku = manager.getNewScoreboard();
                Scoreboard scoreboardNeko = manager.getNewScoreboard();



                if(!yamiValue.equals("none") && Bukkit.getPlayerExact(yamiValue) != null)
                    updateScoreboard(scoreboardYami ,Bukkit.getPlayerExact(yamiValue),abilitiesIdentification.namesYami, caster.yamiIndex );




                if(!meraValue.equals("none") && Bukkit.getPlayerExact(meraValue) != null)
                    updateScoreboard(scoreboardMera ,Bukkit.getPlayerExact(meraValue),abilitiesIdentification.namesMera, caster.meraIndex);

                if(!guraValue.equals("none") && Bukkit.getPlayerExact(guraValue) != null)
                    updateScoreboard(scoreboardGura ,Bukkit.getPlayerExact(guraValue),abilitiesIdentification.namesGura, caster.guraIndex);

                if(!mokuValue.equals("none") && Bukkit.getPlayerExact(mokuValue) != null)
                    updateScoreboard( scoreboardMoku ,Bukkit.getPlayerExact(mokuValue),abilitiesIdentification.namesMoku, caster.mokuIndex);

                if(!nekoReoparudoValue.equals("none"))
                    updateScoreboard(scoreboardNeko ,Bukkit.getPlayerExact(nekoReoparudoValue),abilitiesIdentification.namesNekoReoparudo, caster.nekoReoparudoIndex);

            }
        }.runTaskTimer(plugin,0,10);

    }

    public boolean updateScoreboard(Scoreboard scoreboard , Player player, List<String> names, int index){



        Objective objctive = scoreboard.registerNewObjective("abilitiesScoreboard","dummy");

        ItemStack caster = player.getInventory().getItemInMainHand();

        String abName1="Error", abName2="Error", abName3="Error", abName4 = "Error";

        switch (index){
            case 0:
                abName1 = ChatColor.BOLD + "" + names.get(1);
                abName2 = names.get(2);
                abName3 = names.get(2);
                abName4 = names.get(2);
                break;
            case 1:
                abName1 = names.get(1);
                abName2 = ChatColor.BOLD+ "" + names.get(2);
                abName3 = names.get(2);
                abName4 = names.get(2);
                break;
            case 2:
                abName1 = names.get(1);
                abName2 =  names.get(2);
                abName3 = ChatColor.BOLD + "" + names.get(2);
                abName4 = names.get(2);
                break;
            case 3:
                abName1 = names.get(1);
                abName2 = names.get(2);
                abName3 = names.get(2);
                abName4 = ChatColor.BOLD + "" + names.get(2);
                break;

        }

        if(castIdentification.itemIsCaster(caster)){

            objctive.setDisplaySlot(DisplaySlot.SIDEBAR);

            objctive.setDisplayName(names.get(0));


            Score score1 = objctive.getScore(abName1);
            score1.setScore(0);

            Score score2 = objctive.getScore(abName2);
            score2.setScore(1);


            player.setScoreboard(scoreboard);

        } else
            player.setScoreboard(Bukkit.getScoreboardManager().getNewScoreboard());


        return true;
    }


}
