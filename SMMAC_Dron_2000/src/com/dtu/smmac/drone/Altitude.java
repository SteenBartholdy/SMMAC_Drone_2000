package com.dtu.smmac.drone;

import de.yadrone.base.navdata.AltitudeListener;

public class Altitude implements AltitudeListener {

	private int altitude;
	
	@Override
	public void receivedAltitude(int altitude) {
		this.altitude = altitude;
	}

	@Override
	public void receivedExtendedAltitude(de.yadrone.base.navdata.Altitude altitude) {

	}

	public int getAltitude() {
		return this.altitude;
	}
	
}
