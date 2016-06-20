package com.dtu.smmac.drone;

import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;

public class TakePicture {

	public void savePicture(String str, Mat img) {
		if(img == null) 
			return;
		
		System.out.println("Done. Writing " + str);
		Imgcodecs.imwrite(str, img);
		System.out.println("Image saved");
	}
	
}
