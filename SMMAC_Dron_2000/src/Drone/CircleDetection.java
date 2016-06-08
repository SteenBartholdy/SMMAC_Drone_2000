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

		Imgproc.threshold(image, filterImage, 80, 255, Imgproc.THRESH_BINARY);
		Imgproc.cvtColor(filterImage, greyImage, Imgproc.COLOR_BGR2GRAY);
		Imgproc.GaussianBlur(greyImage, blurImage, new Size(3,3), 2,2);
		Imgproc.HoughCircles(blurImage, circleImage, Imgproc.CV_HOUGH_GRADIENT, 1, 1000, THRESHOLD, THRESHOLD*2, 50, 500);
		
		Point circleCentrum = getCircleCentrum(circleImage, image);
		
		if (circleCentrum != null) {
			Imgproc.arrowedLine(image, center, circleCentrum, new Scalar(233,121,255));
			vectorMovement(new Vector(center, circleCentrum), mov);
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
				Imgproc.circle(drawOnImg, pt, radius,new Scalar(0,255,0), 2);
				//Imgproc.circle(drawOnImg, pt, 3, new Scalar(0,0,255), 2);
			}
		}

		return pt;
	}
	

	public void vectorMovement(Vector v, Movement mv) {		
		if (v.length() < VECTOR_LENGTH) {
			//Forward
			mv.forward(25, 800);
			return;
		}
		
		if (v.getB().y > v.getA().y) {
			//Up
			mv.moveUp(25, 100);
		} else {
			//Down
			mv.moveDown(25, 100);
		}
		
		if (v.getB().x > v.getA().x) {
			//Left
			mv.goLeft(25, 100);
		} else {
			//Right
			mv.goRight(25, 100);
		}
	}
	
}
