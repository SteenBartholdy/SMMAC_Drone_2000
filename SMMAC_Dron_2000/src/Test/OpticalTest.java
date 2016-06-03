package Test;

import org.opencv.core.Core;
import org.opencv.imgcodecs.Imgcodecs;

import Drone.OpticalFlow;

public class OpticalTest {
	
	public static void main(String[] args) {
		
		OpticalFlow op = new OpticalFlow();
		
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		op.useOpticalFlow(Imgcodecs.imread("/Users/Christoffer/Pictures/b1.png"), Imgcodecs.imread("/Users/Christoffer/Pictures/b2.png"));
		
	}

}
