package io.github.leothawne.LTSleepNStorm.api;

import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Monster;

public final class NearbyMonstersAPI {
	public static final boolean isLocationSafe(final Location location) {
		for(final Entity entity : location.getWorld().getNearbyEntities(location, 10, 10, 10)) if(entity instanceof Monster) return false;
		return true;
	}
}