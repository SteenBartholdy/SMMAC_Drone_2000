package com.dtu.smmac.drone;

import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;

import com.dtu.smmac.data.Name;

public class TakePicture {

	public void savePicture(Name input, String filename, Mat img) {
		if(img == null) 
			return;
		
		String str = "";
		
		switch (input) {
		case MARTIN: 
			str = "/Users/Roos/Pictures/" + filename + ".png";
			break;
		case ANDERS: 
			str = "/Users/Anders/Pictures/" + filename + ".png"; 
			break;
		case CHRISTOFFER: 
			str = "/Users/Christoffer/Pictures/" + filename + ".png"; 
			break;
		case MADS: 
			str = ""; 
			break; 
		case STEEN:
			str = ""; 
			break;
		default:
			break;
		}
		
		System.out.println("Done. Writing " + str);
		Imgcodecs.imwrite(str, img);
		System.out.println("Image saved");
	}
	
}
