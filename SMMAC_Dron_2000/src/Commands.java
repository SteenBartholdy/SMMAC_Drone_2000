import de.yadrone.base.command.LEDAnimation;
import de.yadrone.base.IARDrone;
import de.yadrone.base.command.CommandManager;


public class Commands {

private CommandManager cmd;
	
	public Commands(IARDrone drone)
	{
		cmd = drone.getCommandManager();
	}

	public void ledAnimationOrangeBlink()
	{
		cmd.setLedsAnimation(LEDAnimation.BLINK_ORANGE, 3, 10);
	}
	
	public boolean wifi(){
		return cmd.isConnected();
	}
}
