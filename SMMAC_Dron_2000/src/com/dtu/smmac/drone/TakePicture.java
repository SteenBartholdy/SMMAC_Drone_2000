package com.dtu.smmac.drone;

import java.awt.image.BufferedImage;

import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;

import com.dtu.smmac.data.Name;

public class TakePicture {

	public void savePicture(Name input, String filename, BufferedImage img) {
		if (img == null)
			return;
		
		ImageProcessor imgP = new ImageProcessor();
		
		Mat mat = imgP.toMatImage(img);
		
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
		Imgcodecs.imwrite(str, mat);
		System.out.println("Image saved");
	}
	
}
