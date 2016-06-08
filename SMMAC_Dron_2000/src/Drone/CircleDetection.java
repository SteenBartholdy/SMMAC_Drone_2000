package Drone;

import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;

import Math.Vector;

public class CircleDetection {

	private final Point center = new Point(320, 180);
	private final double THRESHOLD = 40;
	private final double VECTOR_LENGTH = 35;

	public void useCircleDetection(Mat image, Movement mov) {
		Mat filterImage = new Mat();
		Mat blurImage = new Mat();
		Mat greyImage = new Mat();
		Mat circleImage = new Mat();

		Imgproc.threshold(image, filterImage, 50, 255, Imgproc.THRESH_BINARY);
		Imgproc.cvtColor(filterImage, greyImage, Imgproc.COLOR_BGR2GRAY);
		Imgproc.GaussianBlur(greyImage, blurImage, new Size(3,3), 2,2);
		Imgproc.HoughCircles(blurImage, circleImage, Imgproc.CV_HOUGH_GRADIENT, 1, 1000, THRESHOLD, THRESHOLD*2, 80, 500);
		
		Point circleCentrum = getCircleCentrum(circleImage, image);
		
		if (circleCentrum != null) {
			Imgproc.arrowedLine(image, center, circleCentrum, new Scalar(233,121,255));
			VectorMovement(new Vector(center, circleCentrum), mov);
		}
	}

	public Point getCircleCentrum(Mat circleImage, Mat drawOnImg) {
		Point pt = null;

		for(int i = 0; i < circleImage.cols(); i++)
		{
			double vCircle[] = circleImage.get(0, i);

			if(vCircle != null)
			{
				pt = new Point(Math.round(vCircle[0]), Math.round(vCircle[1]));
				int radius = (int)Math.round(vCircle[2]);
				Imgproc.circle(drawOnImg, pt, radius,new Scalar(0,255,0), 5);
				//Imgproc.circle(drawOnImg, pt, 3, new Scalar(0,0,255), 2);
			}
		}

		return pt;
	}
	

	public void VectorMovement(Vector v, Movement mov) {		
		if (v.length() < VECTOR_LENGTH) {
			//Forward
			return;
		}
		
		if (v.getB().y > v.getA().y) {
			//Up
		} else {
			//Down
		}
		
		if (v.getB().x > v.getA().x) {
			//Left
		} else {
			//Right
		}
	}
	
}
