package GUI;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import Drone.Movement;

public class Window implements WindowListener {

	private Movement mv;
	
	public Window(Movement mov) {
		super();
		mv = mov;
	}

	@Override
	public void windowOpened(WindowEvent e) {
		
	}

	@Override
	public void windowClosing(WindowEvent e) {
		mv.landing();
		System.exit(0);
	}

	@Override
	public void windowClosed(WindowEvent e) {
		
	}

	@Override
	public void windowIconified(WindowEvent e) {
		
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		
	}

	@Override
	public void windowActivated(WindowEvent e) {
		
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		
	}
	
	
	
}
