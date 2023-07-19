package me.flexicraft.util;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.util.StringUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FlexiCraftTabCompleter implements TabCompleter {
    private static final String[] COMMANDS = { "reload", "kick", "fly", "ban", "clear", "unban", "mute", "help" };

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        final List<String> completions = new ArrayList<>();

        // TODO: Filter permissions for commands so only the users with the permissions can use tabcomplete
        // List<String> commands = new ArrayList<>();

        if (args.length == 1) {
            //create new array
            StringUtil.copyPartialMatches(args[0], Arrays.asList(COMMANDS), completions);
            //Collections.sort(completions);

            //Test shit
            // System.out.println(completions.toString());
            // sender.sendMessage(completions.toString());
        } else if (args.length == 2)  {
            // TODO: Second args with perms
        }
            return completions;
    }
}