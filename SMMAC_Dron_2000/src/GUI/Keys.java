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
			mv.landing();
			moving = false;
		} else if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			mv.takeoff();
		}
		else if (e.getKeyCode() == KeyEvent.VK_SHIFT) {
			moving = true;
		}
		else if(e.getKeyCode() == KeyEvent.VK_Q)
		{
			mv.spinLeftCorrection();
		}
		else if(e.getKeyCode() == KeyEvent.VK_E)
		{
			mv.spinRightCorrection();
		}
		else if(e.getKeyCode() == KeyEvent.VK_W)
		{
			mv.upCorrection();
		}
		else if(e.getKeyCode() == KeyEvent.VK_S)
		{
			mv.downCorrection();
		}
		else if(e.getKeyCode() == KeyEvent.VK_UP)
		{
			mv.forwardCorrection();
		}
		else if(e.getKeyCode() == KeyEvent.VK_DOWN)
		{
			mv.backwardCorrection();
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		mv.hover();
	}

}
