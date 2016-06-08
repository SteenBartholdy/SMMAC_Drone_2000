package Math;

public class Counter {

	private long a, t;

	public Counter(long until) {
		t = until;
		a = 0;
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
			return false; 
		}
	}

}
