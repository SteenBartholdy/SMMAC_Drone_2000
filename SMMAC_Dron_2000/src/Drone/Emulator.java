package Drone;

import Data.Name;
import GUI.Image;
import GUI.Keys;
import GUI.Mouse;
import GUI.Window;
import de.yadrone.base.ARDrone;
import de.yadrone.base.IARDrone;

public class Emulator {

	private Runnable imgAnalyze, takePhoto, setSetting;
	private Movement mov;
	private Image img;
	private ImageProcessor pro;
	private Settings settings;
	private TakePicture pic;
	private Keys key;
	private Name name;

	public void run(Name input, boolean imageAnalyzing, boolean takePhotos, boolean setSettings) {
		name = input;

		setObjects();
		setRunnables();

		if (setSettings) 
			new Thread(setSetting).start();

		if (takePhotos)
			new Thread(takePhoto).start();

		if (imageAnalyzing)
			new Thread(imgAnalyze).start();

	}

	public void setObjects() {
		IARDrone drone = new ARDrone();

		settings = new Settings(drone.getCommandManager());	
		mov = new Movement(drone.getCommandManager());

		img = new Image();
		pro = new ImageProcessor();
		pic = new TakePicture();

		key = new Keys(mov);
		img.addKeyListener(key);
		Window win = new Window();
		img.addWindowListener(win);
		Mouse ms = new Mouse(mov);
		img.addMouseListener(ms);

		drone.getVideoManager().addImageListener(img);
	}

	public void setRunnables() {
		imgAnalyze = new Runnable() {

			@Override
			public void run() {

				while (true) {
					try {
						Thread.sleep(1500);
						pro.start(img.getImage(), mov, key.isFlying());
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				} 
			}

		};

		takePhoto = new Runnable() {

			@Override
			public void run() {
				int i = 0;

				while (true) {
					try {
						Thread.sleep(500);
						pic.savePicture(name, "" + i++, img.getImage());
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}

		};

		setSetting = new Runnable() {

			@Override
			public void run() {
				settings.setMAC(name);
			}

		};
	}

}
