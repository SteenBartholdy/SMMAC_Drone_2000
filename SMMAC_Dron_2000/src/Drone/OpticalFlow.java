package Drone;

import java.util.ArrayList;

import org.opencv.core.Mat;
import org.opencv.core.Point;

public class OpticalFlow {
	
	Mat image;
	
	ArrayList<Point> startPoint = new ArrayList<>();
	ArrayList<Point> endPoint = new ArrayList<>();
	
	public OpticalFlow(Mat image,ArrayList<Point> startPoint,ArrayList<Point> endPoint )
	{
		
		this.image = image;
		this.startPoint = startPoint;
		this.endPoint = endPoint;
		
	}
	
	public ArrayList<Point> getStartPoint() 
	{
		return startPoint;
	}

	public ArrayList<Point> getEndPoint() 
	{
		return endPoint;
	}
	
	public Mat getMatImage()
	{
		return image;
	}

}
