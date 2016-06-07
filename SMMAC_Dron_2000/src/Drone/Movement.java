package Drone;
import de.yadrone.base.IARDrone;
import de.yadrone.base.command.CommandManager;

public class Movement {
	
	private CommandManager cmd;
	
	private boolean forwardTimer = true;
	private boolean backwardTimer = true;
	private int backwardCounter = 0;
	
	public Movement(final IARDrone drone)
	{
		cmd = drone.getCommandManager();
	}
	
	public void takeoff ()
	{
		cmd.takeOff();
	}
	
	public void landing()
	{
		cmd.landing();
	}
	
	public void goLeft(int speed, long ms)
	{
		cmd.goLeft(speed).doFor(ms);
	}
	
	public void goRight(int speed, long ms)
	{
		cmd.goRight(speed).doFor(ms);
	}
	
	public void hover(long ms)
	{
		cmd.hover().doFor(ms);
	}
	
	public void waitFor(long ms)
	{
		cmd.waitFor(ms);
	}

	public void backwards(int speed, int time)
	{
		while (this.backwardCounter < time)
		{
			cmd.backward(speed);
			this.backwardCounter++;
		}
		//this.backwardTimer = true;
		this.backwardCounter = 0;
	}

	public void forward(int speed)
	{
		cmd.forward(speed);
	}
	
	public void spinRight(int speed)
	{
		cmd.spinRight(speed);
	}
	
	public void spinLeft(int speed)
	{
		cmd.spinLeft(speed);
	}

	public void moveUp(int speed)
	{
		cmd.up(speed);
	}
	
	public void moveDown(int speed)
	{
		cmd.down(speed);
	}
	
	public void freeze()
	{
		cmd.freeze();
	}

	public void maxAltitude(int alt)
	{
		cmd.setMaxAltitude(alt);
	}
}
