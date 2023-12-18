package io.github.leothawne.LTSleepNStorm.module;

import java.io.File;

import org.bukkit.configuration.file.FileConfiguration;

import io.github.leothawne.LTSleepNStorm.LTSleepNStorm;
import io.github.leothawne.LTSleepNStorm.type.VersionType;

public final class ConfigurationModule {
	public static final void preLoad() {
		final File configFile = new File(LTSleepNStorm.getInstance().getDataFolder(), "config.yml");
		if(configFile.exists() == false) {
			LTSleepNStorm.getInstance().getConsole().info("Creating config.yml...");
			LTSleepNStorm.getInstance().saveDefaultConfig();
			LTSleepNStorm.getInstance().getConsole().info("Done!");
		}
	}
	public static final FileConfiguration load() {
		final File configFile = new File(LTSleepNStorm.getInstance().getDataFolder(), "config.yml");
		if(configFile.exists()) {
			final FileConfiguration configuration = LTSleepNStorm.getInstance().getConfig();
			LTSleepNStorm.getInstance().getConsole().info("Loaded config.yml.");
			if(configuration.getInt("config-version") != Integer.parseInt(DataModule.getVersion(VersionType.CONFIG_YML))) LTSleepNStorm.getInstance().getConsole().severe("config.yml outdated. Delete it restart the server.");
			return configuration;
		}
		LTSleepNStorm.getInstance().getConsole().severe("config.yml is missing.");
		return null;
	}
}