package Math;

public class Counter {

	private long a;

	public Counter() {
		a = 0;
	}

	public void count() {
		if (a < 10) {
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
			return false; 
		}
	}

}
