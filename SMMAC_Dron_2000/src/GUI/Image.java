package GUI;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import org.opencv.core.Core;
import org.opencv.core.Point;

import de.yadrone.base.video.ImageListener;

@SuppressWarnings("serial")
public class Image extends JFrame implements ImageListener {

	private BufferedImage img, w8;
	
	public Image() {
		super("SMMAC Drone 2000");

		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		
		try {
			w8 = ImageIO.read(new File("images/loading.jpg"));
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		
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
			g.drawImage(w8, 0, 0, 840, 560, null);
		} else {
			g.drawImage(img, 0, 0, 840, 560, null);
		}
	}

}
