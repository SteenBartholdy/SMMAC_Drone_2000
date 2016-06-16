package com.dtu.smmac.main;

import com.dtu.smmac.data.Name;
import com.dtu.smmac.drone.Emulator;

public class SMMAC {

	public static void main(String[] args) {
		new Emulator().run(Name.MARTIN, true, false, false);
	}
	
}
