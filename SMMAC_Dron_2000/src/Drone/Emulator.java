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
		Commands cmd = null;
		CommandManager c = null;
		
		try
		{
			drone = new ARDrone();	

			drone.addExceptionListener(new IExceptionListener() {

				@Override
				public void exeptionOccurred(ARDroneException exc) {
					exc.printStackTrace();
//					ARDrone drone;
//					drone = new ARDrone();
//					drone.getCommandManager().landing();
					System.out.println("Der skete en fejl__________________________________________________________________________________");
				}
			});

			//drone.reset();
			drone.start();
			mov = new Movement(drone);
			//cmd = new Commands(drone);
			
			

			new GUI(drone);
			
			//cmd.orangeBlink();
			Thread.sleep(5000);
			System.out.println("Køres denne??????");
			
			mov.takeoff();
			mov.waitFor(4000);
			long startTime = System.currentTimeMillis();
			mov.backwards(10, 10000);
			
			long endTime = System.currentTimeMillis();
			long totalTime = endTime-startTime;
			System.out.println("Tiden: " + totalTime + "***************************************" );
			
			mov.landing();
			mov.landing();
			mov.landing();
			mov.landing();
			mov.landing();
			
		}
		catch (Exception e)
		{
			System.out.println("catch");
			e.printStackTrace();
		}
	}

}
