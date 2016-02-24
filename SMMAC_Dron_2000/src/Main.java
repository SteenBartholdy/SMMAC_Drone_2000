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
		}
		catch (Exception exc)
		{
			exc.printStackTrace();
		}
		finally
		{
			if (drone != null)
				drone.stop();
			System.exit(0);
		}
		
//		drone.getNavDataManager().addAttitudeListener(new AttitudeListener() {
//			
//		    public void attitudeUpdated(float pitch, float roll, float yaw)
//		    {
//		        System.out.println("Pitch: " + pitch + " Roll: " + roll + " Yaw: " + yaw);
//		    }
//
//		    public void attitudeUpdated(float pitch, float roll) { }
//		    public void windCompensation(float pitch, float roll) { }
//		});
//		
//		drone.getNavDataManager().addBatteryListener(new BatteryListener() {
//			
//		    public void batteryLevelChanged(int percentage)
//		    {
//		        System.out.println("Battery: " + percentage + " %");
//		    }
//
//		    public void voltageChanged(int vbat_raw) { }
//		});
		
		
		CommandManager cmd = drone.getCommandManager();
		
		System.out.println("Den er forbundet = " + cmd.isConnected());
		
		int speed = 30; // percentage of max speed
		
		cmd.takeOff().doFor(5000);
		
		cmd.landing();
		
		drone.addExceptionListener(new IExceptionListener() {
		    public void exeptionOccurred(ARDroneException exc)
		    {
		        exc.printStackTrace();
		    }
		});
	}

}
