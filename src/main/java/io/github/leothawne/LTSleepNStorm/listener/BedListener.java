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
package io.github.leothawne.LTSleepNStorm.listener;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.data.type.Bed;
import org.bukkit.block.data.type.Bed.Part;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;

import io.github.leothawne.LTSleepNStorm.LTSleepNStorm;
import io.github.leothawne.LTSleepNStorm.api.NearbyMonstersAPI;
import io.github.leothawne.LTSleepNStorm.api.SleepAPI;

public final class BedListener implements Listener {
	private LTSleepNStorm plugin;
	private FileConfiguration configuration;
	private FileConfiguration language;
	public BedListener(final LTSleepNStorm plugin, final FileConfiguration configuration, final FileConfiguration language) {
		this.plugin = plugin;
		this.configuration = configuration;
		this.language = language;
	}
	@EventHandler(priority = EventPriority.HIGHEST)
	public final void onPlayerInteract(final PlayerInteractEvent event) {
		final Player player = event.getPlayer();
		if(player.hasPermission("LTSleepNStorm.use")) if(event.getHand() == EquipmentSlot.HAND && event.getAction() == Action.RIGHT_CLICK_BLOCK){
			final Block block = event.getClickedBlock();
			if(block.getType().toString().contains("_BED")) {
				final Bed bedData = (Bed) block.getBlockData();
				boolean isObstructed = false;
				if(bedData.getFacing().equals(BlockFace.EAST)) {
					if(bedData.getPart().equals(Part.HEAD)) {
						if(!new Location(block.getLocation().getWorld(), block.getLocation().getBlockX(), (block.getLocation().getBlockY() + 1), block.getLocation().getBlockZ()).getBlock().getType().equals(Material.AIR)) isObstructed = true;
						if(!new Location(block.getLocation().getWorld(), (block.getLocation().getBlockX() - 1), (block.getLocation().getBlockY() + 1), block.getLocation().getBlockZ()).getBlock().getType().equals(Material.AIR)) isObstructed = true;
					}
					if(bedData.getPart().equals(Part.FOOT)) {
						if(!new Location(block.getLocation().getWorld(), block.getLocation().getBlockX(), (block.getLocation().getBlockY() + 1), block.getLocation().getBlockZ()).getBlock().getType().equals(Material.AIR)) isObstructed = true;
						if(!new Location(block.getLocation().getWorld(), (block.getLocation().getBlockX() + 1), (block.getLocation().getBlockY() + 1), block.getLocation().getBlockZ()).getBlock().getType().equals(Material.AIR)) isObstructed = true;
					}
				}
				if(bedData.getFacing().equals(BlockFace.NORTH)) {
					if(bedData.getPart().equals(Part.HEAD)) {
						if(!new Location(block.getLocation().getWorld(), block.getLocation().getBlockX(), (block.getLocation().getBlockY() + 1), block.getLocation().getBlockZ()).getBlock().getType().equals(Material.AIR)) isObstructed = true;
						if(!new Location(block.getLocation().getWorld(), block.getLocation().getBlockX(), (block.getLocation().getBlockY() + 1), (block.getLocation().getBlockZ() + 1)).getBlock().getType().equals(Material.AIR)) isObstructed = true;
					}
					if(bedData.getPart().equals(Part.FOOT)) {
						if(!new Location(block.getLocation().getWorld(), block.getLocation().getBlockX(), (block.getLocation().getBlockY() + 1), block.getLocation().getBlockZ()).getBlock().getType().equals(Material.AIR)) isObstructed = true;
						if(!new Location(block.getLocation().getWorld(), block.getLocation().getBlockX(), (block.getLocation().getBlockY() + 1), (block.getLocation().getBlockZ() - 1)).getBlock().getType().equals(Material.AIR)) isObstructed = true;
					}
				}
				if(bedData.getFacing().equals(BlockFace.WEST)) {
					if(bedData.getPart().equals(Part.HEAD)) {
						if(!new Location(block.getLocation().getWorld(), block.getLocation().getBlockX(), (block.getLocation().getBlockY() + 1), block.getLocation().getBlockZ()).getBlock().getType().equals(Material.AIR)) isObstructed = true;
						if(!new Location(block.getLocation().getWorld(), (block.getLocation().getBlockX() + 1), (block.getLocation().getBlockY() + 1), block.getLocation().getBlockZ()).getBlock().getType().equals(Material.AIR)) isObstructed = true;
					}
					if(bedData.getPart().equals(Part.FOOT)) {
						if(!new Location(block.getLocation().getWorld(), block.getLocation().getBlockX(), (block.getLocation().getBlockY() + 1), block.getLocation().getBlockZ()).getBlock().getType().equals(Material.AIR)) isObstructed = true;
						if(!new Location(block.getLocation().getWorld(), (block.getLocation().getBlockX() - 1), (block.getLocation().getBlockY() + 1), block.getLocation().getBlockZ()).getBlock().getType().equals(Material.AIR)) isObstructed = true;
					}
				}
				if(bedData.getFacing().equals(BlockFace.SOUTH)) {
					if(bedData.getPart().equals(Part.HEAD)) {
						if(!new Location(block.getLocation().getWorld(), block.getLocation().getBlockX(), (block.getLocation().getBlockY() + 1), block.getLocation().getBlockZ()).getBlock().getType().equals(Material.AIR)) isObstructed = true;
						if(!new Location(block.getLocation().getWorld(), block.getLocation().getBlockX(), (block.getLocation().getBlockY() + 1), (block.getLocation().getBlockZ() - 1)).getBlock().getType().equals(Material.AIR)) isObstructed = true;
					}
					if(bedData.getPart().equals(Part.FOOT)) {
						if(!new Location(block.getLocation().getWorld(), block.getLocation().getBlockX(), (block.getLocation().getBlockY() + 1), block.getLocation().getBlockZ()).getBlock().getType().equals(Material.AIR)) isObstructed = true;
						if(!new Location(block.getLocation().getWorld(), block.getLocation().getBlockX(), (block.getLocation().getBlockY() + 1), (block.getLocation().getBlockZ() + 1)).getBlock().getType().equals(Material.AIR)) isObstructed = true;
					}
				}
				if(!isObstructed) {
					if(player.getLocation().distance(block.getLocation()) <= 3.3) {
						if(NearbyMonstersAPI.isLocationSafe(block.getLocation())) {
							SleepAPI.runSleep(this.plugin, this.configuration, this.language, block, player);
						} else {
							player.sendMessage(ChatColor.AQUA + "[" + this.plugin.getDescription().getName() + "] " + ChatColor.YELLOW + this.language.getString("nearby-monsters"));
							event.setCancelled(true);
						}
					}
				} else {
					player.sendMessage(ChatColor.AQUA + "[" + this.plugin.getDescription().getName() + "] " + ChatColor.YELLOW + this.language.getString("bed-obstructed"));
					event.setCancelled(true);
				}
			}
		}
	}
}