package Drone;
import de.yadrone.base.ARDrone;
import de.yadrone.base.command.CommandManager;

public class Emulator {

	public static void main(String[] args)
	{	
		ARDrone drone = null;
		Movement mov = null;
		Commands cmd = null;
		CommandManager c = null;

		try
		{
			drone = new ARDrone();	

			drone.start();

			mov = new Movement(drone);
			//cmd = new Commands(drone);
			
			//new Listener(drone);
			//new Video(drone);
			new GUI(drone);
			
			//mov.takeoff();
			mov.hover(3000);
			mov.goRight(30, 3000);
			mov.hover(3000);
			mov.goRight(30, 3000);
			mov.hover(3000);
			mov.spinLeft(50, 5000);
			mov.hover(3000);
			mov.spinRight(50, 5000);
			mov.hover(3000);
			mov.landing();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}
