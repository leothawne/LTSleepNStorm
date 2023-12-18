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
package io.github.leothawne.LTSleepNStorm;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitScheduler;

import com.google.gson.JsonObject;

import io.github.leothawne.LTSleepNStorm.api.LTSleepNStormAPI;
import io.github.leothawne.LTSleepNStorm.api.MetricsAPI;
import io.github.leothawne.LTSleepNStorm.command.SleepNStormCommand;
import io.github.leothawne.LTSleepNStorm.command.tabCompleter.SleepNStormCommandTabCompleter;
import io.github.leothawne.LTSleepNStorm.listener.BedListener;
import io.github.leothawne.LTSleepNStorm.module.AdModule;
import io.github.leothawne.LTSleepNStorm.module.ConfigurationModule;
import io.github.leothawne.LTSleepNStorm.module.ConsoleModule;
import io.github.leothawne.LTSleepNStorm.module.LanguageModule;
import io.github.leothawne.LTSleepNStorm.module.MetricsModule;
import io.github.leothawne.LTSleepNStorm.task.AdTask;

/**
 * Main class.
 * 
 * @author leothawne
 *
 */
public final class LTSleepNStorm extends JavaPlugin {
	private static LTSleepNStorm instance;
	private final ConsoleModule console = new ConsoleModule(this);
	private final void registerEvents(final Listener...listeners) {
		for(final Listener listener : listeners) Bukkit.getServer().getPluginManager().registerEvents(listener, this);
	}
	private FileConfiguration configuration;
	private FileConfiguration language;
	private MetricsAPI metrics;
	private BukkitScheduler scheduler;
	private ArrayList<JsonObject> adList = new ArrayList<JsonObject>();
	private int adTask = 0;
	/**
	 * 
	 * @deprecated Not for public use.
	 * 
	 */
	@Override
	public final void onEnable() {
		this.console.Hello();
		this.console.info("Loading...");
		ConfigurationModule.preLoad(this, this.console);
		this.configuration = ConfigurationModule.load(this, this.console);
		if(this.configuration.getBoolean("enable-plugin") == true) {
			this.metrics = MetricsModule.init(this, this.console);
			LanguageModule.preLoad(this, this.console, this.configuration);
			this.language = LanguageModule.load(this, this.console, this.configuration);
			this.getCommand("sleepnstorm").setExecutor(new SleepNStormCommand(this, this.configuration, this.language));
			this.getCommand("sleepnstorm").setTabCompleter(new SleepNStormCommandTabCompleter(this, this.configuration));
			this.scheduler = this.getServer().getScheduler();
			this.adList = AdModule.loadAdList();
			this.adTask = this.scheduler.scheduleAsyncRepeatingTask(this, new AdTask(this, this.console, this.adList), 0, 20 * 60 * 5);
			this.registerEvents(new BedListener(this, this.configuration, this.language));
			this.getServer().dispatchCommand(this.getServer().getConsoleSender(), "sleepnstormadmin update");
		} else this.getServer().getPluginManager().disablePlugin(this);
	}
	/**
	 * 
	 * @deprecated Not for public use.
	 * 
	 */
	@Override
	public final void onDisable() {
		this.console.info("Unloading...");
		if(this.scheduler != null) this.scheduler.cancelTasks(this);
	}
	/**
	 * 
	 * Method used to cast the API class.
	 * 
	 * @return The API class.
	 * 
	 */
	@SuppressWarnings("deprecation")
	public final LTSleepNStormAPI getAPI() {
		return new LTSleepNStormAPI(this, this.console, this.configuration, this.language, this.metrics, this.scheduler, this.adTask);
	}
	/**
	 * 
	 * Method used to cast the main class.
	 * 
	 * @return The main class.
	 * 
	 */
	public static final LTSleepNStorm getInstance() {
		if(LTSleepNStorm.instance == null) LTSleepNStorm.instance = new LTSleepNStorm();
		return LTSleepNStorm.instance;
	}
}