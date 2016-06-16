package com.dtu.smmac.test;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

public class KunSort {
	
	

	public static void main(String[] args) {
		run();
	}

	public static void run()
	{
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);

		final double THRESHOLD = 40;


		Mat matImage = Imgcodecs.imread("/Users/Steen Bartholdy/hullahop.png");
		Mat imaage = new Mat();
		Mat circleImage = new Mat();

		Imgproc.threshold(matImage, imaage, 10, 255, Imgproc.THRESH_BINARY);
		Imgproc.cvtColor(imaage, imaage, Imgproc.COLOR_BGR2GRAY);
		Imgproc.GaussianBlur(imaage, imaage, new Size(3,3), 2,2);
		Imgproc.HoughCircles(imaage, circleImage, Imgproc.CV_HOUGH_GRADIENT, 1, 1000, THRESHOLD, THRESHOLD*2, 80, 500);

		for(int i = 0; i < circleImage.cols(); i++)
		{
			double vCircle[] = circleImage.get(0, i);

			if(vCircle == null)
			{
				break;
			}

			Point pt = new Point(Math.round(vCircle[0]), Math.round(vCircle[1]));
			int radius = (int)Math.round(vCircle[2]);
			Imgproc.circle(matImage, pt, radius,new Scalar(0,255,0), 5);
			Imgproc.circle(matImage, pt, 3, new Scalar(0,0,255), 2);
			System.out.println("Centrum af den fundne cirkel er: " + pt);
		}
		
		
		String filename = "/Users/Steen Bartholdy/hullahop1.png";
		Imgcodecs.imwrite(filename, matImage);
//		Imgcodecs.imwrite(filename, imaage);
		System.out.println("Done. Writing " + filename);
	}
}
