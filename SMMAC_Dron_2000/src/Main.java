import de.yadrone.base.ARDrone;
import de.yadrone.base.IARDrone;
import de.yadrone.base.command.CommandManager;
import de.yadrone.base.exception.ARDroneException;
import de.yadrone.base.exception.IExceptionListener;

public class Main {

	public static void main(String[] args)
	{	
		IARDrone drone = null;
		try
		{
			drone = new ARDrone();
			drone.start();
			Movement mov = new Movement(drone);
			
			mov.color();
			mov.takeof(5000);
			mov.landing();
		}
		catch (Exception exc)
		{
			exc.printStackTrace();
		}
		finally
		{
			if (drone != null)
				drone.stop();
		}
	}
}
