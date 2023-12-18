package io.github.leothawne.LTSleepNStorm.api;

import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import io.github.leothawne.LTSleepNStorm.LTSleepNStorm;

/**
 * 
 * The API class.
 * 
 * @author leothawne
 * 
 */
public final class LTSleepNStormAPI {
	public LTSleepNStormAPI() {}
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
	 * Returns the default AFK
	 * level defined in config.yml.
	 * 
	 * @return An Integer type value.
	 * 
	 */
	public final int getDefaultAFKLevel(){
		return LTSleepNStorm.getInstance().getConfiguration().getInt("auto-restmode");
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
		if(NearbyMonstersAPI.isLocationSafe(player.getLocation())) SleepAPI.runSleep(null, player);
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
		this.forceSleep(Bukkit.getPlayer(playerUUID));
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
		this.forceSleep(Bukkit.getPlayer(playerName));
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
			forceSleep(player);
		} else SleepAPI.runSleep(null, player);
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
		this.forceSleep(Bukkit.getPlayer(playerUUID), ignoreNearbyMonsters);
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
		this.forceSleep(Bukkit.getPlayer(playerName), ignoreNearbyMonsters);
	}
}