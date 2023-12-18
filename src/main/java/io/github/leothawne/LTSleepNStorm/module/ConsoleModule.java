package io.github.leothawne.LTSleepNStorm.module;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.ConsoleCommandSender;

import io.github.leothawne.LTSleepNStorm.LTSleepNStorm;
import io.github.leothawne.LTSleepNStorm.type.VersionType;

public final class ConsoleModule {
	public ConsoleModule() {}
	private final ConsoleCommandSender getConsoleSender() {
		return Bukkit.getConsoleSender();
	}
	public final void Hello() {
		getConsoleSender().sendMessage(ChatColor.AQUA + " _   _______ _____ _   _  _____ ");
		getConsoleSender().sendMessage(ChatColor.AQUA + "| | |__   __/ ____| \\ | |/ ____|");
		getConsoleSender().sendMessage(ChatColor.AQUA + "| |    | | | (___ |  \\| | (___  " + ChatColor.WHITE + "  Plugin version: " + LTSleepNStorm.getInstance().getDescription().getVersion() + " (API version: " + DataModule.getBukkitAPI() + ")");
		getConsoleSender().sendMessage(ChatColor.AQUA + "| |    | |  \\___ \\| . ` |\\___ \\ " + ChatColor.WHITE + "  Java required: " + DataModule.getVersion(VersionType.JAVA));
		getConsoleSender().sendMessage(ChatColor.AQUA + "| |____| |  ____) | |\\  |____) |" + ChatColor.WHITE + "  Released on: " + DataModule.getVersionDate());
		getConsoleSender().sendMessage(ChatColor.AQUA + "|______|_| |_____/|_| \\_|_____/ ");
	}
	public final void info(final String message) {
		getConsoleSender().sendMessage(ChatColor.WHITE + "[" + ChatColor.AQUA + "LTSNS " + ChatColor.GREEN + "I" + ChatColor.WHITE + "] " + message);
	}
	public final void warning(final String message) {
		getConsoleSender().sendMessage(ChatColor.WHITE + "[" + ChatColor.AQUA + "LTSNS " + ChatColor.YELLOW + "W" + ChatColor.WHITE + "] " + message);
	}
	public final void severe(final String message) {
		getConsoleSender().sendMessage(ChatColor.WHITE + "[" + ChatColor.AQUA + "LTSNS " + ChatColor.RED + "E" + ChatColor.WHITE + "] " + message);
	}
}