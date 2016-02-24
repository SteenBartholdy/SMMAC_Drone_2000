import de.yadrone.base.ARDrone;
import de.yadrone.base.IARDrone;
import de.yadrone.base.exception.ARDroneException;
import de.yadrone.base.exception.IExceptionListener;

public class Main {

	public static void main(String[] args)
	{	
		IARDrone drone = null;
		Movement mov = null;
		Commands cmd = null;
		
		try
		{
			drone = new ARDrone();
			
			drone.addExceptionListener(new IExceptionListener() {
				public void exeptionOccurred(ARDroneException exc)
				{
					exc.printStackTrace();
				}
			});		
			
			drone.start();
			
			mov = new Movement(drone);
			cmd = new Commands(drone);
			
			new Video(drone);
			
			cmd.waitFor(10000);
			
			cmd.printBattery();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
	}
}
