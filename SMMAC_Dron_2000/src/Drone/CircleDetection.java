package Drone;

import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;

import Math.Circle;
import Math.Vector;

public class CircleDetection {

	private final Point center = new Point(320, 180);
	private final double THRESHOLD = 40;
	private final double VECTOR_LENGTH = 25;

	public boolean useCircleDetection(Mat image, Movement mov) {
		Mat filterImage = new Mat();
		Mat blurImage = new Mat();
		Mat greyImage = new Mat();
		Mat circleImage = new Mat();

		Imgproc.threshold(image, filterImage, 80, 255, Imgproc.THRESH_BINARY);
		Imgproc.cvtColor(filterImage, greyImage, Imgproc.COLOR_BGR2GRAY);
		Imgproc.GaussianBlur(greyImage, blurImage, new Size(3,3), 2,2);
		Imgproc.HoughCircles(blurImage, circleImage, Imgproc.CV_HOUGH_GRADIENT, 1, 1000, THRESHOLD, THRESHOLD*2, 15, 500);
		
		Circle circle = getCircle(circleImage);
		
		if (circle != null) {
			Imgproc.circle(image, circle.getCentrum(), circle.getRadius(),new Scalar(0,255,0), 2);
			Imgproc.arrowedLine(image, center, circle.getCentrum(), new Scalar(233,121,255));
			circleMovement(circle, mov);
			return true;
		} else {
			return false;
		}
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
	

	public void circleMovement(Circle c, Movement mv) {
		Vector v = new Vector(center, c.getCentrum());
		
		if (v.length() < VECTOR_LENGTH && c.getRadius() > 140) {
			mv.forward(25, 200);
			System.out.println("FREM");
			return;
		} else if (v.length() < VECTOR_LENGTH*1.5) {
			mv.forward(18, 50);
			System.out.println("LIDT FREM");
			return;
		}
		
		if (v.getB().y > v.getA().y) {
			mv.moveDown(18, 100);
			System.out.println("NED");
		} else {
			mv.moveUp(18, 100);
			System.out.println("OP");
		}
		
		if (v.getB().x > v.getA().x) {
			mv.goRight(18, 100);
			System.out.println("HØJRE");
		} else {
			mv.goLeft(18, 100);
			System.out.println("VENSTRE");
		}
	}
	
}
