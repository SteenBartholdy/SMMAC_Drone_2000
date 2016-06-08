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
			new GUI(mov);
			c = drone.getCommandManager();
			mov.startUp();

			//cmd.orangeBlink();
			Thread.sleep(5000);


			mov.takeoff();
			mov.goLeft(25, 150);
			mov.waitFor(2000);
			mov.goRight(25, 150);
			//mov.landing();
			

		}
		catch (Exception e)
		{
			System.out.println("catch");
			e.printStackTrace();
		}

	}

}
