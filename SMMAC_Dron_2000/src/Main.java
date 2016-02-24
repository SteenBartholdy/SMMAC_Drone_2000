import de.yadrone.base.ARDrone;
import de.yadrone.base.IARDrone;

public class Main {

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
			
			//cmd.printBattery();
			
			new Video(drone);
			
			cmd.waitFor(100);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		finally 
		{
			drone.stop();
			System.exit(0);
		}
	}
}
