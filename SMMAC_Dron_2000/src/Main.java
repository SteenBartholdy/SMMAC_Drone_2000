import de.yadrone.base.ARDrone;
import de.yadrone.base.IARDrone;

public class Main {

	public static void main(String[] args)
	{	
		IARDrone drone = null;
		try
		{
			drone = new ARDrone();
			drone.start();
			Movement mov = new Movement(drone);
			
			mov.takeoff();
			mov.waitFor(5000);
			mov.landing();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		finally
		{
			if (drone != null)
				drone.stop();
		}
	}
}
