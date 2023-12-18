package io.github.leothawne.LTSleepNStorm.module;

import java.util.HashMap;

import io.github.leothawne.LTSleepNStorm.type.VersionType;

public final class DataModule {
	private static final String VERSIONS = "CONFIG_YML:4,ENGLISH_YML:3,PORTUGUESE_YML:3,MINECRAFT:1.14,JAVA:7";
	private static final String Plugin_Date = "12/06/2019 15:34 (BRT)";
	private static final String Bukkit_API = "spigot-api-1.15-R0.1-SNAPSHOT";
	private static final String Ad_URL = "https://leothawne.github.io/adserver/adlist.json";
	public static final String getVersion(final VersionType type) {
		final String[] versionString = DataModule.VERSIONS.split(",");
		final HashMap<VersionType, String> versionMap = new HashMap<VersionType, String>();
		for(final String version : versionString) {
			final String[] string = version.split(":");
			versionMap.put(VersionType.valueOf(string[0]), string[1]);
		}
		return versionMap.get(type);
	}
	public static final String getVersionDate() {
		return DataModule.Plugin_Date;
	}
	public static final String getBukkitAPI() {
		return DataModule.Bukkit_API;
	}
	public static final String getAdURL() {
		return DataModule.Ad_URL;
	}
}
