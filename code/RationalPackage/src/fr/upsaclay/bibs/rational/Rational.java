package fr.upsaclay.bibs.rational;

/**
 * A class to represent rational numbers
 * @author Viviane Pons
 */
public class Rational implements Comparable<Rational> {
	
	/*
	 * the minimal rational that can be represented
	 */
	public static final int min_operand = Integer.MIN_VALUE;
	
	/*
	 * the maximal rational that can be represented
	 */
	public static final int max_operand = Integer.MAX_VALUE;
	
	/*
	 * the minimal Rational that has been instantiated
	 */
	private static Rational minimal_created;
	
	/*
	 * the maximal Rational that has been instantiated
	 */
	private static Rational maximal_created;
	
	
	/**
	 * The numerator
	 */
	public final int n;
	
	/**
	 * the denominator
	 */
	public final int d;
	
	
	
	/**
	 * Construct a rational number with given numerator and denominator by simplifying the fraction
	 * the simplifying is done only if `check` is True
	 * @param n the numerator
	 * @param d the denominator
	 * @param check
	 */
	protected Rational(long n, long d, boolean check) {
		if(d == 0) {
			throw new IllegalArgumentException("division by 0");
		}
		if(d < 0) {
			n = -n;
			d = -d;
		}
		if(check) {
			long div = n > 0 ? gcd(n, d): gcd(-n,d);
			n = n / div;
			d = d / div;
		}
		if(n > max_operand || n < min_operand || d > max_operand) {
			throw new IllegalArgumentException("Exceeds operand capacity");
		}
		this.n = (int)n;
		this.d = (int)d;
		if (minimal_created == null || this.compareTo(minimal_created) < 0) {
			minimal_created = this;
		}
		if (maximal_created == null || this.compareTo(maximal_created) > 0) {
			maximal_created = this;
		}
	}
	
	
	/**
	 * Construct a rational number with given numerator and denominator by simplifying the fraction
	 * 
	 * numerator and denominator are saved as int but taken as long to check for overcapacity.
	 * 
	 * @param n the numerator
	 * @param d the denominator
	 */
	public Rational(long n, long d) {
		this(n, d, true);
	}
	
	/**
	 * Construct the rational number n/1
	 * @param n the numerator
	 */
	public Rational(long n) {
		this(n,1, false);
	}
	
	/**
	 * Construct the rational 0
	 */
	public Rational() {
		this(0);
	}
	
	protected static long gcd(long a, long b) {
		return b==0 ? a : gcd(b, a%b);
	}
	
	/**
	 * Value of given int as a Rational
	 * @param a
	 */
	public static Rational valueOf(int a) {
		return new Rational(a);
	}
	
	/**
	 * Value of given string as Rational
	 * @param s a String value
	 * @return
	 */
	public static Rational valueOf(String s) {
		String[] parts = s.split("/");
		if (parts.length == 1) {
			return Rational.valueOf(Integer.valueOf(s));
		} else if (parts.length == 2) {
			return new Rational(Integer.valueOf(parts[0]), Integer.valueOf(parts[1]));
		} else {
			throw new NumberFormatException("For input string: \"" + s + "\"");
		}
	}
	
	/**
	 * Sums the given rationals
	 * @param args a list of Rational
	 * @return the sum as a Rational
	 */
	public static Rational sum(Rational... args) {
		Rational s = new Rational();
		for(Rational r : args) {
			s = s.add(r);
		}
		return s;
	}
	
	/**
	 * Multiply the given rationals
	 * @param args a list of Rational
	 * @return the produtc as a Rational
	 */
	public static Rational prod(Rational... args) {
		Rational p = new Rational(1);
		for(Rational r: args) {
			p = p.multiply(r);
		}
		return p;
	}
	
	public static Rational getMinimalCreated() {
		return minimal_created;
	}
	
	public static Rational getMaximalCreated() {
		return maximal_created;
	}
	
	
	/**
	 * Adds rationals
	 * @param r2 another rational object
	 * @return the Rational representing the sum of object and `r2`
	 */
	public Rational add(Rational r2) {
		return new Rational((long)n*r2.d + (long)r2.n * d, (long)d * r2.d);
	}
	
	/** 
	 * Multiply rationals
	 * @param r2
	 * @return the Rational representing the product of object and `r2`
	 */
	public Rational multiply(Rational r2) {
		return new Rational((long)n*r2.n, (long)d*r2.d);
	}
	
	/** 
	 * Compute the inverse of seld
	 * @return the mathematical inverse of self
	 */
	public Rational inverse() {
		return new Rational(d,n, false);
	}
	
	/**
	 * Compute minus self
	 * @return -n/d
	 */
	public Rational minus() {
		return new Rational(-n, d, false);
	}
	
	/**
	 * Return the approximated value as a double
	 * @return numerator / denominator
	 */
	public double doubleValue() {
		return (double) n / d; 
	}
	
	@Override
	public String toString() {
		if(d != 1) {
			return n + "/" + d;
		} else {
			return String.valueOf(n);
		}
	}
	
	/**
	 * Return whether r2 is equal to object as rationals
	 * @param r2 a rational
	 * @return true if this is equal to self
	 */
	public boolean equals(Rational r2) {
		return (r2.n == n && r2.d == d);
	}
	
	
	@Override
	public boolean equals(Object obj) {
		if(obj == null) {
			return false;
		}
		if(!Rational.class.isAssignableFrom(obj.getClass())) {
			return false;
		}
		return this.equals((Rational)obj);
	}
	
	
	
	@Override
	public int hashCode() {
		return toString().hashCode();
	}


	@Override
	public int compareTo(Rational r2) {
		if(equals(r2)) {
			return 0;
		}
		return (doubleValue() < r2.doubleValue())? -1 : 1;
	}
	
	
	
}
