package com.dtu.smmac.gui;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import com.dtu.smmac.drone.ImageProcessor;
import com.dtu.smmac.drone.Movement;

public class Keys implements KeyListener {

	private Movement mv;
	private boolean moving;
	private Runnable takeoff;
	private ImageProcessor imgP;

	public Keys (Movement mov, ImageProcessor pro) {
		super();
		mv = mov;
		imgP = pro;
		moving = false;

		takeoff = new Runnable() {

			@Override
			public void run() {
				try {
					imgP.setFlyThrough(false);
					mv.takeoff();
					Thread.sleep(5500);
					moving = true;
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}

		};
	}

	public boolean isFlying() {
		return moving;
	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
			moving = false;
			mv.landing();
		} else if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			new Thread(takeoff).start();
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		mv.hover();
	}

}
