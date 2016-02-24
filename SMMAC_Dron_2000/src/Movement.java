import de.yadrone.base.IARDrone;
import de.yadrone.base.command.CommandManager;

public class Movement {
	private CommandManager cmd;
	
	public Movement(IARDrone drone)
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

	public void backwards(int speed, long ms)
	{
		cmd.backward(speed).doFor(ms);
	}

	public void forward(int speed, long ms)
	{
		cmd.forward(speed).doFor(ms);
	}

	public void spinRight(int speed, long ms)
	{
		cmd.spinRight(speed).doFor(ms);
	}
	
	public void spinLeft(int speed, long ms)
	{
		cmd.spinLeft(speed).doFor(ms);
	}

	public void moveUp(int speed, long ms)
	{
		cmd.up(speed).doFor(ms);
	}
	
	public void moveDown(int speed, long ms)
	{
		cmd.down(speed).doFor(ms);
	}
}
