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
package io.github.leothawne.LTSleepNStorm.task;

import java.util.ArrayList;
import java.util.Random;

import org.bukkit.ChatColor;
import org.bukkit.command.ConsoleCommandSender;

import com.google.gson.JsonObject;

import io.github.leothawne.LTSleepNStorm.LTSleepNStorm;
import io.github.leothawne.LTSleepNStorm.module.AdModule;
import io.github.leothawne.LTSleepNStorm.module.ConsoleModule;

public final class AdTask implements Runnable {
	private LTSleepNStorm plugin;
	private ConsoleModule console;
	private ArrayList<JsonObject> adList;
	public AdTask(final LTSleepNStorm plugin, final ConsoleModule console, ArrayList<JsonObject> adList) {
		this.plugin = plugin;
		this.console = console;
		this.adList = adList;
	}
	@Override
	public final void run() {
		if(this.adList != null && adList.size() > 0) {
			final JsonObject ad = this.adList.get(new Random().nextInt(this.adList.size()));
			if(ad != null) {
				final ConsoleCommandSender console = this.plugin.getServer().getConsoleSender();
				final String title = ad.get("title").getAsString();
				final String description = ad.get("description").getAsString();
				final String author = ad.get("author").getAsString();
				console.sendMessage(ChatColor.YELLOW + "[========== " + this.plugin.getDescription().getName() + " Advertisement ==========]");
				console.sendMessage(ChatColor.LIGHT_PURPLE + title);
				console.sendMessage(ChatColor.DARK_PURPLE + "     " + description);
				console.sendMessage("");
				console.sendMessage(ChatColor.DARK_PURPLE + "~ " + author);
				console.sendMessage(ChatColor.YELLOW + "[=================================================]");
			} else this.console.warning("Unable to load ad.");
		} else this.console.warning("Unable to load ad list. Trying again...");
		this.adList = AdModule.loadAdList();
	}
}