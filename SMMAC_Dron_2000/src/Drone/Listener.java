package Drone;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import de.yadrone.base.IARDrone;
import de.yadrone.base.navdata.AttitudeListener;
import de.yadrone.base.navdata.BatteryListener;

public class Listener extends JFrame {

	private String sBattery;
	private String sPitch;
	private String sRoll;
	private String sYaw;

	public Listener(final IARDrone drone)
	{
		super("Facts");

		setSize(150,225);
		setBackground(Color.WHITE);
		setVisible(true);

		drone.getNavDataManager().addBatteryListener(new BatteryListener() {

			public void batteryLevelChanged(int percentage)
			{
				sBattery = "Battery: " + percentage + " %";
				SwingUtilities.invokeLater(new Runnable() {
					public void run()
					{
						repaint();
					}
				});
			}

			public void voltageChanged(int vbat_raw) { }
		});

		drone.getNavDataManager().addAttitudeListener(new AttitudeListener() {

			@Override
			public void attitudeUpdated(float pitch, float roll, float yaw) {
				sPitch = "Pitch: " + pitch;
				sRoll = "Roll: " + roll;
				sYaw = "Yaw: " + yaw;
				SwingUtilities.invokeLater(new Runnable() {
					public void run()
					{
						repaint();
					}
				});
			}

			@Override
			public void attitudeUpdated(float arg0, float arg1) { }

			@Override
			public void windCompensation(float arg0, float arg1) { }
		});	

	}

	public synchronized void paint(Graphics g)
	{
		if(sBattery != null)
			g.drawString(sBattery, 25, 50);

		if(sPitch != null)
			g.drawString(sPitch, 25, 100);
		
		if(sRoll != null)
			g.drawString(sRoll, 25, 150);
		
		if(sYaw != null)
			g.drawString(sYaw, 25, 200);
		
		System.out.println(sPitch);
	}

}
