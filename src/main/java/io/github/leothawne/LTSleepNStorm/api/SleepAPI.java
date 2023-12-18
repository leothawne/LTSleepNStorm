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
package io.github.leothawne.LTSleepNStorm.api;

import java.util.ArrayList;

import org.bukkit.ChatColor;
import org.bukkit.Effect;
import org.bukkit.Sound;
import org.bukkit.SoundCategory;
import org.bukkit.Statistic;
import org.bukkit.World.Environment;
import org.bukkit.block.Block;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import io.github.leothawne.LTSleepNStorm.LTSleepNStorm;

public final class SleepAPI {
	public static final void runSleep(final LTSleepNStorm plugin, final FileConfiguration configuration, final FileConfiguration language, final Block block, final Player player) {
		final ArrayList<?> worldList = new ArrayList<>(configuration.getList("worlds"));
		if(worldList.contains(player.getLocation().getWorld().getName())) {
			if(player.getLocation().getWorld().getEnvironment() != Environment.NETHER && player.getLocation().getWorld().getEnvironment() != Environment.THE_END) {
				boolean stormPassed = false;
				boolean nightPassed = false;
				if(player.getLocation().getWorld().getTime() > 12541 && player.getLocation().getWorld().getTime() < 23458) {
					player.getLocation().getWorld().setTime(0);
					nightPassed = true;
					if(player.getLocation().getWorld().hasStorm() == true){
						player.getLocation().getWorld().setStorm(false);
						stormPassed = true;
					}
					if(player.getLocation().getWorld().isThundering() == true){
						player.getLocation().getWorld().setThundering(false);
						stormPassed = true;
					}
				} else {
					if(player.getLocation().getWorld().hasStorm() == true && player.getLocation().getWorld().isThundering() == true) {
						player.getLocation().getWorld().setStorm(false);
						player.getLocation().getWorld().setThundering(false);
						stormPassed = true;
					}
				}
				final int dayCount = (int) player.getLocation().getWorld().getFullTime() / 24000;
				final String dayTag = language.getString("world-day-tag");
				final String[] nightTag = language.getString("world-night-passed").split("%");
				final String[] stormTag = language.getString("world-storm-passed").split("%");
				final String[] nightStormTag = language.getString("world-night-storm-passed").split("%");
				for(final Player players : plugin.getServer().getOnlinePlayers()) {
					if(players.getLocation().getWorld().equals(player.getLocation().getWorld())) {
						if(nightPassed && !stormPassed) {
							players.sendMessage(ChatColor.AQUA + "" + nightTag[0] + "" + ChatColor.GOLD + "" + player.getName() + "" + ChatColor.AQUA +  "" + nightTag[1]);
							players.sendTitle(ChatColor.GOLD + "" + dayTag + "" + ChatColor.AQUA + "" + String.valueOf(dayCount), null, 10, 70, 20);
							runEffect(plugin, configuration, block, player);
						} else if(!nightPassed && stormPassed) {
							players.sendMessage(ChatColor.AQUA + "" + stormTag[0] + "" + ChatColor.GOLD + "" + player.getName() + "" + ChatColor.AQUA +  "" + stormTag[1]);
							//runEffect(plugin, configuration, block, player);
						} else if(nightPassed && stormPassed) {
							players.sendMessage(ChatColor.AQUA + "" + nightStormTag[0] + "" + ChatColor.GOLD + "" + player.getName() + "" + ChatColor.AQUA +  "" + nightStormTag[1]);
							players.sendTitle(ChatColor.GOLD + "" + dayTag + "" + ChatColor.AQUA + "" + String.valueOf(dayCount), null, 10, 70, 20);
							runEffect(plugin, configuration, block, player);
						}
						if(nightPassed) players.setStatistic(Statistic.TIME_SINCE_REST, 0);
					}
				}
			} else {
				final String environment = player.getLocation().getWorld().getEnvironment().toString();
				final String[] envTag = language.getString("world-environment-error").split("%");
				player.sendMessage(ChatColor.RED +  "" + envTag[0] + "" + ChatColor.GOLD + "" + environment + "" + ChatColor.RED + "" + envTag[1]);
			}
		}
	}
	private static final void runEffect(final LTSleepNStorm plugin, final FileConfiguration configuration, final Block bed, final Player player) {
		if(configuration.getBoolean("use-effects")) {
			//bed.getLocation().getWorld().strikeLightningEffect(bed.getLocation());
			bed.getLocation().getWorld().playEffect(bed.getLocation(), Effect.ENDER_SIGNAL, 0, 10);
			bed.getLocation().getWorld().playSound(bed.getLocation(), Sound.BLOCK_BEACON_POWER_SELECT,  SoundCategory.MASTER, 0.6f, 2f);
			player.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, 20 * 4, 1), true);
			if(!player.getLocation().equals(bed.getLocation())) {
				player.getLocation().getWorld().playEffect(player.getLocation(), Effect.ENDER_SIGNAL, 0, 10);
				player.getLocation().getWorld().playSound(player.getLocation(), Sound.BLOCK_BEACON_POWER_SELECT,  SoundCategory.MASTER, 0.6f, 2f);
			}
		}
	}
}