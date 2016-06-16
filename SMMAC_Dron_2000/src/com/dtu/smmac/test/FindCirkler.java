package com.dtu.smmac.test;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

import com.dtu.smmac.drone.ImageProcessor;

public class FindCirkler {

	private Mat matImage = null;
	private Mat old_matImage = null;
	private Mat canneyOutput = null;
	private Mat blurImage = null;
	private Mat contourOutput = null;
	private Mat greyImage = null;
	private Mat circleImage = null;
	private ImageProcessor imageP = new ImageProcessor();
	private int tresh = 40;
	private int count = 0;
	private long starttime, endtime, duration;

	public void run(){

		
		
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		
		matImage = new Mat();
		old_matImage = new Mat();
		greyImage = new Mat();
		blurImage = new Mat();
		contourOutput = new Mat();
		canneyOutput = new Mat();
		circleImage = new Mat();
		
		Mat matImage = Imgcodecs.imread("/Users/Steen Bartholdy/hullahop.png");
		
		starttime = System.currentTimeMillis();
		
		//Imgproc.blur(matImage, blurImage, new Size(3,3));
		System.out.println("Blur "  + blurImage);


		Imgproc.cvtColor(matImage, greyImage, Imgproc.COLOR_BGR2GRAY);
		Imgproc.GaussianBlur(greyImage, blurImage, new Size(3,3), 2,2);
		
		//Imgproc.Canny(greyImage, canneyOutput, tresh, tresh*2);
		Imgproc.HoughCircles(blurImage, circleImage, Imgproc.CV_HOUGH_GRADIENT, 1, 500, tresh, tresh*2, 100, 500);
		System.out.println(circleImage.toString());
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
			//Mangler at kunne tegne selve cirklen
			Imgproc.circle(matImage, pt, radius,new Scalar(0,255,0), 5);
			Imgproc.circle(matImage, pt, 3, new Scalar(0,0,255), 2);
		}
		
		String filename = "/Users/Steen Bartholdy/hullahop1.png";
		Imgcodecs.imwrite(filename, matImage);
		endtime = System.currentTimeMillis();
		System.out.println(endtime - starttime); 
		System.out.println("Done. Writing " + filename);
		

	}
}
