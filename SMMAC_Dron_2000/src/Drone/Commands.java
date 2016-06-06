package Drone;
import de.yadrone.base.command.LEDAnimation;
import de.yadrone.base.navdata.BatteryListener;
import de.yadrone.base.IARDrone;
import de.yadrone.base.command.CommandManager;


public class Commands {

	private CommandManager cmd;

	public Commands(final IARDrone drone)
	{
		this.cmd = drone.getCommandManager();
	}

	public void orangeBlink()
	{
		cmd.setLedsAnimation(LEDAnimation.BLINK_ORANGE, 3, 10);
	}

	public boolean wifi(){
		return cmd.isConnected();
	}
}
