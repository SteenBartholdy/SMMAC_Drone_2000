package Drone;
import de.yadrone.base.IARDrone;
import de.yadrone.base.command.CommandManager;

public class Movement {

	private CommandManager cmd;
	private IARDrone drone;

	private int counter = 0;

	public Movement(final IARDrone drone)
	{
		this.drone = drone;
		this.cmd = drone.getCommandManager();
	}
	
	public void startUp()
	{
		maxAltitude(2000);
		cmd.setOutdoor(false, false);
	}

	public void takeoff ()
	{
		cmd.takeOff();
		waitFor(4000);	
		forward(50, 55);
		hover(100);
		moveUp(100, 45);
		hover(100);
	}

	public void landing()
	{
		while (true)
		{
			cmd.landing();
		}
	}

	public void goLeft(int speed, int time)
	{
		while (this.counter < time)
		{
			cmd.goLeft(speed);
			waitFor(10);
			this.counter++;
		}
		this.counter = 0;
		
		while (this.counter < 200)
		{
		cmd.goLeft(0);
		waitFor(10);
		this.counter++;
		}
		this.counter = 0;
	}

	public void goRight(int speed, int time)
	{
		while (this.counter < time)
		{
			cmd.goRight(speed);
			waitFor(10);
			this.counter++;
		}
		this.counter = 0;
		
		while (this.counter < 200)
		{
		cmd.goRight(0);
		waitFor(1000);
		this.counter++;
		}
		this.counter = 0;
	}

	public void hover(int time)
	{
		while (this.counter < time)
		{
			cmd.hover();
			waitFor(10);
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
		while (this.counter < time)
		{
			cmd.backward(speed);
			waitFor(10);
			this.counter++;
		}
		cmd.backward(0);
		waitFor(1000);
		this.counter = 0;
	}

	public void forward(int speed, int time)
	{
		while (this.counter < time)
		{
			cmd.forward(speed);
			waitFor(10);
			this.counter++;
		}
		cmd.forward(0);
		waitFor(1000);
		this.counter = 0;
	}

	public void spinRight(int speed, int time)
	{
		while (this.counter < time)
		{
			cmd.spinRight(speed);
			waitFor(10);
			this.counter++;
		}
		cmd.spinRight(0);
		waitFor(1000);
		this.counter = 0;
	}

	public void spinLeft(int speed, int time)
	{
		while (this.counter < time)
		{
			cmd.spinLeft(speed);
			waitFor(10);
			this.counter++;
		}
		cmd.spinLeft(0);
		waitFor(1000);
		this.counter = 0;
	}

	public void moveUp(int speed, int time)
	{
		while (this.counter < time)
		{
			cmd.up(speed);
			waitFor(10);
			this.counter++;
		}
		cmd.up(0);
		waitFor(1000);
		this.counter = 0;
	}

	public void moveDown(int speed, int time)
	{
		while (this.counter < time)
		{
			cmd.down(speed);
			waitFor(10);
			this.counter++;
		}
		cmd.down(0);
		waitFor(1000);
		this.counter = 0;
	}

	public void maxAltitude(int mm)
	{
		cmd.setMaxAltitude(mm);
	}
	
	public void flatTrim()
	{
		cmd.flatTrim();
	}

	public IARDrone getDrone() {
		return this.drone;
	}
	
}
