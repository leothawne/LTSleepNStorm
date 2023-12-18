package io.github.leothawne.LTSleepNStorm.api;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Effect;
import org.bukkit.Sound;
import org.bukkit.SoundCategory;
import org.bukkit.Statistic;
import org.bukkit.World.Environment;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import io.github.leothawne.LTSleepNStorm.LTSleepNStorm;

public final class SleepAPI {
	public static final void runSleep(final Block block, final Player player) {
		final ArrayList<?> worldList = new ArrayList<>(LTSleepNStorm.getInstance().getConfiguration().getList("worlds"));
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
				final String dayTag = LTSleepNStorm.getInstance().getLanguage().getString("world-day-tag");
				final String[] nightTag = LTSleepNStorm.getInstance().getLanguage().getString("world-night-passed").split("%");
				final String[] stormTag = LTSleepNStorm.getInstance().getLanguage().getString("world-storm-passed").split("%");
				final String[] nightStormTag = LTSleepNStorm.getInstance().getLanguage().getString("world-night-storm-passed").split("%");
				for(final Player players : Bukkit.getOnlinePlayers()) {
					if(players.getLocation().getWorld().equals(player.getLocation().getWorld())) {
						if(nightPassed && !stormPassed) {
							players.sendMessage(ChatColor.AQUA + "" + nightTag[0] + "" + ChatColor.GOLD + "" + player.getName() + "" + ChatColor.AQUA +  "" + nightTag[1]);
							players.sendTitle(ChatColor.GOLD + "" + dayTag + "" + ChatColor.AQUA + "" + String.valueOf(dayCount), null, 10, 70, 20);
							runEffect(block, player);
						} else if(!nightPassed && stormPassed) {
							players.sendMessage(ChatColor.AQUA + "" + stormTag[0] + "" + ChatColor.GOLD + "" + player.getName() + "" + ChatColor.AQUA +  "" + stormTag[1]);
							//runEffect(plugin, LTSleepNStorm.getInstance().getConfiguration(), block, player);
						} else if(nightPassed && stormPassed) {
							players.sendMessage(ChatColor.AQUA + "" + nightStormTag[0] + "" + ChatColor.GOLD + "" + player.getName() + "" + ChatColor.AQUA +  "" + nightStormTag[1]);
							players.sendTitle(ChatColor.GOLD + "" + dayTag + "" + ChatColor.AQUA + "" + String.valueOf(dayCount), null, 10, 70, 20);
							runEffect(block, player);
						}
						if(nightPassed) players.setStatistic(Statistic.TIME_SINCE_REST, 0);
					}
				}
			} else {
				final String environment = player.getLocation().getWorld().getEnvironment().toString();
				final String[] envTag = LTSleepNStorm.getInstance().getLanguage().getString("world-environment-error").split("%");
				player.sendMessage(ChatColor.RED +  "" + envTag[0] + "" + ChatColor.GOLD + "" + environment + "" + ChatColor.RED + "" + envTag[1]);
			}
		}
	}
	private static final void runEffect(final Block bed, final Player player) {
		if(LTSleepNStorm.getInstance().getConfiguration().getBoolean("use-effects")) {
			//bed.getLocation().getWorld().strikeLightningEffect(bed.getLocation());
			bed.getLocation().getWorld().playEffect(bed.getLocation(), Effect.ENDER_SIGNAL, 0, 10);
			bed.getLocation().getWorld().playSound(bed.getLocation(), Sound.BLOCK_BEACON_POWER_SELECT,  SoundCategory.MASTER, 0.6f, 2f);
			player.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, 20 * 4, 1));
			if(!player.getLocation().equals(bed.getLocation())) {
				player.getLocation().getWorld().playEffect(player.getLocation(), Effect.ENDER_SIGNAL, 0, 10);
				player.getLocation().getWorld().playSound(player.getLocation(), Sound.BLOCK_BEACON_POWER_SELECT,  SoundCategory.MASTER, 0.6f, 2f);
			}
		}
	}
}