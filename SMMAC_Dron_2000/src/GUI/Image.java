package GUI;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import org.opencv.core.Core;
import org.opencv.core.Mat;

import Drone.CircleDetection;
import Drone.ImageProcessor;
import Drone.Movement;
import Math.Counter;
import de.yadrone.base.video.ImageListener;

@SuppressWarnings("serial")
public class Image extends JFrame implements ImageListener {

	private BufferedImage img;
	private Mat mat, old;
	private ImageProcessor imgP;
	private Keys key;
	private Window win;
	private Mouse ms;
	private Counter counter;
	private Movement mv;
	private CircleDetection cd;
	
	public Image(Movement mov) {
		super("SMMAC Drone 2000");

		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		
		mv = mov;
		cd = new CircleDetection();
		imgP = new ImageProcessor();
		counter = new Counter(15);
		key = new Keys(mv);
		addKeyListener(key);
		win = new Window(mv);
		addWindowListener(win);
		ms = new Mouse(mv);
		addMouseListener(ms);
		
		setSize(840,560);
		setBackground(Color.WHITE);
		setVisible(true);
	}
	
	@Override
	public void imageUpdated(BufferedImage image) {
		img = image;
		
		mat = imgP.toMatImage(img);
		
		if (old == null)
			old = mat;
		
		if (key.isFlying()) {
			if(counter.ready())
			{ 	
				cd.useCircleDetection(mat, mv);
			}			
			else {
				counter.count();
			}
		}
		
		old = mat;
		img = (BufferedImage) imgP.toBufferedImage(mat);
		
		SwingUtilities.invokeLater(new Runnable() {
			public void run()
			{
				repaint();
			}
		});
	}
	
	public synchronized void paint(Graphics g)
	{
		if (img == null) {
			g.drawString("LOADING", 400, 280);
		} else {
			g.drawImage(img, 0, 0, 840, 560, null);
		}
	}

}