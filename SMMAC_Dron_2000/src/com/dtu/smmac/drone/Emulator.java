package com.dtu.smmac.drone;

import com.dtu.smmac.data.Name;
import com.dtu.smmac.gui.Image;
import com.dtu.smmac.gui.Keys;
import com.dtu.smmac.gui.Mouse;
import com.dtu.smmac.gui.Window;

import de.yadrone.base.ARDrone;
import de.yadrone.base.IARDrone;

public class Emulator {

	private Runnable imgAnalyze, takePhoto, setSetting, threshold;
	private Movement mov;
	private Image img;
	private ImageProcessor pro;
	private Settings settings;
	private TakePicture pic;
	private Keys key;
//	private Altitude alt;
	private Name name;

	public void run(Name input, boolean imageAnalyzing, boolean takePhotos, boolean setSettings) {
		name = input;

		setObjects();
		setRunnables();

		new Thread(threshold).start();

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
//		alt = new Altitude();

		key = new Keys(mov, pro);
		img.addKeyListener(key);
		Window win = new Window();
		img.addWindowListener(win);
		Mouse ms = new Mouse(mov);
		img.addMouseListener(ms);

		drone.getVideoManager().addImageListener(img);
		//drone.getNavDataManager().addAltitudeListener(alt);
	}

	public void setRunnables() {
		imgAnalyze = new Runnable() {

			@Override
			public void run() {
				while (true) {
					try {
						Thread.sleep(2500);
						pro.start(img.getMatImg(), img.getCentrum(), mov, key.isFlying(), img);
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
						pic.savePicture(name, "" + i++, img.getMatImg());
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

		threshold = new Runnable() {

			@Override
			public void run() {
				while(true) {
					try {
						Thread.sleep(10);
						img.setThresholdImage();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}

		};
	}

}
