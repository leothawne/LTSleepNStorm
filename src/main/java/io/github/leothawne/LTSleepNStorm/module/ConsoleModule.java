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

import org.bukkit.ChatColor;
import org.bukkit.command.ConsoleCommandSender;

import io.github.leothawne.LTSleepNStorm.LTSleepNStorm;
import io.github.leothawne.LTSleepNStorm.type.VersionType;

public final class ConsoleModule {
	private LTSleepNStorm plugin;
	private String prefix;
	public ConsoleModule(final LTSleepNStorm plugin) {
		this.plugin = plugin;
		this.prefix = ChatColor.WHITE + "[" + this.plugin.getDescription().getName() + "]";
	}
	private final ConsoleCommandSender getConsoleSender() {
		return this.plugin.getServer().getConsoleSender();
	}
	public final void Hello() {
		getConsoleSender().sendMessage(ChatColor.AQUA + " _   _______ _____ _   _  _____ ");
		getConsoleSender().sendMessage(ChatColor.AQUA + "| | |__   __/ ____| \\ | |/ ____|");
		getConsoleSender().sendMessage(ChatColor.AQUA + "| |    | | | (___ |  \\| | (___  " + ChatColor.WHITE + "  Plugin version: " + this.plugin.getDescription().getVersion() + " (API version: " + DataModule.getBukkitAPI() + ")");
		getConsoleSender().sendMessage(ChatColor.AQUA + "| |    | |  \\___ \\| . ` |\\___ \\ " + ChatColor.WHITE + "  Java required: " + DataModule.getVersion(VersionType.JAVA));
		getConsoleSender().sendMessage(ChatColor.AQUA + "| |____| |  ____) | |\\  |____) |" + ChatColor.WHITE + "  Released on: " + DataModule.getVersionDate());
		getConsoleSender().sendMessage(ChatColor.AQUA + "|______|_| |_____/|_| \\_|_____/ ");
	}
	public final void info(final String message) {
		getConsoleSender().sendMessage(this.prefix + " " + ChatColor.GREEN + message);
	}
	public final void warning(final String message) {
		getConsoleSender().sendMessage(this.prefix + " " + ChatColor.YELLOW + message);
	}
	public final void severe(final String message) {
		getConsoleSender().sendMessage(this.prefix + " " + ChatColor.RED + message);
	}
}