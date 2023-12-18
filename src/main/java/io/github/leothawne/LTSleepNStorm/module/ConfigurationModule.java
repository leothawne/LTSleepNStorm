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
package io.github.leothawne.LTSleepNStorm.module;

import java.io.File;

import org.bukkit.configuration.file.FileConfiguration;

import io.github.leothawne.LTSleepNStorm.LTSleepNStorm;
import io.github.leothawne.LTSleepNStorm.type.VersionType;

public final class ConfigurationModule {
	public static final void preLoad(final LTSleepNStorm plugin, final ConsoleModule console) {
		final File configFile = new File(plugin.getDataFolder(), "config.yml");
		if(configFile.exists() == false) {
			console.info("Creating config.yml...");
			plugin.saveDefaultConfig();
			console.info("Done!");
		}
	}
	public static final FileConfiguration load(final LTSleepNStorm plugin, final ConsoleModule console) {
		final File configFile = new File(plugin.getDataFolder(), "config.yml");
		if(configFile.exists()) {
			final FileConfiguration configuration = plugin.getConfig();
			console.info("Loaded config.yml.");
			if(configuration.getInt("config-version") != Integer.parseInt(DataModule.getVersion(VersionType.CONFIG_YML))) console.severe("config.yml outdated. Delete it restart the server.");
			return configuration;
		}
		console.severe("config.yml is missing.");
		return null;
	}
}