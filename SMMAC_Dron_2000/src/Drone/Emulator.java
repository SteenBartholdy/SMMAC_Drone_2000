package Drone;
import de.yadrone.base.ARDrone;
import de.yadrone.base.IARDrone;

public class Emulator {

	public static void main(String[] args)
	{	
		IARDrone drone = null;
		Movement mov = null;
		Commands cmd = null;
		
		try
		{
			drone = new ARDrone();	
			
			drone.start();
			
			mov = new Movement(drone);
			cmd = new Commands(drone);
			
			cmd.printBattery();
			
			new Video(drone);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
	}
}
