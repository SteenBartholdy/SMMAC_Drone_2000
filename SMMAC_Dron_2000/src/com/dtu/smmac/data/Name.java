package com.dtu.smmac.data;

public enum Name {
	MARTIN, ANDERS, STEEN, CHRISTOFFER, MADS;
	
	public String getMAC() {
		String str = null;
		
		switch (this) {
		case MARTIN: 
			str = "a8:66:7f:03:c9:05";
			break;
		case ANDERS: 
			str = "a8:66:7f:03:c9:05"; 
			break;
		case CHRISTOFFER: 
			str = "80:e6:50:09:6b:90"; 
			break;
		case MADS: 
			str = "18:CF:5E:91:4B:A3"; 
			break; 
		case STEEN:
			str = "60:36:DD:15:E5:55"; 
			break;
		default:
			break;
		}
		
		return str;
	}
	
	public String getPhotoLibary(String filename) {
		String str = null;
		
		switch (this) {
		case MARTIN: 
			str = "/Users/Roos/Pictures/" + filename + ".png";
			break;
		case ANDERS: 
			str = "/Users/Anders/Pictures/" + filename + ".png"; 
			break;
		case CHRISTOFFER: 
			str = "/Users/Christoffer/Pictures/" + filename + ".png"; 
			break;
		case MADS: 
			str = ""; 
			break; 
		case STEEN:
			str = ""; 
			break;
		default:
			break;
		}
		
		return str;
	}
}
