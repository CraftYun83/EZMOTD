package com.craftyun83.deathrun.commands;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.WorldType;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.craftyun83.deathrun.Main;

public class DeathRunCommand implements CommandExecutor{
	
	@SuppressWarnings("unused")
	private Main plugin;
	private Location startLoc;
	private ArrayList<String> players = new ArrayList<String>();
	 public DeathRunCommand(Main plugin) {
		 this.plugin = plugin;
		 plugin.getCommand("deathrun").setExecutor(this);
	 }
	 
	 @Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage("¡±cThis command is only available for players!");
			return true;
		}
		Player p = (Player) sender;
		
		if (p.hasPermission("deathrun.use.*")) {
			try {
				if (cmd.getName().equalsIgnoreCase("deathrun")) {
					if (args[0].equalsIgnoreCase("setstart")) {
						try {
							startLoc = p.getLocation();
							p.sendMessage("Succesfully set start location at your location!");
						}
						catch ( Exception e) {
							
						}
					}
					if (args[0].equalsIgnoreCase("info")) {
						p.sendMessage("Start Location: "+startLoc);
					}
					if (args[0].equalsIgnoreCase("teleportStart")) {
						try {
							p.teleport(startLoc);
						}
						catch (Exception e) {
							p.sendMessage("You didn't set the start location yet!");
						}
					}
					if (args[0].equalsIgnoreCase("join")) {
						if (players.contains(p.getName())) {
							p.sendMessage("¡±eYou already joined the queue!");
						} else {
							p.sendMessage("¡±aYou have joined the queue!");
							players.add(p.getName());
							p.sendMessage("¡±aPlayers");
							p.sendMessage("¡±l¡±c----------");
							for (int i = 0; i <= 8; i++) {
								  try {
									  p.sendMessage("¡±e"+players.get(i));
									  
								  }
								  catch ( Exception e ){
									  break;
								  }
								}

						}
					}
					if (args[0].equalsIgnoreCase("author")) {
						p.sendMessage("¡±aThis DeathRun plugin was made by: ¡±cCraftYun83");
					}
					if (args[0].equalsIgnoreCase("subcommands")) {
						p.sendMessage("¡±aSubcommands:");
						p.sendMessage("¡±e---------------------");
						p.sendMessage("¡±e/deathrun info - gets the info you set");
						p.sendMessage("¡±e/deathrun setstart - sets the deathrun start area");
						p.sendMessage("¡±e/deathrun teleportstart - teleports you to start area");
						p.sendMessage("¡±e/deathrun join - join a game");
						p.sendMessage("¡±e/deathrun author - gets the author of the plugin (aka ME)");
						p.sendMessage("¡±e---------------------");
					}
					if (args[0].equalsIgnoreCase("createarena")) {
						try {
							WorldCreator wc = new WorldCreator(args[1]);
					        wc.type(WorldType.FLAT);
					        wc.generatorSettings("2;0;1;"); //This is what makes the world empty (void)
					        wc.createWorld();
					        p.sendMessage("Succesfully created world "+args[1]);
						}
						catch (Exception e){
							p.sendMessage("¡±cMissing 1 argument: WORLD_NAME");
						}
					if (args[0].equalsIgnoreCase("arenas")) {
						String[] worldNames = new String[Bukkit.getServer().getWorlds().size()];
						int count = 0;
						for(World w : Bukkit.getServer().getWorlds()){
							worldNames[count] = w.getName();
							count++;
						}
						for(String s : worldNames){
							p.sendMessage("World Names = " + s);
						}
					}
					}
				
				}
			}
			catch (Exception e){
				p.sendMessage("¡±cCorrect Usage: /deathrun <subcommand>");
			}
		}
		return false;
	}
}
