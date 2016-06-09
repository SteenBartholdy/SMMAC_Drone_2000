package Drone;

import Data.Name;
import GUI.Image;
import de.yadrone.base.ARDrone;
import de.yadrone.base.IARDrone;

public class Emulator {

	public static void main(String[] args)
	{
		IARDrone drone = new ARDrone();
		
//		Settings settings = new Settings(drone.getCommandManager());
//		settings.setMAC(Name.ANDERS);
		
		Movement mov = new Movement(drone.getCommandManager());
		drone.getCommandManager().setVideoCodecFps(15);
		drone.getVideoManager().addImageListener(new Image(mov));
	}

}
