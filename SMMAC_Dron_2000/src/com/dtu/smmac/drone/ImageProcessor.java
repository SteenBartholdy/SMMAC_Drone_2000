package com.dtu.smmac.drone;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.util.ArrayList;
import java.util.List;

import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;

import com.dtu.smmac.gui.Image;
import com.dtu.smmac.math.Circle;

public class ImageProcessor {

	private CircleDetection cd = new CircleDetection();

	public Mat erode(Mat input, int elementSize, int elementShape) {
		Mat outputImage = new Mat();
		Mat element = getKernelFromShape(elementSize, elementShape);
		Imgproc.erode(input, outputImage, element);
		return outputImage;
	}

	public Mat dilate(Mat input, int elementSize, int elementShape) {
		Mat outputImage = new Mat();
		Mat element = getKernelFromShape(elementSize, elementShape);
		Imgproc.dilate(input, outputImage, element);
		return outputImage;
	}

	public Mat open(Mat input, int elementSize, int elementShape) {
		Mat outputImage = new Mat();
		Mat element = getKernelFromShape(elementSize, elementShape);
		Imgproc.morphologyEx(input, outputImage, Imgproc.MORPH_OPEN, element);
		return outputImage;
	}

	public Mat close(Mat input, int elementSize, int elementShape) {
		Mat outputImage = new Mat();
		Mat element = getKernelFromShape(elementSize, elementShape);
		Imgproc.morphologyEx(input, outputImage, Imgproc.MORPH_CLOSE, element);
		return outputImage;
	}

	private Mat getKernelFromShape(int elementSize, int elementShape) {
		return Imgproc.getStructuringElement(elementShape, new Size(elementSize * 2 + 1, elementSize * 2 + 1));
	}

	public BufferedImage toBufferedImage(Mat matrix){
		int type = BufferedImage.TYPE_BYTE_GRAY;
		if ( matrix.channels() > 1 ) {
			type = BufferedImage.TYPE_3BYTE_BGR;
		}
		int bufferSize = matrix.channels()*matrix.cols()*matrix.rows();
		byte [] buffer = new byte[bufferSize];
		matrix.get(0,0,buffer); 
		BufferedImage image = new BufferedImage(matrix.cols(),matrix.rows(), type);
		final byte[] targetPixels = ((DataBufferByte) image.getRaster().getDataBuffer()).getData();
		System.arraycopy(buffer, 0, targetPixels, 0, buffer.length);
		return image;
	}

	public Mat toMatImage(BufferedImage image) {
		byte[] pixels = ((DataBufferByte) image.getRaster().getDataBuffer()).getData();
		Mat imageMat = new Mat(image.getHeight(), image.getWidth(), CvType.CV_8UC3);
		imageMat.put(0, 0, pixels);
		return imageMat;
	}

	public Mat findContours(Mat image, Mat contourOutput)
	{
		List<MatOfPoint> contours = new ArrayList<>();
		Mat hierarchy = new Mat();

		Imgproc.findContours(image, contours, hierarchy, Imgproc.RETR_CCOMP, Imgproc.CHAIN_APPROX_SIMPLE);

		if (hierarchy.size().height > 0 && hierarchy.size().width > 0)
		{
			// for each contour, display it in blue
			for (int idx = 0; idx >= 0; idx = (int) hierarchy.get(0, idx)[0])
			{
				Imgproc.drawContours(contourOutput, contours, idx, new Scalar(250, 0, 0));
			}
		}

		return contourOutput;
	}

	public void start(Mat img, Point centrum, Movement mv, boolean isFlying, Image image) {
		if (img == null || !isFlying)
			return;

		Circle circle = cd.useCircleDetection(setThreshold(img));
		
		if (circle != null) {
			image.setCircleImage(circle);
			mv.circleMovement(circle, centrum);
		} else {
			//mv.search();
			mv.up();
			System.out.println("LIDT OP");
		}

	}
	
	public Mat setThreshold(Mat input) {
		Mat output = new Mat();
		
		Imgproc.threshold(input, output, 80, 255, Imgproc.THRESH_BINARY);
		
		return output;
	}
}
