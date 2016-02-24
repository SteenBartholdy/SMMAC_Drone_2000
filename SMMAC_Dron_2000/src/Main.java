import de.yadrone.base.ARDrone;
import de.yadrone.base.IARDrone;
import de.yadrone.base.exception.ARDroneException;
import de.yadrone.base.exception.IExceptionListener;

public class Main {

	public static void main(String[] args)
	{	
		IARDrone drone = null;
		Movement mov = null;
		
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
			
			new Video(drone);
			
			
			mov = new Movement(drone);
			
			mov.takeoff();
			mov.waitFor(5000);
			mov.forward(30, 1000);
			mov.backwards(30, 1000);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		finally
		{
			if (drone != null)
			{
				mov.landing();
				drone.stop();
			}
		}
	}
}
