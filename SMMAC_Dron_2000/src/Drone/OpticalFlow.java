package Drone;

import java.util.ArrayList;
import java.util.List;

import org.opencv.core.Mat;
import org.opencv.core.MatOfByte;
import org.opencv.core.MatOfFloat;
import org.opencv.core.MatOfPoint;
import org.opencv.core.MatOfPoint2f;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;
import org.opencv.video.Video;

import Math.Vector;

public class OpticalFlow {

	private List<Vector> vectorList = new ArrayList<Vector>();;
	private final double THRESHOLD = 40;
	private final double NOISE_X = 0.8;
	private final double NOISE_Y = 1.2;
	
	public void useOpticalFlow (Mat imagePrev, Mat imageNext) {
		vectorList.clear();
		
		Mat processedImagePrev = new Mat();
		Mat processedImageNext = new Mat();
		MatOfByte status = new MatOfByte();
		MatOfFloat err = new MatOfFloat();
		MatOfPoint pointsPrev = new MatOfPoint();
		
		Imgproc.cvtColor(imagePrev, processedImagePrev, Imgproc.COLOR_BGR2GRAY);
		Imgproc.Canny(processedImagePrev, processedImagePrev, THRESHOLD, THRESHOLD*2);
		Imgproc.goodFeaturesToTrack(processedImagePrev, pointsPrev, 100, 0.1, 1);
		
		Imgproc.cvtColor(imageNext, processedImageNext, Imgproc.COLOR_BGR2GRAY);
		Imgproc.Canny(processedImageNext, processedImageNext, THRESHOLD, THRESHOLD*2);
		
		MatOfPoint2f c1 = new MatOfPoint2f(pointsPrev.toArray());
		MatOfPoint2f c2 = new MatOfPoint2f();
		Video.calcOpticalFlowPyrLK(processedImagePrev, processedImageNext, c1, c2, status, err);

		setVectors(c1, c2, status);
		
		removeNoise();
		
		//drawVectors(imageNext);
		
		System.out.println(getMovement(avgVector()));
		//Imgproc.line(imageNext, v.getA(), v.getB(), new Scalar(233,121,255), 2);
	}
	
	public void setVectors(MatOfPoint2f prev, MatOfPoint2f next, MatOfByte status) {
		for (int i = 0; i < status.rows(); i++) {
			
			int statusInt = (int) status.get(i, 0)[0];
			
			if (statusInt == 1) {
				double[] cornerPoints1 = prev.get(i, 0);
				double[] cornerPoints2 = next.get(i, 0);
				
				Point a = new Point(cornerPoints1[0], cornerPoints1[1]);
				Point b = new Point(cornerPoints2[0], cornerPoints2[1]);
				
				vectorList.add(new Vector(a, b));
			}
		}
	}
	
	public void drawVectors(Mat img) {
		for (Vector v : vectorList) {
			Imgproc.line(img, v.getA(), v.getB(), new Scalar(233,121,255), 2);
		}
	}
	
	public double averageVectorLength() {
		double avg = 0;
		
		for (Vector v : vectorList) {
			avg += v.length();
		}
		
		return avg /= vectorList.size();
	}
	
	public void removeNoise() {
		ArrayList<Vector> list = new ArrayList<Vector>();
		double avg = averageVectorLength();
		
		for (Vector v : vectorList) {
			if (v.length() >= avg * NOISE_X && v.length() <= avg * NOISE_Y)
				list.add(v);
		}
		
		vectorList = list;
	}
	
	public Vector avgVector() {
		double ax = 0, ay = 0, bx = 0, by = 0;
		
		for (Vector v : vectorList) {
			ax += v.getA().x;
			ay += v.getA().y;
			bx += v.getB().x;
			by += v.getB().y;
		}
		
		ax /= vectorList.size();
		ay /= vectorList.size();
		bx /= vectorList.size();
		by /= vectorList.size();
		
		return new Vector(new Point(ax, ay), new Point(bx, by));
	}
	
	public String getMovement(Vector v) {
		String str = "";
		
		if (v.getB().y > v.getA().y) {
			str = "Op ";
		} else {
			str = "Ned ";
		}
		
		if (v.getB().x > v.getA().x) {
			str += "Højre";
		} else {
			str += "Venstre";
		}
		
		return str;
	}
	
}
