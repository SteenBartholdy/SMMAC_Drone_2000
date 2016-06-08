package Drone;
import de.yadrone.base.ARDrone;
import de.yadrone.base.IARDrone;
import de.yadrone.base.command.CommandManager;

public class Movement {

	private final IARDrone drone = new ARDrone();
	private CommandManager cmd;

	private int counter = 0;

	public Movement()
	{
		this.cmd = drone.getCommandManager();
		//this.cmd.setSSIDSinglePlayer("SMMAC");
		//this.cmd.setWifiMode(WifiMode.ADHOC);
		//this.cmd.setOwnerMac("a8:66:7f:03:c9:05");
	}

	public void takeoff ()
	{
		cmd.takeOff();
		cmd.hover();
		waitFor(5500);
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
	
}
