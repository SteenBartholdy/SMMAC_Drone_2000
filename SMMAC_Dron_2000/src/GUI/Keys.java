package GUI;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import Drone.Movement;

public class Keys implements KeyListener {

	private Movement mv;
	private boolean moving;
	private Runnable takeoff;

	public Keys (Movement mov) {
		super();
		mv = mov;
		moving = false;

		takeoff = new Runnable() {

			@Override
			public void run() {
				try {
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
