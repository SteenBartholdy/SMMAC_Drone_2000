package com.dtu.smmac.drone;

import com.dtu.smmac.gui.Image;

import de.yadrone.base.command.VideoChannel;

public class Landing {

	private QRCode qr;
	private Movement mv;
	private Image img;
	private final String landingField = "Airfield 0";
	private boolean searching;
	
	public Landing(QRCode qrcode, Movement mov, Image image) {
		qr = qrcode;
		mv = mov;
		img = image;
		searching = true;
	}
	
	public void proceedLading() {
		mv.getCmd().setVideoChannel(VideoChannel.NEXT);
		
		while (searching) {
			if (qr.readQRCode(img.getImg()).equals(landingField)) {
				searching = false;
				mv.landing();
			} else {
				mv.landingSearch();
			}
			
			mv.stopMoving();
		}
	}
	
	
}
