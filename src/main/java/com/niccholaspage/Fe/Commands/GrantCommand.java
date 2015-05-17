package com.niccholaspage.Fe.Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import com.niccholaspage.Fe.Fe;
import com.niccholaspage.Fe.Phrases;
import com.niccholaspage.Fe.API.CommandType;
import com.niccholaspage.Fe.API.SubCommand;
import com.niccholaspage.Fe.API.Account;

public class GrantCommand extends SubCommand
{
	private final Fe plugin;
	public GrantCommand(Fe plugin)
	{
		super("grant", "fe.grant", "grant [name] [amount]", Phrases.COMMAND_GRANT, CommandType.CONSOLE);
		this.plugin = plugin;
	}
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args)
	{
		if(args.length < 2)
			return false;
		double money;
		try
		{
			money = Double.parseDouble(args[1]);
		} catch(NumberFormatException e)
		{
			return false;
		}
		Account victim = plugin.getShortenedAccount(args[0]);
		if(victim == null)
		{
			Phrases.ACCOUNT_DOES_NOT_EXIST.sendWithPrefix(sender);
			return true;
		}
		if(!victim.canReceive(money))
		{
			Phrases.MAX_BALANCE_REACHED.sendWithPrefix(sender, victim.getName());
			return true;
		}
		String formattedMoney = plugin.getAPI().format(money);
		victim.deposit(money);
		Phrases.PLAYER_GRANT_MONEY.sendWithPrefix(sender, formattedMoney, victim.getName());
		Player receiverPlayer = plugin.getServer().getPlayerExact(victim.getName());
		if(receiverPlayer != null)
			Phrases.PLAYER_GRANTED_MONEY.sendWithPrefix(receiverPlayer, formattedMoney, sender.getName());
		return true;
	}
}