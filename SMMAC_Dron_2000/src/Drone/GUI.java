package Drone;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.GrayFilter;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfByte;
import org.opencv.core.MatOfFloat;
import org.opencv.core.MatOfPoint;
import org.opencv.core.MatOfPoint2f;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.video.Video;

import de.yadrone.base.IARDrone;
import de.yadrone.base.command.VideoChannel;
import de.yadrone.base.navdata.AttitudeListener;
import de.yadrone.base.navdata.BatteryListener;
import de.yadrone.base.video.ImageListener;

public class GUI extends JFrame {

	private BufferedImage image = null;
	private BufferedImage processedImage = null;
	private String sBattery;
	private String sPitch;
	private String sRoll;
	private String sYaw;
	private Color backgroud = Color.WHITE;
	private Mat matImage = null;
	private Mat old_matImage = null;
	private Mat blurImage = null;
	private Mat greyImage = null;
	private Mat circleImage = null;
	private ImageProcessor imageP = new ImageProcessor();
	private int tresh = 40;
	private int count = 0;
	private OpticalFlow op = new OpticalFlow();

	public GUI (final IARDrone drone)
	{
		super("SMMAC Drone 2000");

		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);

		setSize(1040,560);
		setBackground(backgroud);
		setVisible(true);

		drone.getVideoManager().addImageListener(new ImageListener() {
			public void imageUpdated(BufferedImage newImage)
			{
				image = newImage;
				matImage = imageP.toMatImage(image);
				
				greyImage = new Mat();
				blurImage = new Mat();
				circleImage = new Mat();

				if(old_matImage == null)
				{
					old_matImage = matImage;
				}
				
				op.useOpticalFlow(old_matImage, matImage);

				//K�r en while l�kke med to if statements. N�r der fx er g�et et sekundt
				//skal cvtColor canney og find cirkler k�rer
				//Optical flow algoritmen skal k�re meget hurtigere og kun m�ske med GaussioanBlur
				//eller bare p� r�t matImage

				if(count == 0)
				{
					Imgproc.cvtColor(matImage, greyImage, Imgproc.COLOR_BGR2GRAY);
					Imgproc.GaussianBlur(greyImage, blurImage, new Size(3,3), 2,2);
					Imgproc.HoughCircles(blurImage, circleImage, Imgproc.CV_HOUGH_GRADIENT, 1, 500, tresh, tresh*2, 50, 500);
					//System.out.println(circleImage.toString());
					for(int i = 0; i < circleImage.cols(); i++)
					{
						double vCircle[] = circleImage.get(0, i);

						System.out.println(circleImage.toString());
						if(vCircle == null)
						{
							break;
						}

						Point pt = new Point(Math.round(vCircle[0]), Math.round(vCircle[1]));
						int radius = (int)Math.round(vCircle[2]);
						System.out.println("point " + pt.toString());
						Imgproc.circle(matImage, pt, radius,new Scalar(0,255,0), 5);
						Imgproc.circle(matImage, pt, 3, new Scalar(0,0,255), 2);
					}
				}

				if(count < 10)
				{
					count++;
				}
				else
				{
					count = 0;
				}

				System.out.println("Count er " + count);
				
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
		if (processedImage  != null)
		{

			g.drawImage(processedImage, 0, 0, 840, 560, null);

		}


		g.setColor(backgroud);
		g.fillRect(840, 0, 150, 225);

		g.setColor(Color.BLACK);

		if(sBattery != null)
			g.drawString(sBattery, 865, 50);

		if(sPitch != null)
			g.drawString(sPitch, 865, 100);

		if(sRoll != null)
			g.drawString(sRoll, 865, 150);

		if(sYaw != null) 
			g.drawString(sYaw, 865, 200);
	}



}

