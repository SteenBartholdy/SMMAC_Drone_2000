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
			
	/*		mov.takeoff();
			mov.waitFor(3000);
			
			mov.moveUp(100);
			mov.waitFor(1000);
			mov.moveUp(0);
			
			mov.moveDown(60);
			mov.waitFor(1000);
			mov.moveDown(0);
			
			mov.waitFor(1000);
			
			
			
			

			mov.landing();
			mov.landing();
			mov.landing();
			mov.landing();
			mov.landing();
	*/
			
//			drone.getCommandManager().takeOff();
//			drone.getCommandManager().backward(0);			
//			drone.getCommandManager().waitFor(5000);
//			System.out.println("TakeOff færdig-----------------------------------------------------------------------------------------------");
//
//			drone.getCommandManager().forward(30);
//			drone.getCommandManager().waitFor(2000);
//			drone.getCommandManager().forward(0);			
//			drone.getCommandManager().waitFor(2000);
//			System.out.println("Forward færdig-----------------------------------------------------------------------------------------------");
//			
//	/*		drone.getCommandManager().goRight(50);
//			drone.getCommandManager().waitFor(2000);
//			drone.getCommandManager().goRight(0);
//			drone.getCommandManager().waitFor(2000);
//			System.out.println("Right færdig-------------------------------------------------------------------------------------------------");
//	*/		
//			drone.getCommandManager().landing();
//			
//			//mov.maxAltitude(600);
//			

			
		}
		catch (Exception e)
		{
			System.out.println("catch");
			e.printStackTrace();
		}
	}

}
