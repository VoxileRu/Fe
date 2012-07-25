package org.melonbrew.fe.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

public abstract class SubCommand {
	private final String name;
	
	private final String permission;
	
	private final String usage;
	
	private final String description;
	
	private final CommandType commandType;
	
	public SubCommand(String name, String permission, String usage, String description, CommandType commandType){
		this.name = name;
		
		this.permission = permission;
		
		this.usage = usage;
		
		this.description = description;
		
		this.commandType = commandType;
	}
	
	public String getFirstName(){
		return name.split(",")[0];
	}
	
	public String getName(){
		return name;
	}
	
	public String getPermission(){
		return permission;
	}
	
	public String getUsage(){
		return usage;
	}
	
	public String getDescription(){
		return description;
	}
	
	public CommandType getCommandType(){
		return commandType;
	}
	
	public abstract boolean onCommand(CommandSender sender, Command cmd, String[] args);
}