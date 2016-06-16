package com.dtu.smmac.math;

public class Counter {

	private long a, t;

	public Counter(long until) {
		t = until;
		a = 1;
	}

	public void count() {
		if (a < t) {
			a++;
		} else {
			a = 0;
		}
	}

	public boolean ready() {
		if (a == 0) {
			a++;
			return true;
		}
		else {
			count();
			return false; 
		}
	}

}
