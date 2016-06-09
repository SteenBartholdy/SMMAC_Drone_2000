package Drone;
import Data.Name;
import de.yadrone.base.ARDrone;
import de.yadrone.base.IARDrone;
import de.yadrone.base.command.CommandManager;
import de.yadrone.base.command.WifiMode;

public class Movement {

	private final IARDrone drone = new ARDrone();
	private CommandManager cmd;

	private int counter = 0;

	public Movement()
	{
		this.cmd = drone.getCommandManager();
		this.cmd.setMinAltitude(1500);
	}

	public void takeoff ()
	{
		cmd.takeOff();
		cmd.hover();
		waitFor(4000);
//		moveUp(100, 50);
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

	public CommandManager getCmd() {
		return this.cmd;
	}

	public void setMAC(Name input) {
		String str = null;

		switch (input) {
		case MARTIN: 
			str = "a8:66:7f:03:c9:05";
			break;
		case ANDERS: 
			str = "a8:66:7f:03:c9:05"; 
			break;
		case CHRISTOFFER: 
			str = "80:e6:50:09:6b:90"; 
			break;
		case MADS: 
			str = "18:CF:5E:91:4B:A3"; 
			break; 
		case STEEN:
			str = "60:36:DD:15:E5:55"; 
			break;
		default:
			break;
		}

		this.cmd.setWifiMode(WifiMode.ADHOC);
		this.cmd.setSSIDSinglePlayer("SMMAC");
		this.cmd.setOwnerMac(str);
	}

}
