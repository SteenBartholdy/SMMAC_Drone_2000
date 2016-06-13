package Drone;

import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;

import Math.Circle;

public class CircleDetection {

	private final double THRESHOLD = 40;

	public Circle useCircleDetection(Mat image) {
		Mat filterImage = new Mat();
		Mat blurImage = new Mat();
		Mat greyImage = new Mat();
		Mat circleImage = new Mat();

		Imgproc.threshold(image, filterImage, 80, 255, Imgproc.THRESH_BINARY);
		Imgproc.cvtColor(filterImage, greyImage, Imgproc.COLOR_BGR2GRAY);
		Imgproc.GaussianBlur(greyImage, blurImage, new Size(3,3), 2,2);
		Imgproc.HoughCircles(blurImage, circleImage, Imgproc.CV_HOUGH_GRADIENT, 1, 1000, THRESHOLD, THRESHOLD*2, 15, 500);
		
		return getCircle(circleImage);
	}

	public Circle getCircle(Mat circleImage) {
		Circle circle = null;

		for(int i = 0; i < circleImage.cols(); i++)
		{
			double vCircle[] = circleImage.get(0, i);

			if(vCircle != null)
			{
				circle = new Circle(new Point(Math.round(vCircle[0]), Math.round(vCircle[1])), (int)Math.round(vCircle[2]));
			}
		}

		return circle;
	}
}
