package Drone;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import org.opencv.core.Core;
import org.opencv.core.Mat;

import Math.Counter;
import de.yadrone.base.command.VideoChannel;
import de.yadrone.base.navdata.Altitude;
import de.yadrone.base.navdata.AltitudeListener;
import de.yadrone.base.navdata.BatteryListener;
import de.yadrone.base.video.ImageListener;

public class GUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private BufferedImage image = null;
	private BufferedImage processedImage = null;
	private String sBattery, sWay, sQR;
	private Color backgroud = Color.WHITE;
	private Mat matImage = null;
	private Mat old_matImage = null;
	private ImageProcessor imageP = new ImageProcessor();
	private QRCode qr = new QRCode(6);
	private OpticalFlow op = new OpticalFlow();
	private CircleDetection cd = new CircleDetection();
	private Counter counter = new Counter(15);
	private boolean takeoff = false;

	public GUI (Movement mov)
	{
		super("SMMAC Drone 2000");

		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);

		setSize(840,560);
		setBackground(backgroud);
		setVisible(true);

		mov.getCmd().setVideoCodecFps(15);

		mov.getDrone().getVideoManager().addImageListener(new ImageListener() {
			public void imageUpdated(BufferedImage newImage)
			{	
				image = newImage;

				matImage = imageP.toMatImage(image);

				if(old_matImage == null)
				{
					old_matImage = matImage;
				}

				if (takeoff) {
					if(counter.ready())
					{ 	
						//sWay = "Direction: " + op.useOpticalFlow(old_matImage, matImage);
						//sQR = "QR-code: " + qr.readQRCode(image);
						cd.useCircleDetection(matImage, mov);
					}			
					else {
						counter.count();
					}
				}

				//Gemmer billedet, så det kan bruges igen
				old_matImage = matImage;

				//Parameteren i toBufferedImage() skal v�re det sidst behandlede Mat objekt
				processedImage = (BufferedImage) imageP.toBufferedImage(matImage);

				SwingUtilities.invokeLater(new Runnable() {
					public void run()
					{
						repaint();
					}
				});
			}
		});

		//		mov.getDrone().getNavDataManager().addBatteryListener(new BatteryListener() {
		//
		//			public void batteryLevelChanged(int percentage)
		//			{
		//				sBattery = "Battery: " + percentage + " %";
		//				
		//				SwingUtilities.invokeLater(new Runnable() {
		//					public void run()
		//					{
		//						repaint();
		//					}
		//				});
		//			}
		//
		//			public void voltageChanged(int vbat_raw) { }
		//		});

		addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e)
			{
				mov.getCmd().setVideoChannel(VideoChannel.NEXT);
			}
		});

		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) 
			{
				mov.emergencyLanding();
				System.exit(0);
			}
		});

		addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {

			}

			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
					mov.emergencyLanding();
					takeoff = false;
				} else if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					mov.takeoff();
					takeoff = true;
				}
			}

			@Override
			public void keyReleased(KeyEvent e) {

			}
		});

	}

	public synchronized void paint(Graphics g)
	{
		if (processedImage == null) {
			g.drawString("LOADING", 400, 280);
		} else {
			g.drawImage(processedImage, 0, 0, 840, 560, null);
		}
	}
}

