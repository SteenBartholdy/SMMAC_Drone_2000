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

			// new GUI(drone);
			
			mov.takeoff();
			mov.moveUp(20, 100);
			// mov.freeze(1000);
			mov.landing();
			
			/*
			mov.hover(3000);
			mov.goRight(20, 100);
			mov.hover(3000);
			mov.goLeft(20, 100);
			mov.hover(3000);
			mov.spinLeft(50, 2000);
			mov.hover(3000);
			mov.spinRight(50, 2000);
			mov.hover(3000);
			mov.landing();
			*/
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}
