package GUI;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import Drone.Movement;

public class Keys implements KeyListener {

	private Movement mv;
	private boolean moving;
	
	public Keys (Movement mov) {
		super();
		mv = mov;
		moving = false;
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
			mv.emergencyLanding();
			moving = false;
		} else if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			mv.takeoff();
			moving = true;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		
	}

}
