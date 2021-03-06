package com.dtu.smmac.drone;
import java.awt.image.BufferedImage;
import java.util.Random;

import org.opencv.core.Point;

import com.dtu.smmac.gui.Image;
import com.dtu.smmac.math.Circle;
import com.dtu.smmac.math.Vector;

import de.yadrone.base.command.CommandManager;

public class Movement {

	private CommandManager cmd;
	private final double VECTOR_LENGTH = 40;
	//	private int counter = 0;
	
	private CircleDetection cd = new CircleDetection();
	private boolean flyThrough;

	public Movement(CommandManager cmd)
	{
		this.cmd = cmd;
	}
	
	public void setFlyThrough(boolean value) {
		flyThrough = value;
	}

	public void takeoff() {
		cmd.takeOff();
	}

	public void landing() {
		cmd.landing();
	}

	public CommandManager getCmd() {
		return this.cmd;
	}	

	public void up() {
		cmd.up(100);
		cmd.waitFor(200);
		cmd.hover();
	}
	
	public void up2() {
		cmd.up(100);
		cmd.waitFor(100);
		cmd.hover();
	}

	public void down() {
		cmd.down(100);
		cmd.waitFor(20);
		cmd.hover();
	}

	public void right() {
		cmd.goRight(100);
		cmd.waitFor(200);
		cmd.hover();
	}

	public void left() {
		cmd.goLeft(100);
		cmd.waitFor(200);
		cmd.hover();
	}

	public void spinRight() {
		cmd.spinRight(20);
		cmd.waitFor(10);
		cmd.hover();
	}

	public void spinLeft() {
		cmd.spinLeft(20);
		cmd.waitFor(10);
		cmd.hover();
	}
	
	public void backward() {
		cmd.backward(20);
		cmd.waitFor(10);
		cmd.hover();
	}

	public void forward() {
		cmd.forward(15);
		cmd.waitFor(275);
		stopMoving();
		cmd.hover();
		cmd.waitFor(20);
	}

	public void fastForward() {
		cmd.forward(80);
		cmd.waitFor(2000);
		stopMoving();
		cmd.hover();
		cmd.waitFor(20);
	}
	
	public void fastForward2() {
		cmd.forward(45);
		cmd.waitFor(1500);
		stopMoving();
		cmd.hover();
		cmd.waitFor(20);
	}

	public void hover()
	{
		cmd.hover();
	}

	public void stopMoving() {
		cmd.spinRight(0);
		cmd.spinLeft(0);
		cmd.forward(0);
		cmd.backward(0);
		cmd.goRight(0);
		cmd.goLeft(0);
		cmd.up(0);
		cmd.down(0);
		cmd.hover();
	}

	public boolean circleMovement(Circle c, Point center) {
		Vector v = new Vector(center, c.getCentrum());

		if (v.length() < VECTOR_LENGTH) {
			if(c.getRadius() > 60 && c.getRadius() < 115)
			{
				fastForward();
				System.out.println("FREM");	
				System.out.println("Vektor længde: " + v.length() + " Radius: " + c.getRadius());
				return true;
			}
			else if(c.getRadius() > 115)
			{
				fastForward2();
				System.out.println("FREM 2");	
				System.out.println("Vektor længde: " + v.length() + " Radius: " + c.getRadius());
				return true;
			}
			else
			{
				forward();
				System.out.println("LIDT FREM, MEN TÆT PÅ CENTRUM");
				System.out.println("Vektor længde: " + v.length() + " Radius: " + c.getRadius());
				return false;	
			}
			
		} else if (v.length() < VECTOR_LENGTH+15 && c.getRadius() > 110) {
			forward();
			System.out.println("LIDT FREM");
			System.out.println("Vektor længde: " + v.length() + " Radius: " + c.getRadius());
			return false;
		}

		if(Math.abs(v.getA().y-v.getB().y) > Math.abs(v.getA().x-v.getB().x))
		{
			if (v.getB().y > v.getA().y) {
				down();
				System.out.println("NED");
			} 
			else if(v.getB().y < v.getA().y){
				up();
				System.out.println("OP");
			}
		}
		else if(Math.abs(v.getA().y-v.getB().y) < Math.abs(v.getA().x-v.getB().x))
		{
			if (v.getB().x > v.getA().x) {
				right();
				System.out.println("HØJRE");
			} 
			else if(v.getB().x < v.getA().x){
				left();
				System.out.println("VENSTRE");
			}
		}
		
		return false;
	}

	public void search() {
		Random rn = new Random();
		int search = rn.nextInt() % 6;

		switch(search) {
		case 0: 
			spinLeft();
			System.out.println("LIDT SPIN VENSTRE");
			break;
		case 1: 
			spinRight();
			System.out.println("LIDT SPIN HØJRE");
			break;
		case 2: 
			right();
			System.out.println("LIDT HØJRE");
			break;
		case 3: 
			left();
			System.out.println("LIDT VENSTRE");
			break;
		case 4: 
			up();
			System.out.println("LIDT OP");
			break;
		case 5: 
			down();
			System.out.println("LIDT NED");
			break;
		}
	}
	
	public void landingSearch() {
		Random rn = new Random();
		int search = rn.nextInt() % 6;

		switch(search) {
		case 0: 
			up();
			System.out.println("LIDT SPIN VENSTRE");
			break;
		case 1: 
			left();
			System.out.println("LIDT VENSTRE");
			break;
		case 2: 
			right();
			System.out.println("LIDT HØJRE");
			break;
		case 3: 
			down();
			System.out.println("LIDT NED");
			break;
		case 4:
			forward();
			System.out.println("LIDT FREM");
			break;
		case 5:
			backward();
			System.out.println("LIDT TILBAGE");
			break;
		}
	}
	
	public void start(boolean isFlying, Image image, ImageProcessor pro) {
		BufferedImage img = image.getImg();
		
		if (img == null || !isFlying)
			return;

		Circle circle = cd.useCircleDetection(pro.setThreshold(img));
		
		if (circle != null) {
			image.setCircleImage(circle);
			flyThrough = circleMovement(circle, image.getCentrum());
		} else if (flyThrough) {
			spinRight();
			search();
		} else {
			up2();
			System.out.println("LIDT OP");
		}
		
		stopMoving();
	}

	//	public void goLeft(int speed, int time)
	//	{
	//		time = time/50;
	//		while (this.counter < time)
	//		{
	//			cmd.goLeft(speed);
	//			waitFor(50);
	//			this.counter++;
	//		}
	//		this.counter = 0;
	//
	//		cmd.hover();
	//
	//	}
	//
	//	public void goRight(int speed, int time)
	//	{
	//		time = time/50;
	//		while (this.counter < time)
	//		{
	//			cmd.goRight(speed);
	//			waitFor(50);
	//			this.counter++;
	//		}
	//		this.counter = 0;
	//
	//		cmd.hover();
	//
	//	}
	//
	//	public void waitFor(long ms)
	//	{
	//		cmd.waitFor(ms);
	//	}
	//
	//	public void backwards(int speed, int time)
	//	{
	//		time = time/50;
	//		while (this.counter < time)
	//		{
	//			cmd.backward(speed);
	//			waitFor(50);
	//			this.counter++;
	//		}
	//
	//		cmd.hover();
	//	}
	//
	//	public void forward(int speed, int time)
	//	{
	//		time = time/50;
	//		while (this.counter < time)
	//		{
	//			cmd.forward(speed);
	//			waitFor(50);
	//			this.counter++;
	//		}
	//
	//		cmd.hover();
	//	}
	//
	//	public void spinRight(int speed, int time)
	//	{
	//		time = time/50;
	//		while (this.counter < time)
	//		{
	//			cmd.spinRight(speed);
	//			waitFor(50);
	//			this.counter++;
	//		}
	//
	//		cmd.hover();
	//	}
	//
	//	public void spinLeft(int speed, int time)
	//	{
	//		time = time/50;
	//		while (this.counter < time)
	//		{
	//			cmd.spinLeft(speed);
	//			waitFor(50);
	//			this.counter++;
	//		}
	//
	//		cmd.hover();
	//	}
	//
	//	public void moveUp(int speed, int time)
	//	{
	//		time = time/50;
	//		while (this.counter < time)
	//		{
	//			cmd.up(speed);
	//			waitFor(50);
	//			this.counter++;
	//		}
	//
	//		cmd.hover();
	//	}
	//
	//	public void moveDown(int speed, int time)
	//	{
	//		time = time/50;
	//		while (this.counter < time)
	//		{
	//			cmd.down(speed);
	//			waitFor(50);
	//			this.counter++;
	//		}
	//
	//		cmd.hover();
	//	}
	//	
	//	public void upCorrection()
	//	{
	//		moveUp(25,50);
	//	}
	//	
	//	public void downCorrection()
	//	{
	//		moveDown(25,50);
	//	}
	//	
	//	public void spinLeftCorrection()
	//	{
	//		spinLeft(100, 150);
	//	}
	//	
	//	public void spinRightCorrection()
	//	{
	//		spinRight(100, 150);
	//	}
	//	
	//	public void forwardCorrection()
	//	{
	//		forward(100, 200);
	//	}
	//	
	//	public void backwardCorrection()
	//	{
	//		//TODO
	//	}
	//	
	//	public void leftCorrection()
	//	{
	//		goLeft(20, 50);
	//	}
	//	
	//	public void rightCorrection()
	//	{
	//		goRight(20, 50);
	//	}
	//
	//	public void maxAltitude(int mm)
	//	{
	//		cmd.setMaxAltitude(mm);
	//	}
	//
	//	public void flatTrim()
	//	{
	//		cmd.flatTrim();
	//	}
}
