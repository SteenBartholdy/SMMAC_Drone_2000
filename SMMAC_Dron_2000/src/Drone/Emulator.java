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
			
			mov.startUp();
			
			//cmd.orangeBlink();
			Thread.sleep(5000);
			
			
			mov.takeoff();
			
//			mov.goRight(30, 50);
//			mov.forward(100, 100);
			mov.hover(200);
			mov.forward(100, 200);
			mov.landing();
			
		}
		catch (Exception e)
		{
			System.out.println("catch");
			e.printStackTrace();
		}
	}

}
