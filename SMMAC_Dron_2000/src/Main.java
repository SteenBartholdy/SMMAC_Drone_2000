import de.yadrone.base.ARDrone;
import de.yadrone.base.IARDrone;

public class Main {

	public static void main(String[] args)
	{	
		IARDrone drone = null;
		Movement mov = null;
		
		try
		{
			drone = new ARDrone();
			drone.start();
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
