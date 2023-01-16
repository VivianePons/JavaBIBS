
public class Rational {
	public final int n;
	public final int d;
	
	Rational(int n, int d) {
		if(d == 0) {
			throw new IllegalArgumentException();
		}
		int div = Main.gcd(n, d);
		n = n / div;
		d = d / div;
		this.n = n;
		this.d = d;
	}
	
	public Rational add(Rational r2) {
		return new Rational(n*r2.d + r2.n * d, d * r2.d);
	}
	
	public String toString() {
		return n + "/" + d;
	}
}
