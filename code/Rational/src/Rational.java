
public class Rational {
	public final int n;
	public final int d;
	
	Rational(int n, int d) {
		if(d == 0) {
			throw new IllegalArgumentException();
		}
		int div = gcd(n, d);
		n = n / div;
		d = d / div;
		this.n = n;
		this.d = d;
	}
	
	public static int gcd(int a, int b) {
		return b==0 ? a : gcd(b, a%b);
	}
	
	/**
	 * Adds rationals
	 * @param r2 another rational object
	 * @return the rational representing the sum of `self` and `r2`
	 */
	public Rational add(Rational r2) {
		return new Rational(n*r2.d + r2.n * d, d * r2.d);
	}
	
	public String toString() {
		return n + "/" + d;
	}
}