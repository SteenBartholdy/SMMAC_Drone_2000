package com.dtu.smmac.drone;

import com.dtu.smmac.data.Name;

import de.yadrone.base.command.CommandManager;
import de.yadrone.base.command.WifiMode;

public class Settings {

	private CommandManager cmd;
	
	public Settings(CommandManager cmd) {
		this.cmd = cmd;
	}
	
	public void setMAC(Name input) {
		String str = null;

		switch (input) {
		case MARTIN: 
			str = "a8:66:7f:03:c9:05";
			break;
		case ANDERS: 
			str = "a8:66:7f:03:c9:05"; 
			break;
		case CHRISTOFFER: 
			str = "80:e6:50:09:6b:90"; 
			break;
		case MADS: 
			str = "18:CF:5E:91:4B:A3"; 
			break; 
		case STEEN:
			str = "60:36:DD:15:E5:55"; 
			break;
		default:
			break;
		}

		this.cmd.setWifiMode(WifiMode.ADHOC);
		this.cmd.setSSIDSinglePlayer("SMMAC");
		this.cmd.setOwnerMac(str);
	}

	
}
