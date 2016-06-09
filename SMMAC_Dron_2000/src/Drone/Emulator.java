package Drone;

import Data.Name;

public class Emulator {

	public static void main(String[] args)
	{
		Movement mov = new Movement();
		//mov.setMAC(Name.MARTIN);
		new GUI(mov);
	}

}
