package Drone;

import org.opencv.core.Mat;
import org.opencv.core.MatOfByte;
import org.opencv.core.MatOfFloat;
import org.opencv.core.MatOfPoint;
import org.opencv.core.MatOfPoint2f;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;
import org.opencv.video.Video;

public class OpticalFlow {
	
	public void flow (Mat imagePrev, Mat imageNext) {
		Mat grayImagePrev = new Mat();
		Mat grayImageNext = new Mat();
		MatOfByte status = new MatOfByte();
		MatOfFloat err = new MatOfFloat();
		MatOfPoint pointsPrev = new MatOfPoint();
		Imgproc.cvtColor(imagePrev, grayImagePrev, Imgproc.COLOR_BGR2GRAY);
		Imgproc.goodFeaturesToTrack(grayImagePrev, pointsPrev, 1000, 0.01, 1);
		Imgproc.cvtColor(imageNext, grayImageNext, Imgproc.COLOR_BGR2GRAY);
		MatOfPoint2f c1 = new MatOfPoint2f(pointsPrev.toArray());
		MatOfPoint2f c2 = new MatOfPoint2f();
		Video.calcOpticalFlowPyrLK(grayImagePrev, grayImageNext, c1, c2, status, err);
		
		for (int i = 0; i < status.rows(); i++) {
			int statusInt = (int) status.get(i, 0)[0];
			if (statusInt == 1) {
				double[] cornerPoints1 = c1.get(i, 0);
				double[] cornerPoints2 = c2.get(i, 0);
				Imgproc.line(imageNext, new Point(cornerPoints1[0], cornerPoints1[1]), 
						 new Point(cornerPoints2[0], cornerPoints2[1]), new Scalar(255,50,0), 2);
			}
		}
	}
	

}
