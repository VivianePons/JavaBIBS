package fr.upsaclay.bibs.rational.test;


import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import fr.upsaclay.bibs.rational.Rational;

class RationalTest {

	@Test
	public final void testRational() {
		assertEquals(new Rational(1,2), new Rational(2,4));
		assertEquals(new Rational(2), new Rational(4,2));
		assertEquals(new Rational(-2,4), new Rational(2,-4));
		assertEquals(new Rational(-1,-2), new Rational(1,2));
		assertEquals(new Rational(3), new Rational(3,1));
		assertEquals(new Rational(), new Rational(0));
		assertEquals(new Rational(0,1), new Rational(0,2));
		assertEquals(new Rational(-0,1), new Rational());
		assertThrows(IllegalArgumentException.class, () -> new Rational(1,0));
		assertThrows(IllegalArgumentException.class, () -> new Rational((long)Rational.max_operand + 1));
		assertThrows(IllegalArgumentException.class, () -> new Rational((long)Rational.min_operand - 1));
		assertThrows(IllegalArgumentException.class, () -> new Rational(1,(long)Rational.max_operand + 1));
	}

	
	@Test
	public final void testValueOf() {
		assertEquals(Rational.valueOf("1/2"), new Rational(1,2));
		assertEquals(Rational.valueOf("-1/2"), new Rational(-1,2));
		assertEquals(Rational.valueOf("1/-2"), new Rational(-1,2));
		assertEquals(Rational.valueOf("3"), new Rational(3));
		assertEquals(Rational.valueOf("0"), new Rational());
		assertEquals(Rational.valueOf("4/2"), new Rational(2));
		assertThrows(NumberFormatException.class, () -> Rational.valueOf("a"));
		assertThrows(NumberFormatException.class, () -> Rational.valueOf("a/b"));
		assertThrows(NumberFormatException.class, () -> Rational.valueOf("1/2/3"));
		assertThrows(NumberFormatException.class, () -> Rational.valueOf(""));
		assertThrows(NumberFormatException.class, () -> Rational.valueOf("1/"));
		assertThrows(NumberFormatException.class, () -> Rational.valueOf("/1"));

	}
	
	@Test
	public final void testSum() {
		assertEquals(Rational.sum(), new Rational());
		assertEquals(Rational.sum(new Rational(1)), new Rational(1));
		assertEquals(Rational.sum(new Rational(1), new Rational(1,2)), new Rational(3,2));
		assertEquals(Rational.sum(new Rational(1), new Rational(1,4), new Rational(1,4)), new Rational(3,2));
		assertEquals(Rational.sum(new Rational(1,2), new Rational(-1,2)), new Rational());
	}
	
	@Test
	public final void testProduct() {
		assertEquals(Rational.prod(), new Rational(1));
		assertEquals(Rational.prod(new Rational(1)), new Rational(1));
		assertEquals(Rational.prod(new Rational()), new Rational());
		assertEquals(Rational.prod(new Rational(1), new Rational(1,2)), new Rational(1,2));
		assertEquals(Rational.prod(new Rational(1), new Rational(1,4), new Rational(1,4)), new Rational(1,16));
		assertEquals(Rational.prod(new Rational(1,2), new Rational(2)), new Rational(1));
		assertEquals(Rational.prod(new Rational(1,2), new Rational(-1,2)), new Rational(-1,4));
	}
	
	@Test
	public final void testGetMinimalCreated() {
		Rational r = new Rational(Rational.min_operand);
		assertEquals(Rational.getMinimalCreated(), r);	
	}
	
	@Test
	public final void testGetMaximalCreated() {
		Rational r = new Rational(Rational.max_operand);
		assertEquals(Rational.getMaximalCreated(), r);	
	}
	
	
	@Test
	public final void testAdd() {
		assertEquals(new Rational(1,2).add(new Rational(1,2)), new Rational(1));
		assertEquals(new Rational(1).add(new Rational(1,2)), new Rational(3,2));
		assertEquals(new Rational(1,2).add(new Rational(-1,2)), new Rational());
		assertEquals(new Rational(1,2).add(new Rational(2)), new Rational(5,2));
		assertEquals(new Rational(Rational.max_operand).add(new Rational(-Rational.max_operand)), new Rational());
		assertThrows(IllegalArgumentException.class, () -> new Rational(Rational.max_operand).add(new Rational(1)));
		assertThrows(IllegalArgumentException.class, () -> new Rational(1,Rational.max_operand).add(new Rational(1)));
	}
	
	@Test
	public final void testMultiply() {
		assertEquals(new Rational(1,2).multiply(new Rational(1,2)), new Rational(1,4));
		assertEquals(new Rational(1).multiply(new Rational(1,2)), new Rational(1,2));
		assertEquals(new Rational(1,2).multiply(new Rational(2)), new Rational(1));
		assertEquals(new Rational(1,2).multiply(new Rational(-1,2)), new Rational(-1,4));
		assertEquals(new Rational(Rational.max_operand).multiply(new Rational(1,Rational.max_operand)), new Rational(1));
		assertEquals(new Rational(1, Rational.max_operand).multiply(new Rational(2)), new Rational(2, Rational.max_operand));
		assertThrows(IllegalArgumentException.class, () -> new Rational(Rational.max_operand).multiply(new Rational(2)));
		assertThrows(IllegalArgumentException.class, () -> new Rational(1,Rational.max_operand).multiply(new Rational(1,2)));
	}
	
	@Test
	public final void testInverse() {
		assertEquals(new Rational(1,2).inverse(), new Rational(2));
		Rational r = new Rational(1);
		assertEquals(r.inverse(), r);
		assertEquals(new Rational(Rational.max_operand).inverse(), new Rational(1, Rational.max_operand));
		Rational zero = new Rational();
		assertThrows(IllegalArgumentException.class, () -> zero.inverse());
	}
	
	@Test
	public final void testMinus() {
		assertEquals(new Rational(1,2).minus(), new Rational(-1,2));
		assertEquals(new Rational(Rational.max_operand).minus(), new Rational(-Rational.max_operand));
		Rational zero = new Rational();
		assertEquals(zero.minus(), zero);
	}
	
	@Test
	public final void testToString() {
		assertEquals(new Rational(1,2).toString(), "1/2");
		assertEquals(new Rational(2).toString(), "2");
		assertEquals(new Rational(-2,4).toString(), "-1/2");
		assertEquals(new Rational(-1,-2).toString(), "1/2");
		assertEquals(new Rational(3).toString(), "3");
		assertEquals(new Rational().toString(), "0");
		assertEquals(new Rational(0,1).toString(), "0");
		assertEquals(new Rational(-0,1).toString(), "0");
	}
	
	@Test
	public final void testDoubleValue() {
		assertEquals(new Rational(1,2).doubleValue(), 0.5);
		assertEquals(new Rational(2).doubleValue(), 2.);
		assertEquals(new Rational(-2,4).doubleValue(), -0.5);
		assertEquals(new Rational(-1,-2).doubleValue(), 0.5);
		assertEquals(new Rational(3).doubleValue(), 3.);
		assertEquals(new Rational().doubleValue(), 0.);
		assertEquals(new Rational(0,1).doubleValue(), 0.);
		assertEquals(new Rational(-0,1).doubleValue(), 0.);
	}
	
	@Test
	public final void testEquals() {
		assertNotEquals(new Rational(1,2), new Rational(1));
		assertNotEquals(new Rational(2), new Rational(3));
		assertNotEquals(new Rational(1), 1);
		assertNotEquals(new Rational(1,2), null);
		assertNotEquals(new Rational(1), "1");
		Object obj = new Rational(1,2);
		assertEquals(new Rational(1,2), obj);
	}
	
	@Test
	public final void testHashCode() {
		assertEquals(new Rational(1,2).hashCode(), new Rational(2,4).hashCode());
		assertEquals(new Rational(2).hashCode(), new Rational(4,2).hashCode());
		assertEquals(new Rational(-2,4).hashCode(), new Rational(2,-4).hashCode());
		assertEquals(new Rational(-1,-2).hashCode(), new Rational(1,2).hashCode());
		assertEquals(new Rational(3).hashCode(), new Rational(3,1).hashCode());
		assertEquals(new Rational().hashCode(), new Rational(0).hashCode());
		assertEquals(new Rational(0,1).hashCode(), new Rational(0,2).hashCode());
		assertEquals(new Rational(-0,1).hashCode(), new Rational().hashCode());
	}
	
	@Test
	public final void testCompareTo() {
		assertEquals(new Rational(1,2).compareTo(new Rational(2,4)), 0);
		assertTrue(new Rational(1,2).compareTo(new Rational(1)) < 0);
		assertTrue(new Rational(-1,2).compareTo(new Rational(0)) < 0);
		assertTrue(new Rational(1).compareTo(new Rational(1,2)) > 0);
		assertTrue(new Rational(0).compareTo(new Rational(-1,2)) > 0);
		assertTrue(new Rational(1,2).compareTo(new Rational(1,4)) > 0);
		assertTrue(new Rational(-1,2).compareTo(new Rational(-1,4)) < 0);
	}
	

}
