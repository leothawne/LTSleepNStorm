/*
 * Copyright (C) 2019 Murilo Amaral Nappi
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package io.github.leothawne.LTSleepNStorm.command;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import io.github.leothawne.LTSleepNStorm.LTSleepNStorm;

public final class SleepNStormCommand implements CommandExecutor {
	public SleepNStormCommand() {}
	@Override
	public final boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		if(sender.hasPermission("LTSleepNStorm.use")) {
			if(args.length == 0) {
				sender.sendMessage(ChatColor.AQUA + "=+=+=+= [" + LTSleepNStorm.getInstance().getDescription().getName() + "] =+=+=+=");
				sender.sendMessage(ChatColor.GOLD + LTSleepNStorm.getInstance().getDescription().getDescription());
				sender.sendMessage("");
				sender.sendMessage(ChatColor.GREEN + "/sleepnstorm " + ChatColor.AQUA + "- Main command of the plugin.");
			} else if(sender.hasPermission("LTSleepNStorm.admin") || sender.isOp()) {
				if(args[0].equalsIgnoreCase("reset")) {
					if(args.length < 2) {
						if(sender instanceof Player) {
							final Player player = (Player) sender;
							final ArrayList<?> worldList = new ArrayList<>(LTSleepNStorm.getInstance().getConfiguration().getList("worlds"));
							if(worldList.contains(player.getLocation().getWorld().getName())) {
								player.getLocation().getWorld().setFullTime(0);
								final String dayTag = LTSleepNStorm.getInstance().getLanguage().getString("world-day-tag");
								for(final Player players : Bukkit.getOnlinePlayers()) if(players.getLocation().getWorld().equals(player.getLocation().getWorld())) {
									players.sendMessage(ChatColor.RED + "" + LTSleepNStorm.getInstance().getLanguage().getString("world-day-reset"));
									players.sendTitle(ChatColor.GOLD + "" + dayTag + "" + ChatColor.AQUA + "" + "0", null, 10, 70, 20);
								}
							} else player.sendMessage(ChatColor.AQUA + "[" + LTSleepNStorm.getInstance().getDescription().getName() + "] " + ChatColor.YELLOW + "" + LTSleepNStorm.getInstance().getLanguage().getString("world-blocked"));
						} else for(final World world : LTSleepNStorm.getInstance().getServer().getWorlds()) {
							final ArrayList<?> worldList = new ArrayList<>(LTSleepNStorm.getInstance().getConfiguration().getList("worlds"));
							if(worldList.contains(world.getName())) {
								world.setFullTime(0);
								sender.sendMessage(ChatColor.RED + "" + world.getName() + ": " + LTSleepNStorm.getInstance().getLanguage().getString("world-day-reset"));
								final String dayTag = LTSleepNStorm.getInstance().getLanguage().getString("world-day-tag");
								for(final Player player : Bukkit.getOnlinePlayers()) if(player.getLocation().getWorld().equals(world)) {
									player.sendMessage(ChatColor.RED + "" + LTSleepNStorm.getInstance().getLanguage().getString("world-day-reset"));
									player.sendTitle(ChatColor.GOLD + "" + dayTag + "" + ChatColor.AQUA + "" + "0", null, 10, 70, 20);
								}
							}
						}
					} else if(args.length < 3) {
						if(args[1].equals("*")) for(final World world : Bukkit.getWorlds()) {
							final ArrayList<?> worldList = new ArrayList<>(LTSleepNStorm.getInstance().getConfiguration().getList("worlds"));
							if(worldList.contains(world.getName())) {
								world.setFullTime(0);
								sender.sendMessage(ChatColor.RED + "" + world.getName() + ": " + LTSleepNStorm.getInstance().getLanguage().getString("world-day-reset"));
								final String dayTag = LTSleepNStorm.getInstance().getLanguage().getString("world-day-tag");
								for(final Player player : Bukkit.getOnlinePlayers()) if(player.getLocation().getWorld().equals(world)) {
									player.sendMessage(ChatColor.RED + "" + LTSleepNStorm.getInstance().getLanguage().getString("world-day-reset"));
									player.sendTitle(ChatColor.GOLD + "" + dayTag + "" + ChatColor.AQUA + "" + "0", null, 10, 70, 20);
								}
							}
						} else {
							final World world = Bukkit.getWorld(args[1]);
							if(world != null) {
								final ArrayList<?> worldList = new ArrayList<>(LTSleepNStorm.getInstance().getConfiguration().getList("worlds"));
								if(worldList.contains(world.getName())) {
									world.setFullTime(0);
									sender.sendMessage(ChatColor.RED + "" + world.getName() + ": " + LTSleepNStorm.getInstance().getLanguage().getString("world-day-reset"));
									final String dayTag = LTSleepNStorm.getInstance().getLanguage().getString("world-day-tag");
									for(final Player player : Bukkit.getOnlinePlayers()) if(player.getLocation().getWorld().equals(world)) {
										player.sendMessage(ChatColor.RED + "" + LTSleepNStorm.getInstance().getLanguage().getString("world-day-reset"));
										player.sendTitle(ChatColor.GOLD + "" + dayTag + "" + ChatColor.AQUA + "" + "0", null, 10, 70, 20);
									}
								} else sender.sendMessage(ChatColor.AQUA + "[" + LTSleepNStorm.getInstance().getDescription().getName() + "] " + ChatColor.YELLOW + "" + LTSleepNStorm.getInstance().getLanguage().getString("world-blocked"));
							} else {
								final String[] notFoundTag = LTSleepNStorm.getInstance().getLanguage().getString("world-not-found").split("%");
								sender.sendMessage(ChatColor.AQUA + "[" + LTSleepNStorm.getInstance().getDescription().getName() + "] " + ChatColor.YELLOW + "" + notFoundTag[0] + "" + ChatColor.GREEN + "" + args[1] + "" + ChatColor.YELLOW + "" + notFoundTag[1]);
							}
						}
					} else sender.sendMessage(ChatColor.AQUA + "[" + LTSleepNStorm.getInstance().getDescription().getName() + "] " + ChatColor.YELLOW + "" + LTSleepNStorm.getInstance().getLanguage().getString("player-tma"));
				} else sender.sendMessage(ChatColor.AQUA + "[" + LTSleepNStorm.getInstance().getDescription().getName() + "] " + ChatColor.YELLOW + "Invalid subcommand! Type " + ChatColor.GREEN + "/sleepnstorm " + ChatColor.YELLOW + "for help.");
			} else sender.sendMessage(ChatColor.AQUA + "[" + LTSleepNStorm.getInstance().getDescription().getName() + "] " + ChatColor.YELLOW + "" + LTSleepNStorm.getInstance().getLanguage().getString("no-permission"));
		} else sender.sendMessage(ChatColor.AQUA + "[" + LTSleepNStorm.getInstance().getDescription().getName() + "] " + ChatColor.YELLOW + "" + LTSleepNStorm.getInstance().getLanguage().getString("no-permission"));
		return true;
	}
}