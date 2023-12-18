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

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import io.github.leothawne.LTSleepNStorm.api.LTSleepNStormAPI;
import io.github.leothawne.LTSleepNStorm.command.SleepNStormCommand;
import io.github.leothawne.LTSleepNStorm.command.tabCompleter.SleepNStormCommandTabCompleter;
import io.github.leothawne.LTSleepNStorm.listener.BedListener;
import io.github.leothawne.LTSleepNStorm.module.ConfigurationModule;
import io.github.leothawne.LTSleepNStorm.module.ConsoleModule;
import io.github.leothawne.LTSleepNStorm.module.LanguageModule;
import io.github.leothawne.LTSleepNStorm.module.MetricsModule;

/**
 * Main class.
 * 
 * @author leothawne
 *
 */
public final class LTSleepNStorm extends JavaPlugin {
	private static LTSleepNStorm instance;
	private final void registerEvents(final Listener...listeners) {
		for(final Listener listener : listeners) Bukkit.getPluginManager().registerEvents(listener, this);
	}
	private final ConsoleModule console = new ConsoleModule();
	private FileConfiguration configuration;
	private FileConfiguration language;
	/*private BukkitScheduler scheduler;
	private ArrayList<JsonObject> adList = new ArrayList<JsonObject>();
	private int adTask = 0;*/
	@Override
	public final void onEnable() {
		instance = this;
		console.Hello();
		console.info("Loading...");
		ConfigurationModule.preLoad();
		configuration = ConfigurationModule.load();
		if(configuration.getBoolean("enable-plugin")) {
			MetricsModule.init();
			LanguageModule.preLoad();
			language = LanguageModule.load();
			getCommand("sleepnstorm").setExecutor(new SleepNStormCommand());
			getCommand("sleepnstorm").setTabCompleter(new SleepNStormCommandTabCompleter());
			/*scheduler = Bukkit.getScheduler();
			adList = AdModule.loadAdList();
			adTask = this.scheduler.scheduleSyncRepeatingTask(this, new AdTask(this, this.console, this.adList), 0, 20 * 60 * 5);*/
			registerEvents(new BedListener());
			getServer().dispatchCommand(Bukkit.getConsoleSender(), "sleepnstormadmin update");
		} else this.getServer().getPluginManager().disablePlugin(this);
	}
	@Override
	public final void onDisable() {
		console.info("Unloading...");
		//if(scheduler != null) scheduler.cancelTasks(this);
	}
	/**
	 * 
	 * Method used to cast the API class.
	 * 
	 * @return The API class.
	 * 
	 */
	public final LTSleepNStormAPI getAPI() {
		return new LTSleepNStormAPI();
	}
	/**
	 * 
	 * Method used to cast the main class.
	 * 
	 * @return The main class.
	 * 
	 */
	public static final LTSleepNStorm getInstance() {
		return LTSleepNStorm.instance;
	}
	public final ConsoleModule getConsole() {
		return console;
	}
	public final FileConfiguration getConfiguration() {
		return configuration;
	}
	public final FileConfiguration getLanguage() {
		return language;
	}
}