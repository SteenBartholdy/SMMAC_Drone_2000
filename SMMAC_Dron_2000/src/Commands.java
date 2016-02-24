import de.yadrone.base.command.LEDAnimation;
import de.yadrone.base.navdata.BatteryListener;
import de.yadrone.base.IARDrone;
import de.yadrone.base.command.CommandManager;


public class Commands {

	private CommandManager cmd;
	private IARDrone drone;

	public Commands(final IARDrone drone)
	{
		this.cmd = drone.getCommandManager();
		this.drone = drone;
	}

	public void ledAnimationOrangeBlink()
	{
		cmd.setLedsAnimation(LEDAnimation.BLINK_ORANGE, 3, 10);
	}

	public boolean wifi(){
		return cmd.isConnected();
	}

	public void printBattery(){
		drone.getNavDataManager().addBatteryListener(new BatteryListener() {

			public void batteryLevelChanged(int percentage)
			{
				System.out.println("Battery: " + percentage + " %");
			}

			public void voltageChanged(int vbat_raw) { }
		});
	}
	
	public void waitFor(long ms){
		cmd.waitFor(ms);
	}
}
