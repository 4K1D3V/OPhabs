package newSystem.commands;

import htt.ophabs.OPhabs;
import newSystem.OPUser;
import newSystem.consumables.ConsumableDevilFruit;
import newSystem.fruits.DevilFruit;
import newSystem.registry.FruitRegistry;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OPHCommandManager implements CommandExecutor, TabCompleter
{
    private HashMap<String, DevilFruit> fruitCommandMap = new HashMap<>();

    public OPHCommandManager()
    {
        //
        // Frutas
        //

        for (DevilFruit fruit : OPhabs.registrySystem.fruitRegistry.getFruitMap().values())
        {
            fruitCommandMap.put(fruit.getCommandName(), fruit);
        }
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
    {
        Player player = (Player) sender;
        String order = args[0];

        if (args.length == 3)
        {
            String fruitCommandName = args[1];
            Player targetPlayer = Bukkit.getServer().getPlayerExact(args[2]);

            if (targetPlayer == null)
            {
                player.sendMessage("Unknown player");
                return false;
            }

            if (order.equalsIgnoreCase("giveFruit"))
            {
                if (fruitCommandMap.containsKey(fruitCommandName))
                {
                    DevilFruit fruit = fruitCommandMap.get(fruitCommandName);
                    OPUser fruitOwner = fruit.getUser();

                    if (fruitOwner == null)
                    {
                        player.getInventory().addItem(ConsumableDevilFruit.getItem(fruit.getID()));
                        return true;
                    }
                    else
                    {
                        player.sendMessage("The fruit has already been consumed by " + fruitOwner.getPlayerName());
                        return false;
                    }
                }
                else
                {
                    player.sendMessage("The fruit doesn't exist");
                    return false;
                }
            }
        }
        else if (args.length == 2)
        {
            if (order.equalsIgnoreCase("removeFruit"))
            {
                FruitRegistry fruitRegistry = OPhabs.registrySystem.fruitRegistry;
                OPUser user = OPhabs.newUsers.getUserByName(args[1]);

                if (user == null)
                {
                    player.sendMessage("This player doesn't have a fruit");
                    return false;
                }

                if (fruitRegistry.unlinkFruitUser(user))
                    return true;
                else
                {
                    player.sendMessage("Couldn't unlink the fruit");
                    return false;
                }
            }
        }

        player.sendMessage("Unknown command");

        return false;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args)
    {
        List<String> tabOptions = new ArrayList<>();

        if (args.length == 1)
        {
            tabOptions.add("giveFruit");
            tabOptions.add("removeFruit");
        }

        if (args.length == 2 && args[0].equalsIgnoreCase("giveFruit"))
        {
            tabOptions.addAll(fruitCommandMap.keySet());
        }

        if (args.length == 2 && args[0].equalsIgnoreCase("removeFruit"))
        {
            Map<Integer, DevilFruit> fruitMap = OPhabs.registrySystem.fruitRegistry.getFruitMap();

            for (DevilFruit fruit : fruitMap.values())
            {
                OPUser fruitUser = fruit.getUser();

                if (fruitUser != null)
                    tabOptions.add(fruitUser.getPlayerName());
            }
        }

        if (args.length == 3)
        {
            for (Player player : Bukkit.getServer().getOnlinePlayers())
            {
                tabOptions.add(player.getName());
            }
        }

        return tabOptions;
    }
}