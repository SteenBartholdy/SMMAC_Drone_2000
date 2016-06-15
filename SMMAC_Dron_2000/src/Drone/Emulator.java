package Drone;

import Data.Name;
import GUI.Image;
import GUI.Keys;
import GUI.Mouse;
import GUI.Window;
import Math.Counter;
import de.yadrone.base.ARDrone;
import de.yadrone.base.IARDrone;

public class Emulator {

	public static void main(String[] args)
	{
		IARDrone drone = new ARDrone();

		//		Settings settings = new Settings(drone.getCommandManager());
		//		settings.setMAC(Name.ANDERS);

		Movement mov = new Movement(drone.getCommandManager());
		Image img = new Image();
		ImageProcessor pro = new ImageProcessor();

		Keys key = new Keys(mov);
		img.addKeyListener(key);
		Window win = new Window();
		img.addWindowListener(win);
		Mouse ms = new Mouse(mov);
		img.addMouseListener(ms);

		drone.getCommandManager().setVideoCodecFps(30);
		drone.getVideoManager().addImageListener(img);

		while (true) {
			try {
				Thread.sleep(1000);
				pro.start(img.getImage(), mov, key.isFlying());
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}
}
