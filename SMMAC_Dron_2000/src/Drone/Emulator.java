package Drone;
import de.yadrone.base.ARDrone;
import de.yadrone.base.command.CommandManager;
import de.yadrone.base.command.LEDAnimation;

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

			drone.start();

			mov = new Movement(drone);
			//cmd = new Commands(drone);

			new GUI(drone);
			
			drone.getCommandManager().setLedsAnimation(LEDAnimation.BLINK_ORANGE, 3, 10);

			mov.takeoff(2000);
			Thread.sleep(2000);
			mov.forward(15, 500);
			Thread.sleep(2000);
			mov.spinLeft(20, 2000);
			Thread.sleep(2000);
			//mov.goLeft(15, 500);
			//Thread.sleep(3000);
			// mov.spinRight(50, 5000);
			mov.landing();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}
