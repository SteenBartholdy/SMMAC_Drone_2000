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
			cmd = new Commands(drone);

			new GUI(drone);
			
			//cmd.orangeBlink();
			Thread.sleep(5000);
			System.out.println("Køres denne??????");
			mov.flatTrim();
			mov.takeoff(4000);
			mov.forward(100, 50);
			mov.moveUp(100, 100);
//			long startTime1 = System.currentTimeMillis();
//			mov.forward(80, 100);
//			long endTime1 = System.currentTimeMillis();
//			long totalTime1 = endTime1-startTime1;
//			System.out.println("Tiden backward: " + totalTime1 + "***************************************" );
//			
//			long startTime = System.currentTimeMillis();
//			mov.backwards(30, 100);
//			long endTime = System.currentTimeMillis();
//			long totalTime = endTime-startTime;
//			System.out.println("Tiden backward: " + totalTime + "***************************************" );
			mov.spinLeft(100, 500);
			
			mov.waitFor(2000);
			
			mov.landing();
			
		}
		catch (Exception e)
		{
			System.out.println("catch");
			e.printStackTrace();
		}
	}

}
