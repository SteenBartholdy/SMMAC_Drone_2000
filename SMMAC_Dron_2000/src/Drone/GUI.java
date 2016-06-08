package Drone;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import org.opencv.core.Core;
import org.opencv.core.Mat;

import de.yadrone.base.IARDrone;
import de.yadrone.base.command.VideoChannel;
import de.yadrone.base.navdata.AttitudeListener;
import de.yadrone.base.navdata.BatteryListener;
import de.yadrone.base.video.ImageListener;

import Math.Counter;

public class GUI extends JFrame {

	private BufferedImage image = null;
	private BufferedImage processedImage = null;
	private String sBattery, sPitch, sRoll, sYaw, sWay, sQR;
	private Color backgroud = Color.WHITE;
	private Mat matImage = null;
	private Mat old_matImage = null;
	private ImageProcessor imageP = new ImageProcessor();
	private QRCode qr = new QRCode(6);
	private OpticalFlow op = new OpticalFlow();
	private Counter counter = new Counter();

	public GUI (final IARDrone drone)
	{
		super("SMMAC Drone 2000");

		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);

		setSize(1040,560);
		setBackground(backgroud);
		setVisible(true);

		drone.getCommandManager().setVideoCodecFps(15);

		drone.getVideoManager().addImageListener(new ImageListener() {
			public void imageUpdated(BufferedImage newImage)
			{	
				image = newImage;

				matImage = imageP.toMatImage(image);

				if(old_matImage == null)
				{
					old_matImage = matImage;
				}

				if(counter.ready())
				{ 
					imageP.useCircleDetection(matImage);
					sWay = "Direction: " + op.useOpticalFlow(old_matImage, matImage);
					sQR = "QR-code: " + qr.readQRCode(image);
				}			
				else {
					counter.count();
				}

				//Parameteren i toBufferedImage() skal v�re det sidst behandlede Mat objekt
				processedImage = (BufferedImage) imageP.toBufferedImage(matImage);

				//Gemmer billedet, så det kan bruges igen
				old_matImage = matImage;

				SwingUtilities.invokeLater(new Runnable() {
					public void run()
					{
						repaint();
					}
				});
			}
		});

		drone.getNavDataManager().addAttitudeListener(new AttitudeListener() {

			@Override
			public void attitudeUpdated(float pitch, float roll, float yaw) {
				sPitch = "Pitch: " + pitch;
				sRoll = "Roll: " + roll;
				sYaw = "Yaw: " + yaw;
				SwingUtilities.invokeLater(new Runnable() {
					public void run()
					{
						repaint();
					}
				});
			}

			@Override
			public void attitudeUpdated(float arg0, float arg1) { }

			@Override
			public void windCompensation(float arg0, float arg1) { }
		});	

		drone.getNavDataManager().addBatteryListener(new BatteryListener() {

			public void batteryLevelChanged(int percentage)
			{
				sBattery = "Battery: " + percentage + " %";
				SwingUtilities.invokeLater(new Runnable() {
					public void run()
					{
						repaint();
					}
				});
			}

			public void voltageChanged(int vbat_raw) { }
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

	public synchronized void paint(Graphics g)
	{
		if (processedImage == null) {
			g.drawString("LOADING", 500, 280);
		} else {
			g.drawImage(processedImage, 0, 0, 840, 560, null);

			g.setColor(backgroud);
			g.fillRect(840, 0, 200, 325);
			
			g.setColor(Color.BLACK);
			
			if(sBattery != null)
				g.drawString(sBattery, 865, 50);

			if(sPitch != null)
				g.drawString(sPitch, 865, 100);

			if(sRoll != null)
				g.drawString(sRoll, 865, 150);

			if(sYaw != null) 
				g.drawString(sYaw, 865, 200);

			if(sWay != null)
				g.drawString(sWay, 865, 250);

			if(sQR != null)
				g.drawString(sQR, 865, 300);
		}


	}
}

