package fr.upsaclay.bibs;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import fr.upsaclay.bibs.rational.Rational;

public class RationnalExample {

	public static void main(String[] args) {
		Rational r1 = new Rational(1,2);
		Rational r2 = new Rational(1,2);
		
		Rational r3 = r1.add(r2);
		
		System.out.println(r1 + "+" + r2 + "=" + r3); 
		System.out.println(r1 + "+" + r3 + "=" + (r1.add(r3)));
		
		System.out.println(r1.doubleValue());

		System.out.println(r1 == r2);
		System.out.println(r1.equals(r2));
		
		Set<Rational> rset = new HashSet<Rational>();
		
		rset.add(r1);
		rset.add(r2);
		
		System.out.println(rset);
		
		List<Rational> rationals = Arrays.asList(new Rational(1,2), new Rational(1,3), new Rational(2), new Rational(), new Rational(1,2));
		System.out.println(rationals);
		rationals.sort(null);
		System.out.println(rationals);
		
		r1 = Rational.valueOf("1/2");
		r2 = Rational.valueOf("3");
		System.out.println(r1);
		System.out.println(r2);
		
		
		r1 = Rational.sum(new Rational(1,2), new Rational(1,3), new Rational(1,4));
		System.out.println(r1);
		
		r1 = Rational.prod(new Rational(1,2), new Rational(1,3), new Rational(1,4));
		System.out.println(r1);
		
		System.out.println(Rational.getMinimalCreated());
		System.out.println(Rational.getMaximalCreated());
		
		System.out.println(new Rational(1,-3));
		
		System.out.println(new Rational(-2,4));
		
		System.out.println(new Rational(0,3));
		
		//System.out.println(Rational.min_value);
		System.out.println(Rational.getMinimalCreated());
		

	}

}
