package Drone;
import de.yadrone.base.IARDrone;
import de.yadrone.base.command.CommandManager;

public class Movement {
	private CommandManager cmd;
	private long hoverTime = 100;
	
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
		cmd.goLeft(speed);
		waitFor(ms);
		hover(hoverTime);
	}
	
	public void goRight(int speed, long ms)
	{
		cmd.goRight(speed);
		waitFor(ms);
		hover(hoverTime);
	}
	
	public void hover(long ms)
	{
		cmd.hover();
		waitFor(ms);
		hover(hoverTime);
	}
	
	public void waitFor(long ms)
	{
		cmd.waitFor(ms);
	}

	public void backwards(int speed, long ms)
	{
		cmd.backward(speed);
		waitFor(ms);
		hover(hoverTime);
	}

	public void forward(int speed, long ms)
	{
		cmd.forward(speed);
		waitFor(ms);
		hover(hoverTime);
	}

	public void spinRight(int speed, long ms)
	{
		cmd.spinRight(speed);
		waitFor(ms);
		hover(hoverTime);
	}
	
	public void spinLeft(int speed, long ms)
	{
		cmd.spinLeft(speed);
		waitFor(ms);
		hover(hoverTime);
	}

	public void moveUp(int speed, long ms)
	{
		cmd.up(speed);
		waitFor(ms);
		hover(hoverTime);
	}
	
	public void moveDown(int speed, long ms)
	{
		cmd.down(speed);
		waitFor(ms);
		hover(hoverTime);
	}
	
	public void freeze(long ms)
	{
		cmd.freeze().doFor(ms);
	}
}
