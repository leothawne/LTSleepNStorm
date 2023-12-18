package io.github.leothawne.LTSleepNStorm.api;

import java.util.UUID;

import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitScheduler;

import io.github.leothawne.LTSleepNStorm.LTSleepNStorm;
import io.github.leothawne.LTSleepNStorm.module.ConsoleModule;

/**
 * 
 * The API class.
 * 
 * @author leothawne
 * 
 */
public final class LTSleepNStormAPI {
	private LTSleepNStorm plugin;
	private ConsoleModule console;
	private FileConfiguration configuration;
	private FileConfiguration language;
	private MetricsAPI metrics;
	private BukkitScheduler scheduler;
	private int adTask;
	/**
	 * 
	 * @deprecated There is no need to manually create
	 * an object with this constructor when
	 * you can easily use {@link LTSleepNStorm#getAPI()}.
	 * 
	 */
	public LTSleepNStormAPI(final LTSleepNStorm plugin, final ConsoleModule console, final FileConfiguration configuration, final FileConfiguration language, final MetricsAPI metrics, final BukkitScheduler scheduler, final int adTask) {
		this.plugin = plugin;
		this.console = console;
		this.configuration = configuration;
		this.language = language;
		this.metrics = metrics;
		this.scheduler = scheduler;
		this.adTask = adTask;
	}
	/**
	 * 
	 * Returns a boolean type value that can be used
	 * to determine if a player is away from hostile mobs.
	 * 
	 * @param location A Location type variable.
	 * 
	 * @return A boolean type value.
	 * 
	 */
	public final boolean isPlayerSafe(final Location location) {
		return NearbyMonstersAPI.isLocationSafe(location);
	}
	/**
	 * 
	 * Returns a ConsoleLoader type value that can
	 * be used to log messages on the server console.
	 * 
	 * @return A ConsoleLoader type value.
	 * 
	 */
	public final ConsoleModule getConsoleSender() {
		return this.console;
	}
	/**
	 * 
	 * Returns the default AFK
	 * level defined in config.yml.
	 * 
	 * @return An Integer type value.
	 * 
	 */
	public final int getDefaultAFKLevel(){
		return this.configuration.getInt("auto-restmode");
	}
	/**
	 * 
	 * Returns a boolean type value that can be used to determine
	 * if the plugin is currently using bStats.
	 * 
	 * @return A boolean type value.
	 * 
	 */
	public final boolean isMetricsEnabled() {
		return this.metrics.isEnabled();
	}
	/**
	 * 
	 * Returns a FileConfiguration type value that can be used to access
	 * all configuration variables been used by the plugin.
	 * 
	 * @return A FileConfiguration type value.
	 * 
	 */
	public final FileConfiguration getConfigurationMap() {
		return this.configuration;
	}
	/**
	 * 
	 * Returns a FileConfiguration type value that can be used to access
	 * all language variables been used by the plugin.
	 * 
	 * @return A FileConfiguration type value.
	 * 
	 */
	public final FileConfiguration getLanguageMap() {
		return this.language;
	}
	/**
	 * 
	 * Forces the player to sleep.
	 * Permission nodes not required.
	 * 
	 * @param player The Player type variable.
	 * 
	 */
	public final void forceSleep(final Player player) {
		if(NearbyMonstersAPI.isLocationSafe(player.getLocation())) SleepAPI.runSleep(this.plugin, this.configuration, this.language, null, player);
	}
	/**
	 * 
	 * Forces the player to sleep.
	 * Permission nodes not required.
	 * 
	 * @param playerUUID The player's unique id.
	 * 
	 */
	public final void forceSleep(final UUID playerUUID) {
		this.forceSleep(this.plugin.getServer().getPlayer(playerUUID));
	}
	/**
	 * 
	 * Forces the player to sleep.
	 * Permission nodes not required.
	 * 
	 * @param playerName The player's name.
	 * 
	 */
	public final void forceSleep(final String playerName) {
		this.forceSleep(this.plugin.getServer().getPlayer(playerName));
	}
	/**
	 * 
	 * Forces the player to sleep.
	 * Permission nodes not required.
	 * 
	 * @param player The Player type variable.
	 * @param ignoreNearbyMonsters "true" if you want to make the player sleep even with monsters nearby. Default: false.
	 * 
	 */
	public final void forceSleep(final Player player, final boolean ignoreNearbyMonsters) {
		if(!ignoreNearbyMonsters) {
			this.forceSleep(player);
		} else SleepAPI.runSleep(this.plugin, this.configuration, this.language, null, player);
	}
	/**
	 * 
	 * Forces the player to sleep.
	 * Permission nodes not required.
	 * 
	 * @param playerUUID The player's unique id.
	 * @param ignoreNearbyMonsters "true" if you want to make the player sleep even with monsters nearby. Default: false.
	 * 
	 */
	public final void forceSleep(final UUID playerUUID, final boolean ignoreNearbyMonsters) {
		this.forceSleep(this.plugin.getServer().getPlayer(playerUUID), ignoreNearbyMonsters);
	}
	/**
	 * 
	 * Forces the player to sleep.
	 * Permission nodes not required.
	 * 
	 * @param playerName The player's name.
	 * @param ignoreNearbyMonsters "true" if you want to make the player sleep even with monsters nearby. Default: false.
	 * 
	 */
	public final void forceSleep(final String playerName, final boolean ignoreNearbyMonsters) {
		this.forceSleep(this.plugin.getServer().getPlayer(playerName), ignoreNearbyMonsters);
	}
	/**
	 * 
	 * @deprecated For development mode only.
	 * 
	 */
	public final boolean cancelAdTask() {
		if(this.scheduler.isCurrentlyRunning(this.adTask) || this.scheduler.isQueued(this.adTask)) {
			this.scheduler.cancelTask(this.adTask);
			this.console.warning("Task #" + this.adTask + " cancelled (using LTSleepNStorm API).");
			return true;
		}
		return false;
	}
}