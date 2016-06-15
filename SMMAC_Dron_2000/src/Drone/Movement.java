package Drone;
import java.util.Random;

import org.opencv.core.Point;

import Math.Circle;
import Math.Vector;
import de.yadrone.base.command.CommandManager;

public class Movement {

	private CommandManager cmd;
	private final double VECTOR_LENGTH = 25;
	//	private int counter = 0;

	public Movement(CommandManager cmd)
	{
		this.cmd = cmd;
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
		cmd.waitFor(10);
		cmd.hover();
	}

	public void down() {
		cmd.down(100);
		cmd.waitFor(10);
		cmd.hover();
	}

	public void right() {
		cmd.goRight(20);
		cmd.waitFor(10);
		cmd.hover();
	}

	public void left() {
		cmd.goLeft(20);
		cmd.waitFor(10);
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

	public void forward() {
		cmd.forward(45);
		cmd.waitFor(20);
		cmd.hover();
	}

	public void fastForward() {
		cmd.forward(45);
		cmd.waitFor(40);
		cmd.hover();
	}

	public void hover()
	{
		cmd.hover();
	}

	public void stopMoveing() {
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

	public void circleMovement(Circle c, Point center) {
		Vector v = new Vector(center, c.getCentrum());

		if (v.length() < VECTOR_LENGTH && c.getRadius() > 100) {
			fastForward();
			System.out.println("FREM");
			return;
		} else if (v.length() < VECTOR_LENGTH*2) {
			forward();
			System.out.println("LIDT FREM");
			return;
		}

		if (v.getB().y > v.getA().y) {
			down();
			System.out.println("NED");
		} else {
			up();
			System.out.println("OP");
		}

		if (v.getB().x > v.getA().x) {
			right();
			System.out.println("HØJRE");
		} else {
			left();
			System.out.println("VENSTRE");
		}

		stopMoveing();
	}

	public void search() {
		Random rn = new Random();
		int search = rn.nextInt() % 5;

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
			down();
			System.out.println("LIDT NED");
			break;
		case 3: 
		case 4:
			up();
			System.out.println("LIDT OP");
			break;
		}
		
		stopMoveing();
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
