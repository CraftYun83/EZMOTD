package com.craftyun83.deathrun;

import org.bukkit.plugin.java.JavaPlugin;

import com.craftyun83.deathrun.commands.DeathRunCommand;

public class Main extends JavaPlugin{
	
	@Override
	public void onEnable() {
		new DeathRunCommand(this);
	}

}
