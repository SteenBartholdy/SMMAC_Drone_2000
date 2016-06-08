package Drone;
import de.yadrone.base.ARDrone;
import de.yadrone.base.command.CommandManager;
import de.yadrone.base.command.LEDAnimation;
import de.yadrone.base.exception.ARDroneException;
import de.yadrone.base.exception.IExceptionListener;

public class Emulator {
	
	public static void main(String[] args)
	{	
		ARDrone drone = null;
		Movement mov = null;

		try
		{
			drone = new ARDrone();	
			drone.start();

			mov = new Movement(drone);
			new GUI(mov);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}
