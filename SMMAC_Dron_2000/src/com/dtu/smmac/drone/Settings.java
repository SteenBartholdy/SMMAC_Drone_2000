package com.dtu.smmac.drone;

import de.yadrone.base.command.CommandManager;
import de.yadrone.base.command.WifiMode;

public class Settings {

	private CommandManager cmd;
	
	public Settings(CommandManager cmd) {
		this.cmd = cmd;
	}
	
	public void setMAC(String str) {
		this.cmd.setWifiMode(WifiMode.ADHOC);
		this.cmd.setSSIDSinglePlayer("SMMAC");
		this.cmd.setOwnerMac(str);
	}

	
}
