package com.dtu.smmac.math;

public class Timer {

	public int min, sek;
	
	public Timer(int min, int sek) {
		this.min = min;
		this.sek = sek;
	}
	
	public void start() {
		try {
			boolean run = true;
			while(run) {
				Thread.sleep(1000);
				if(sek > 0) {
					sek--;
				} else if (sek == 0 && min > 0) {
					min--;
					sek = 59;
				}
				
				if (min == 0 && sek == 0) {
					run = false;
				}
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public String toString() {
		return min + ":" + sek;
	}
	
}
