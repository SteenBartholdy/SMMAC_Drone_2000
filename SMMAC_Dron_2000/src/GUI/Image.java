package GUI;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import org.opencv.core.Core;

import de.yadrone.base.video.ImageListener;

@SuppressWarnings("serial")
public class Image extends JFrame implements ImageListener {

	private BufferedImage img;
	
	public Image() {
		super("SMMAC Drone 2000");

		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		
		setSize(840,560);
		setBackground(Color.WHITE);
		setVisible(true);
	}
	
	@Override
	public void imageUpdated(BufferedImage image) {
		img = image;
		
		SwingUtilities.invokeLater(new Runnable() {
			public void run()
			{
				repaint();
			}
		});
	}
	
	public BufferedImage getImage() {
		return img;
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
