import de.yadrone.base.IARDrone;
import de.yadrone.base.command.CommandManager;

public class Movement {
	private CommandManager cmd;
	
	public Movement(IARDrone drone)
	{
		cmd = drone.getCommandManager();
	}
	
	public void takeof (long ms)
	{
		cmd.takeOff().doFor(ms);
	}
	
	public void landing()
	{
		cmd.landing();
	}
}
