package Test;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

import Drone.ImageProcessor;

public class FindCirkler {

	private Mat matImage = null;
	private Mat old_matImage = null;
	private Mat canneyOutput = null;
	private Mat blurImage = null;
	private Mat contourOutput = null;
	private Mat greyImage = null;
	private Mat circleImage = null;
	private ImageProcessor imageP = new ImageProcessor();
	private int tresh = 80;
	private int count = 0;

	public void run(){

		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		
		matImage = new Mat();
		old_matImage = new Mat();
		greyImage = new Mat();
		blurImage = new Mat();
		contourOutput = new Mat();
		canneyOutput = new Mat();
		circleImage = new Mat();
		
		Mat matImage = Imgcodecs.imread("/Users/cirkel.png");
		
		Imgproc.GaussianBlur(matImage, blurImage, new Size(5,5), 0);
		System.out.println("Blur "  + blurImage);


		Imgproc.cvtColor(blurImage, greyImage, Imgproc.COLOR_BGR2GRAY);

		Imgproc.Canny(greyImage, canneyOutput, tresh, tresh*2);
		Imgproc.HoughCircles(canneyOutput, circleImage, Imgproc.CV_HOUGH_GRADIENT, 1, 30, 200, 50, 0, 0 );
		circleImage.cols();
		for(int i = 0; i < circleImage.cols(); i++)
		{
			double vCircle[] = circleImage.get(0, i);

			if(vCircle == null)
			{
				break;
			}

			Point pt = new Point(Math.round(vCircle[0]), Math.round(vCircle[1]));
			int radius = (int)Math.round(vCircle[2]);
			System.out.println("point " + pt.toString());
			//Mangler at kunne tegne selve cirklen
			Imgproc.circle(canneyOutput, pt, radius,new Scalar(0,255,0), 5);
			Imgproc.circle(canneyOutput, pt, 3, new Scalar(0,0,255), 2);
		}
		
		String filename = "/Users/cirkel1.png";
		System.out.println("Done. Writing " + filename);
		Imgcodecs.imwrite(filename, canneyOutput);

	}
}
