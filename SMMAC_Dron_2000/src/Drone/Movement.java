package Drone;
import de.yadrone.base.command.CommandManager;

public class Movement {

	private CommandManager cmd;

	private int counter = 0;

	public Movement(CommandManager cmd)
	{
		this.cmd = cmd;
		//this.cmd.setMinAltitude(1500);
	}

	public void takeoff ()
	{
		
		cmd.takeOff();
		backwards(0, 100);
		waitFor(4000);
		spinLeft(0, 50);
		spinRight(0, 50);
		cmd.hover();
	}

	public void landing()
	{
		while (true)
		{
			cmd.landing();
		}
	}

	public void emergencyLanding() {
		cmd.landing();
	}

	public void goLeft(int speed, int time)
	{
		time = time/50;
		while (this.counter < time)
		{
			cmd.goLeft(speed);
			waitFor(50);
			this.counter++;
		}
		this.counter = 0;

		cmd.hover();

	}

	public void goRight(int speed, int time)
	{
		time = time/50;
		while (this.counter < time)
		{
			cmd.goRight(speed);
			waitFor(50);
			this.counter++;
		}
		this.counter = 0;

		cmd.hover();

	}

	public void hover(int time)
	{
		time = time/50;
		while (this.counter < time)
		{
			cmd.hover();
			waitFor(50);
			this.counter++;
		}
		this.counter = 0;
	}

	public void waitFor(long ms)
	{
		cmd.waitFor(ms);
	}

	public void backwards(int speed, int time)
	{
		time = time/50;
		while (this.counter < time)
		{
			cmd.backward(speed);
			waitFor(50);
			this.counter++;
		}

		cmd.hover();
	}

	public void forward(int speed, int time)
	{
		time = time/50;
		while (this.counter < time)
		{
			cmd.forward(speed);
			waitFor(50);
			this.counter++;
		}

		cmd.hover();
	}

	public void spinRight(int speed, int time)
	{
		time = time/50;
		while (this.counter < time)
		{
			cmd.spinRight(speed);
			waitFor(50);
			this.counter++;
		}

		cmd.hover();
	}

	public void spinLeft(int speed, int time)
	{
		time = time/50;
		while (this.counter < time)
		{
			cmd.spinLeft(speed);
			waitFor(50);
			this.counter++;
		}

		cmd.hover();
	}

	public void moveUp(int speed, int time)
	{
		time = time/50;
		while (this.counter < time)
		{
			cmd.up(speed);
			waitFor(50);
			this.counter++;
		}

		cmd.hover();
	}

	public void moveDown(int speed, int time)
	{
		time = time/50;
		while (this.counter < time)
		{
			cmd.down(speed);
			waitFor(50);
			this.counter++;
		}

		cmd.hover();
	}
	
	public void upCorrection()
	{
		moveUp(100,200);
	}
	
	public void downCorrection()
	{
		moveDown(100,200);
	}
	
	public void spinLeftCorrection()
	{
		spinLeft(100, 150);
	}
	
	public void spinRightCorrection()
	{
		spinRight(100, 150);
	}
	
	public void forwardCorrection()
	{
		forward(100, 750);
	}
	
	public void backwardCorrection()
	{
		//TODO
	}
	
	public void leftCorrection()
	{
		//TODO
	}
	
	public void rightCorrection()
	{
		//TODO
	}

	public void maxAltitude(int mm)
	{
		cmd.setMaxAltitude(mm);
	}

	public void flatTrim()
	{
		cmd.flatTrim();
	}

	public CommandManager getCmd() {
		return this.cmd;
	}	
}
