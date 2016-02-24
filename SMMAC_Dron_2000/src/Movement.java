import de.yadrone.base.IARDrone;
import de.yadrone.base.command.CommandManager;
import de.yadrone.base.command.LEDAnimation;

public class Movement {
	private CommandManager cmd;
	
	public Movement(IARDrone drone)
	{
		cmd = drone.getCommandManager();
	}
	
	public void takeof ()
	{
		cmd.takeOff();
	}
	
	public void landing()
	{
		cmd.landing();
	}
	
	public void color()
	{
		cmd.setLedsAnimation(LEDAnimation.BLINK_ORANGE, 3, 10);
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
}
