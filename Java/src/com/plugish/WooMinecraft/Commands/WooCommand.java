package com.plugish.WooMinecraft.Commands;

import com.plugish.WooMinecraft.WooMinecraft;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.security.SecureRandom;
import java.util.UUID;

public class WooCommand implements CommandExecutor {

    public static WooMinecraft plugin = WooMinecraft.instance;
    public static String Theme = ChatColor.DARK_PURPLE + "[" + ChatColor.WHITE + "Woo" + ChatColor.DARK_PURPLE + "] " + ChatColor.DARK_PURPLE + "";

    @Override
    public boolean onCommand(CommandSender s, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("woo") && args.length == 0) {
            if (s.hasPermission("woo.admin") || s.isOp()) {
                s.sendMessage( Theme + " Available Commands: /woo [register , reload , check]");
            } else {
                s.sendMessage(Theme + " You Don't Have Permission for that Command!");
            }
        } else if(command.getName().equalsIgnoreCase("woo") && args.length == 1) {
            if(args[0].equalsIgnoreCase("reload")) {
                if(s.hasPermission("woo.admin") || s.isOp()) {
                    plugin.reloadConfig();
                    s.sendMessage(Theme + " Config Reloaded");
                } else {
                    s.sendMessage(Theme + " You Don't Have Permission for that Command!");
                }
            } else if(args[0].equalsIgnoreCase("register")) {
                s.sendMessage("[Woo] You have used the register command!");
                if(s.hasPermission("woo.admin") || s.isOp()) {
                    /*
                    * Creating a random UUID (Universally unique identifier).
                    */
                    UUID uuid = UUID.randomUUID();
                    String key = "";
                    s.sendMessage(Theme + " Created key string == nothing");
                    if (plugin.c.getString(plugin.urlPath + ".key") == "") {
                        s.sendMessage(Theme + " Getting empty config path");
                        key = uuid.toString().replaceAll("-", "");
                        s.sendMessage(Theme + " set key = to uuid");
                        plugin.c.set(plugin.urlPath + ".key", key);
                        s.sendMessage(Theme + " Almost There!");
                        plugin.saveConfig();
                        s.sendMessage(Theme + " Saved Config!");
                        s.sendMessage(Theme + " Key: " + key);
                        s.sendMessage(Theme + " Copy this key and put it in your WooMinecraft options panel in WordPress");
                    } else {
                        key = plugin.c.getString(plugin.urlPath + ".key");
                        s.sendMessage(Theme + " key already set");
                    }
                } else {
                    s.sendMessage(Theme + " You Don't Have Permission for that Command!");
                }
            } else if(args[0].equalsIgnoreCase("check")) {
                if(s.hasPermission("woo.admin") || s.isOp()) {
                    // well we need to run the check then don't we?
                    plugin.check();
                    s.sendMessage(Theme + " Checked Purchases!");
                } else {
                    s.sendMessage(Theme + " You Don't Have Permission for that Command!");
                }
            }
        }
        return true;
    }
}
