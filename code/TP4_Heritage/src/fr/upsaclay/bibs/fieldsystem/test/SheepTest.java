package fr.upsaclay.bibs.fieldsystem.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import fr.upsaclay.bibs.fieldsystem.sheepfield.FieldElementType;
import fr.upsaclay.bibs.fieldsystem.sheepfield.FieldMove;
import fr.upsaclay.bibs.fieldsystem.sheepfield.Grass;
import fr.upsaclay.bibs.fieldsystem.sheepfield.Position;
import fr.upsaclay.bibs.fieldsystem.sheepfield.Sheep;

class SheepTest {

	@Test
	void testCreate() {
		assertTrue(new Sheep().isActive());
		assertEquals(new Sheep().getType(), FieldElementType.SHEEP);
	}
	
	@Test
	void testGetSetDeFaultLifeSpan() {
		Sheep.setDefaultLifeSpan(2);
		assertEquals(Sheep.getDefaultLifeSpan(), 2);
	}
	
	@Test
	void testGetSetDefaultWeakLevel() {
		Sheep.setDefaultWeakLevel(1);
		assertEquals(Sheep.getDefaultWeakLevel(),1);
	}
	
	@Test
	void testGetSetDefaultIncreasePerEat() {
		Sheep.setDefaultIncreasePerEat(2);
		assertEquals(Sheep.getDefaultIncreasePerEat(),2);
	}
	
	@Test
	void testGetSetDefaultSpeed() {
		Sheep.setDefaultSpeed(2);
		assertEquals(Sheep.getDefaultSpeed(),2);
	}
	
	@Test
	void testGetSetReproductionProba() {
		Sheep.setDefaultReproductionProba(.1);
		assertEquals(Sheep.getDefaultReproductionProba(),.1);
		assertThrows(IllegalArgumentException.class, () -> Sheep.setDefaultReproductionProba(2));
		assertThrows(IllegalArgumentException.class, () -> Sheep.setDefaultReproductionProba(-1));
	}
	
	@Test
	void testGetInitialLifeSpan() {
		Sheep.setDefaultLifeSpan(2);
		Sheep s1 = new Sheep();
		assertEquals(s1.getInitialLifeSpan(), 2);
		Sheep.setDefaultLifeSpan(3);
		assertEquals(s1.getInitialLifeSpan(), 3);
	}
	
	@Test
	void testGetWeakLevel() {
		Sheep.setDefaultWeakLevel(1);
		Sheep s1 = new Sheep();
		assertEquals(s1.getWeakLevel(), 1);
		Sheep.setDefaultWeakLevel(2);
		assertEquals(s1.getWeakLevel(), 2);
	}
	
	@Test
	void testIsWeak() {
		Sheep.setDefaultWeakLevel(10);
		Sheep.setDefaultLifeSpan(10);
		assertTrue(new Sheep().isWeak());
	}
	
	@Test
	void testGetIncreasePerEat() {
		Sheep.setDefaultIncreasePerEat(1);
		Sheep s1 = new Sheep();
		assertEquals(s1.getIncreasePerEat(), 1);
		Sheep.setDefaultIncreasePerEat(2);
		assertEquals(s1.getIncreasePerEat(), 2);
	}
	
	@Test
	void testGetSpeed() {
		Sheep.setDefaultSpeed(2);
		Sheep s1 = new Sheep();
		assertEquals(s1.getSpeed(), 2);
		Sheep.setDefaultSpeed(1);
		assertEquals(s1.getSpeed(), 1);
	}
	
	@Test
	void testGetReproductionProba() {
		Sheep.setDefaultReproductionProba(.5);
		Sheep s1 = new Sheep();
		assertEquals(s1.getReproductionProba(), .5);
		Sheep.setDefaultReproductionProba(1);
		assertEquals(s1.getReproductionProba(), 1);
	}
	
	@Test
	void testNewInstance() {
		Sheep s = new Sheep();
		assertEquals(s.getClass(), s.newInstance().getClass());
		assertNotEquals(s, s.newInstance());
	}
	
	@Test
	public void testEvolveDead() {
		Sheep.setDefaultLifeSpan(1);
		Sheep s1 = new Sheep();
		assertTrue(s1.isActive());
		assertFalse(s1.isDead());
		s1.evolve();
		assertFalse(s1.isActive());
		assertTrue(s1.isDead());
	}
	
	@Test
	public void testEvolveWeak() {
		Sheep.setDefaultLifeSpan(2);
		Sheep.setDefaultWeakLevel(1);
		Sheep s1 = new Sheep();
		assertFalse(s1.isWeak());
		s1.evolve();
		assertTrue(s1.isWeak());
	}
	
	@Test
	public void testCanEat() {
		assertTrue(new Sheep().canEat());
		assertTrue(new Sheep().canEat(FieldElementType.GRASS));
		assertFalse(new Sheep().canEat(FieldElementType.SHEEP));
		assertFalse(new Sheep().canEat(FieldElementType.WOLF));
		Sheep sheep = new Sheep();
		sheep.eaten();
		assertFalse(sheep.canEat());
	}
	
	@Test
	public void testEaten() {
		Sheep s1 = new Sheep();
		s1.eaten();
		assertTrue(s1.isDead());
		assertFalse(s1.isActive());
	}
	
	@Test
	public void testEat() {
		Sheep sheep = new Sheep();
		Grass grass = new Grass();
		grass.activate();
		Sheep.setDefaultIncreasePerEat(2);
		int before = sheep.getLifespan();
		sheep.eat(grass);
		assertEquals(sheep.getLifespan(), before + 2);
		assertFalse(grass.isActive());
		assertThrows(IllegalArgumentException.class, () -> sheep.eat(grass));
		assertThrows(IllegalArgumentException.class, () -> sheep.eat(sheep));
		sheep.eaten();
		assertThrows(IllegalStateException.class, () -> sheep.eat(new Grass(true)));
	}
	
	@Test
	public void testCanMove() {
		Sheep sheep = new Sheep();
		assertTrue(sheep.canMove());
		sheep.eaten();
		assertFalse(sheep.canMove());
	}
	
	@Test
	public void testNextMove() {
		Sheep.setDefaultSpeed(1);
		Sheep sheep = new Sheep();
		Position source = new Position(5,5);
		assertEquals(sheep.nextMove(source, source), new FieldMove(sheep, source, source));
		Position target = new Position(10,5);
		assertEquals(sheep.nextMove(source, target), new FieldMove(sheep, source, new Position(6,5)));
		target = new Position(5,10);
		assertEquals(sheep.nextMove(source, target), new FieldMove(sheep, source, new Position(5,6)));
		target = new Position(0,5);
		assertEquals(sheep.nextMove(source, target), new FieldMove(sheep, source, new Position(4,5)));
		target = new Position(5,0);
		assertEquals(sheep.nextMove(source, target), new FieldMove(sheep, source, new Position(5,4)));
		target = new Position(10,9);
		assertEquals(sheep.nextMove(source, target), new FieldMove(sheep, source, new Position(6,5)));
		target = new Position(9,10);
		assertEquals(sheep.nextMove(source, target), new FieldMove(sheep, source, new Position(5,6)));
		target = new Position(0,1);
		assertEquals(sheep.nextMove(source, target), new FieldMove(sheep, source, new Position(4,5)));
		target = new Position(1,0);
		assertEquals(sheep.nextMove(source, target), new FieldMove(sheep, source, new Position(5,4)));
		Sheep.setDefaultSpeed(2);
		target = new Position(6,6);
		assertEquals(sheep.nextMove(source, target), new FieldMove(sheep, source, new Position(6,6)));
		target = new Position(5,6);
		assertEquals(sheep.nextMove(source, target), new FieldMove(sheep, source, new Position(5,6)));
		target = new Position(6,5);
		assertEquals(sheep.nextMove(source, target), new FieldMove(sheep, source, new Position(6,5)));
		target = new Position(10,5);
		assertEquals(sheep.nextMove(source, target), new FieldMove(sheep, source, new Position(7,5)));
		target = new Position(5,10);
		assertEquals(sheep.nextMove(source, target), new FieldMove(sheep, source, new Position(5,7)));
		target = new Position(0,5);
		assertEquals(sheep.nextMove(source, target), new FieldMove(sheep, source, new Position(3,5)));
		target = new Position(5,0);
		assertEquals(sheep.nextMove(source, target), new FieldMove(sheep, source, new Position(5,3)));
		target = new Position(10,8);
		assertEquals(sheep.nextMove(source, target), new FieldMove(sheep, source, new Position(7,5)));
		target = new Position(8,10);
		assertEquals(sheep.nextMove(source, target), new FieldMove(sheep, source, new Position(5,7)));
		target = new Position(0,2);
		assertEquals(sheep.nextMove(source, target), new FieldMove(sheep, source, new Position(3,5)));
		target = new Position(2,0);
		assertEquals(sheep.nextMove(source, target), new FieldMove(sheep, source, new Position(5,3)));
	}
	
	
	@Test
	public void testConditionalReproduce() {
		Sheep sheep = new Sheep();
		Sheep.setDefaultReproductionProba(0);
		assertEquals(sheep.conditionnalReproduce(), null);
		Sheep.setDefaultReproductionProba(1);
		Sheep.setDefaultWeakLevel(sheep.getLifespan());
		assertEquals(sheep.conditionnalReproduce(), null);
		Sheep.setDefaultWeakLevel(0);
		assertEquals(sheep.conditionnalReproduce().getClass(), sheep.getClass());
	}
}
