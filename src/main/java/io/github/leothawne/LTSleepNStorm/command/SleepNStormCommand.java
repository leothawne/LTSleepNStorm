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

import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import io.github.leothawne.LTSleepNStorm.LTSleepNStorm;

public final class SleepNStormCommand implements CommandExecutor {
	private LTSleepNStorm plugin;
	private FileConfiguration configuration;
	private FileConfiguration language;
	public SleepNStormCommand(final LTSleepNStorm plugin, final FileConfiguration configuration, final FileConfiguration language) {
		this.plugin = plugin;
		this.configuration = configuration;
		this.language = language;
	}
	@Override
	public final boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		if(sender.hasPermission("LTSleepNStorm.use")) {
			if(args.length == 0) {
				sender.sendMessage(ChatColor.AQUA + "=+=+=+= [" + this.plugin.getDescription().getName() + "] =+=+=+=");
				sender.sendMessage(ChatColor.GOLD + this.plugin.getDescription().getDescription());
				sender.sendMessage("");
				sender.sendMessage(ChatColor.GREEN + "/sleepnstorm " + ChatColor.AQUA + "- Main command of the plugin.");
			} else if(sender.hasPermission("LTSleepNStorm.admin") || sender.isOp()) {
				if(args[0].equalsIgnoreCase("reset")) {
					if(args.length < 2) {
						if(sender instanceof Player) {
							final Player player = (Player) sender;
							final ArrayList<?> worldList = new ArrayList<>(this.configuration.getList("worlds"));
							if(worldList.contains(player.getLocation().getWorld().getName())) {
								player.getLocation().getWorld().setFullTime(0);
								final String dayTag = this.language.getString("world-day-tag");
								for(final Player players : this.plugin.getServer().getOnlinePlayers()) if(players.getLocation().getWorld().equals(player.getLocation().getWorld())) {
									players.sendMessage(ChatColor.RED + "" + this.language.getString("world-day-reset"));
									players.sendTitle(ChatColor.GOLD + "" + dayTag + "" + ChatColor.AQUA + "" + "0", null, 10, 70, 20);
								}
							} else player.sendMessage(ChatColor.AQUA + "[" + this.plugin.getDescription().getName() + "] " + ChatColor.YELLOW + "" + this.language.getString("world-blocked"));
						} else for(final World world : this.plugin.getServer().getWorlds()) {
							final ArrayList<?> worldList = new ArrayList<>(this.configuration.getList("worlds"));
							if(worldList.contains(world.getName())) {
								world.setFullTime(0);
								sender.sendMessage(ChatColor.RED + "" + world.getName() + ": " + this.language.getString("world-day-reset"));
								final String dayTag = this.language.getString("world-day-tag");
								for(final Player player : this.plugin.getServer().getOnlinePlayers()) if(player.getLocation().getWorld().equals(world)) {
									player.sendMessage(ChatColor.RED + "" + this.language.getString("world-day-reset"));
									player.sendTitle(ChatColor.GOLD + "" + dayTag + "" + ChatColor.AQUA + "" + "0", null, 10, 70, 20);
								}
							}
						}
					} else if(args.length < 3) {
						if(args[1].equals("*")) for(final World world : this.plugin.getServer().getWorlds()) {
							final ArrayList<?> worldList = new ArrayList<>(this.configuration.getList("worlds"));
							if(worldList.contains(world.getName())) {
								world.setFullTime(0);
								sender.sendMessage(ChatColor.RED + "" + world.getName() + ": " + this.language.getString("world-day-reset"));
								final String dayTag = this.language.getString("world-day-tag");
								for(final Player player : this.plugin.getServer().getOnlinePlayers()) if(player.getLocation().getWorld().equals(world)) {
									player.sendMessage(ChatColor.RED + "" + this.language.getString("world-day-reset"));
									player.sendTitle(ChatColor.GOLD + "" + dayTag + "" + ChatColor.AQUA + "" + "0", null, 10, 70, 20);
								}
							}
						} else {
							final World world = this.plugin.getServer().getWorld(args[1]);
							if(world != null) {
								final ArrayList<?> worldList = new ArrayList<>(this.configuration.getList("worlds"));
								if(worldList.contains(world.getName())) {
									world.setFullTime(0);
									sender.sendMessage(ChatColor.RED + "" + world.getName() + ": " + this.language.getString("world-day-reset"));
									final String dayTag = this.language.getString("world-day-tag");
									for(final Player player : this.plugin.getServer().getOnlinePlayers()) if(player.getLocation().getWorld().equals(world)) {
										player.sendMessage(ChatColor.RED + "" + this.language.getString("world-day-reset"));
										player.sendTitle(ChatColor.GOLD + "" + dayTag + "" + ChatColor.AQUA + "" + "0", null, 10, 70, 20);
									}
								} else sender.sendMessage(ChatColor.AQUA + "[" + this.plugin.getDescription().getName() + "] " + ChatColor.YELLOW + "" + this.language.getString("world-blocked"));
							} else {
								final String[] notFoundTag = this.language.getString("world-not-found").split("%");
								sender.sendMessage(ChatColor.AQUA + "[" + this.plugin.getDescription().getName() + "] " + ChatColor.YELLOW + "" + notFoundTag[0] + "" + ChatColor.GREEN + "" + args[1] + "" + ChatColor.YELLOW + "" + notFoundTag[1]);
							}
						}
					} else sender.sendMessage(ChatColor.AQUA + "[" + this.plugin.getDescription().getName() + "] " + ChatColor.YELLOW + "" + this.language.getString("player-tma"));
				} else sender.sendMessage(ChatColor.AQUA + "[" + this.plugin.getDescription().getName() + "] " + ChatColor.YELLOW + "Invalid subcommand! Type " + ChatColor.GREEN + "/sleepnstorm " + ChatColor.YELLOW + "for help.");
			} else sender.sendMessage(ChatColor.AQUA + "[" + this.plugin.getDescription().getName() + "] " + ChatColor.YELLOW + "" + this.language.getString("no-permission"));
		} else sender.sendMessage(ChatColor.AQUA + "[" + this.plugin.getDescription().getName() + "] " + ChatColor.YELLOW + "" + this.language.getString("no-permission"));
		return true;
	}
}