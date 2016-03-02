package Drone;

import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import de.yadrone.base.IARDrone;
import de.yadrone.base.navdata.BatteryListener;

public class Listener extends JFrame {
	
	private String battery;
	
	public Listener(final IARDrone drone)
	{
		super("Listener");

        setSize(300,300);
        setVisible(true);
        
        drone.getNavDataManager().addBatteryListener(new BatteryListener() {

			public void batteryLevelChanged(int percentage)
			{
				battery = "Battery: " + percentage + " %";
				SwingUtilities.invokeLater(new Runnable() {
        			public void run()
        			{
        				repaint();
        			}
        		});
			}

			public void voltageChanged(int vbat_raw) { }
		});
	}
	
	public synchronized void paint(Graphics g)
    {
		super.paintComponents(g);
        if (battery != null)
        {
        	g.drawString(battery, 10, 20);	
        }
        System.out.println(battery);
    }
	
//	public void printAttitude(){
//		this.drone.getNavDataManager().addAttitudeListener(new AttitudeListener() {
//
//			@Override
//			public void attitudeUpdated(float arg0, float arg1) {
//				
//			}
//
//			@Override
//			public void attitudeUpdated(float pitch, float roll, float yaw) {
//				System.out.println("Pitch: " + pitch);
//				System.out.println("Roll: " + roll);
//				System.out.println("Yaw: " + yaw);
//			}
//
//			@Override
//			public void windCompensation(float arg0, float arg1) {
//
//			}
//			
//		});
//		
//	}
}
