package com.dtu.smmac.gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.imgproc.Imgproc;

import com.dtu.smmac.drone.ImageProcessor;

import de.yadrone.base.video.ImageListener;

@SuppressWarnings("serial")
public class Image extends JFrame implements ImageListener {

	private BufferedImage img, w8;
	private ImageProcessor imgP;
	
	public Image() {
		super("SMMAC Drone 2000");

		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		
		imgP = new ImageProcessor();
		
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
	
	public Point getCentrum() {
		if (img == null)
			return null;
		
		return new Point(img.getWidth()/2, img.getHeight()/2);
	}
	
	public Mat getMatImg() {
		if (img == null)
			return null;
		
		Mat mat = imgP.toMatImage(img);
		Mat newMat = new Mat(); 
		
		Imgproc.undistort(mat, newMat, getCameraMat(), getDistCoeffs());
		
		return newMat;
	}
	
	public BufferedImage getImg() {
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

	public Mat getDistCoeffs()
	{
		Mat dCoef = new Mat(1, 4, 5);
		dCoef.put(0, 0, -0.5564);
		dCoef.put(0, 1, 0.3382);
		dCoef.put(0, 2, 0);
		dCoef.put(0, 3, 0);
		
		return dCoef;
	}
	
	public Mat getCameraMat() {
		Mat cMatrix = new Mat(3, 3, 5);
		cMatrix.put(0, 0, 1.1311e03);
		cMatrix.put(0, 1, 0.0);
		cMatrix.put(0, 2, 666.5773);
		cMatrix.put(1, 0, 0.0);
		cMatrix.put(1, 1, 1.1321e03);
		cMatrix.put(1, 2, 292.8758);
		cMatrix.put(2, 0, 0.0);
		cMatrix.put(2, 1, 0.0);
		cMatrix.put(2, 2, 1);
		
		return cMatrix;
	}
	
}
