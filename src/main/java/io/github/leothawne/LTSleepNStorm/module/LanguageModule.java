package io.github.leothawne.LTSleepNStorm.module;

import java.io.File;
import java.io.IOException;

import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import io.github.leothawne.LTSleepNStorm.LTSleepNStorm;
import io.github.leothawne.LTSleepNStorm.type.VersionType;

public final class LanguageModule {
	public static final void preLoad() {
		final File languageFile = new File(LTSleepNStorm.getInstance().getDataFolder(), LTSleepNStorm.getInstance().getConfiguration().getString("language") + ".yml");
		if(!languageFile.exists()) {
			LTSleepNStorm.getInstance().getConsole().info("Creating " + LTSleepNStorm.getInstance().getConfiguration().getString("language") + ".yml...");
			if(LTSleepNStorm.getInstance().getConfiguration().getString("language").equalsIgnoreCase("english") || LTSleepNStorm.getInstance().getConfiguration().getString("language").equalsIgnoreCase("portuguese")) {
				LTSleepNStorm.getInstance().saveResource(LTSleepNStorm.getInstance().getConfiguration().getString("language") + ".yml", false);
				LTSleepNStorm.getInstance().getConsole().info("Done!");
			} else LTSleepNStorm.getInstance().getConsole().severe(LTSleepNStorm.getInstance().getConfiguration().getString("language") + " is not supported yet. I suggest you to manually create a new file named " + LTSleepNStorm.getInstance().getConfiguration().getString("language") + ".yml and manually create the desired translation.");
		}
	}
	public static final FileConfiguration load() {
		final File languageFile = new File(LTSleepNStorm.getInstance().getDataFolder(), LTSleepNStorm.getInstance().getConfiguration().getString("language") + ".yml");
		if(languageFile.exists()) {
			final FileConfiguration languageConfig = new YamlConfiguration();
			try {
				languageConfig.load(languageFile);
				LTSleepNStorm.getInstance().getConsole().info("Loaded " + LTSleepNStorm.getInstance().getConfiguration().getString("language") + ".yml.");
				int languageVersion = 0;
				if(LTSleepNStorm.getInstance().getConfiguration().getString("language").equalsIgnoreCase("english")) languageVersion = Integer.parseInt(DataModule.getVersion(VersionType.ENGLISH_YML));
				if(LTSleepNStorm.getInstance().getConfiguration().getString("language").equalsIgnoreCase("portuguese")) languageVersion = Integer.parseInt(DataModule.getVersion(VersionType.PORTUGUESE_YML));
				if(languageVersion != 0) if(languageConfig.getInt("language-version") != languageVersion) LTSleepNStorm.getInstance().getConsole().severe(LTSleepNStorm.getInstance().getConfiguration().getString("language") + ".yml outdated. Delete it and restart the server.");
				return languageConfig;
			} catch(final IOException | InvalidConfigurationException exception) {
				exception.printStackTrace();
			}
		}
		LTSleepNStorm.getInstance().getConsole().severe(LTSleepNStorm.getInstance().getConfiguration().getString("language") + ".yml is missing.");
		return null;
	}
}