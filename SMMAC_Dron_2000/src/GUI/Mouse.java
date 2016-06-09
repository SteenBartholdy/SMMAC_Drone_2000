package GUI;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import Drone.Movement;
import de.yadrone.base.command.VideoChannel;

public class Mouse implements MouseListener {

	private Movement mv;
	
	public Mouse(Movement mov) {
		super();
		mv = mov;
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		mv.getCmd().setVideoChannel(VideoChannel.NEXT);
	}

	@Override
	public void mousePressed(MouseEvent e) {
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		
	}

}
