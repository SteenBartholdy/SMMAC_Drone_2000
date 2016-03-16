package Drone;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;

import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.videoio.VideoCapture;

import de.yadrone.base.IARDrone;
import de.yadrone.base.command.VideoChannel;
import de.yadrone.base.video.ImageListener;

public class Video extends JFrame {

	private BufferedImage image = null;
	private Mat matImage = null;
	private JLabel label = null;
	private VideoCapture vc;

	public Video (final IARDrone drone)
	{
		super("SMMAC Drone 2000");

		setSize(640,360);
//		label = new JLabel();
//		add(label);
		setVisible(true);
		

		drone.getVideoManager().addImageListener(new ImageListener() {
			public void imageUpdated(BufferedImage newImage)
			{
				image = newImage;
//				matImage = bufferedImageToMat(image);
//				vc = new VideoCapture(0);
				
				SwingUtilities.invokeLater(new Runnable() {
					public void run()
					{
//						vc.read(matImage);
//						imagePainter();
						repaint();
					}
				});
			}
		});

		addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e)
			{
				drone.getCommandManager().setVideoChannel(VideoChannel.NEXT);
			}
		});

		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) 
			{
				drone.getCommandManager().landing();
				drone.stop();
				System.exit(0);
			}
		});
	}
	
	public void imagePainter()
	{
		ImageProcessor imageP = new ImageProcessor();
		Image temp = imageP.toBufferedImage(matImage);
		ImageIcon imageIcon = new ImageIcon(temp, "LOOOL");
		label.setIcon(imageIcon);
		pack();
	}

	public synchronized void paint(Graphics g)
	{
		if (image != null)
			g.drawImage(image, 0, 0, image.getWidth(), image.getHeight(), null);
	}

	public static Mat bufferedImageToMat(BufferedImage buffImage) {
		Mat mat = new Mat(buffImage.getHeight(), buffImage.getWidth(), CvType.CV_8UC3);
		byte[] data = ((DataBufferByte) buffImage.getRaster().getDataBuffer()).getData();
		mat.put(0, 0, data);
		return mat;
	}
}
