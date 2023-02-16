package fr.upsaclay.bibs.fieldsystem.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import fr.upsaclay.bibs.fieldsystem.sheepfield.FieldElementType;
import fr.upsaclay.bibs.fieldsystem.sheepfield.FieldMove;
import fr.upsaclay.bibs.fieldsystem.sheepfield.Grass;
import fr.upsaclay.bibs.fieldsystem.sheepfield.Position;
import fr.upsaclay.bibs.fieldsystem.sheepfield.Sheep;
import fr.upsaclay.bibs.fieldsystem.sheepfield.Wolf;

class WolfTest {

	/// BEGIN COMMENTEE
	
	@Test
	void testCreate() {
		assertTrue(new Wolf().isActive());
	}
	
	@Test
	void testGetSetDeFaultLifeSpan() {
		Wolf.setDefaultLifeSpan(2);
		assertEquals(Wolf.getDefaultLifeSpan(), 2);
	}
	
	@Test
	void testGetSetDefaultWeakLevel() {
		Wolf.setDefaultWeakLevel(1);
		assertEquals(Wolf.getDefaultWeakLevel(),1);
	}
	
	@Test
	void testGetSetDefaultIncreasePerEat() {
		Wolf.setDefaultIncreasePerEat(2);
		assertEquals(Wolf.getDefaultIncreasePerEat(),2);
	}
	
	@Test
	void testGetSetDefaultSpeed() {
		Wolf.setDefaultSpeed(2);
		assertEquals(Wolf.getDefaultSpeed(),2);
	}
	
	@Test
	void testGetSetReproductionProba() {
		Wolf.setDefaultReproductionProba(.1);
		assertEquals(Wolf.getDefaultReproductionProba(),.1);
		assertThrows(IllegalArgumentException.class, () -> Wolf.setDefaultReproductionProba(2));
		assertThrows(IllegalArgumentException.class, () -> Wolf.setDefaultReproductionProba(-1));
	}
	
	@Test
	void testGetInitialLifeSpan() {
		Wolf.setDefaultLifeSpan(2);
		Wolf wolf = new Wolf();
		assertEquals(wolf.getInitialLifeSpan(), 2);
		Wolf.setDefaultLifeSpan(3);
		assertEquals(wolf.getInitialLifeSpan(), 3);
	}
	
	@Test
	void testGetWeakLevel() {
		Wolf.setDefaultWeakLevel(1);
		Wolf wolf = new Wolf();
		assertEquals(wolf.getWeakLevel(), 1);
		Wolf.setDefaultWeakLevel(2);
		assertEquals(wolf.getWeakLevel(), 2);
	}
	
	@Test
	void testGetIncreasePerEat() {
		Wolf.setDefaultIncreasePerEat(1);
		Wolf wolf = new Wolf();
		assertEquals(wolf.getIncreasePerEat(), 1);
		Wolf.setDefaultIncreasePerEat(2);
		assertEquals(wolf.getIncreasePerEat(), 2);
	}
	
	@Test
	void testGetSpeed() {
		Wolf.setDefaultSpeed(2);
		Wolf wolf = new Wolf();
		assertEquals(wolf.getSpeed(), 2);
		Wolf.setDefaultSpeed(1);
		assertEquals(wolf.getSpeed(), 1);
	}
	
	@Test
	void testGetReproductionProba() {
		Wolf.setDefaultReproductionProba(.5);
		Wolf wolf = new Wolf();
		assertEquals(wolf.getReproductionProba(), .5);
		Wolf.setDefaultReproductionProba(1);
		assertEquals(wolf.getReproductionProba(), 1);
	}
	
	@Test
	void testNewInstance() {
		Wolf s = new Wolf();
		assertEquals(s.getClass(), s.newInstance().getClass());
	}
	
	@Test
	public void testEvolveDead() {
		Wolf.setDefaultLifeSpan(1);
		Wolf wolf = new Wolf();
		assertTrue(wolf.isActive());
		assertFalse(wolf.isDead());
		wolf.evolve();
		assertFalse(wolf.isActive());
		assertTrue(wolf.isDead());
	}
	
	@Test
	public void testEvolveWeak() {
		Wolf.setDefaultLifeSpan(2);
		Wolf.setDefaultWeakLevel(1);
		Wolf wolf = new Wolf();
		assertFalse(wolf.isWeak());
		wolf.evolve();
		assertTrue(wolf.isWeak());
	}
	
	@Test
	public void testCanEat() {
		assertTrue(new Wolf().canEat());
		assertFalse(new Wolf().canEat(FieldElementType.GRASS));
		assertTrue(new Wolf().canEat(FieldElementType.SHEEP));
		assertFalse(new Wolf().canEat(FieldElementType.WOLF));
		Wolf wolf = new Wolf();
		wolf.eaten();
		assertFalse(wolf.canEat());
		assertFalse(wolf.canEat(FieldElementType.SHEEP));
	}
	
	@Test
	public void testEaten() {
		Wolf wolf = new Wolf();
		wolf.eaten();
		assertTrue(wolf.isDead());
		assertFalse(wolf.isActive());
	}
	
	@Test
	public void testEat() {
		Wolf wolf = new Wolf();
		Sheep sheep = new Sheep();
		Wolf.setDefaultIncreasePerEat(2);
		int before = wolf.getLifespan();
		wolf.eat(sheep);
		assertEquals(wolf.getLifespan(), before + 2);
		assertFalse(sheep.isActive());
		assertThrows(IllegalArgumentException.class, () -> wolf.eat(sheep));
		assertThrows(IllegalArgumentException.class, () -> wolf.eat(wolf));
		wolf.eaten();
		assertThrows(IllegalStateException.class, () -> wolf.eat(new Sheep()));
	}
	
	@Test
	public void testCanMove() {
		Wolf wolf = new Wolf();
		assertTrue(wolf.canMove());
		wolf.eaten();
		assertFalse(wolf.canMove());
	}
	
	@Test
	public void testNextMove() {
		Wolf.setDefaultSpeed(1);
		Wolf wolf = new Wolf();
		Position source = new Position(5,5);
		assertEquals(wolf.nextMove(source, source), new FieldMove(wolf, source, source));
		Position target = new Position(10,5);
		assertEquals(wolf.nextMove(source, target), new FieldMove(wolf, source, new Position(6,5)));
		target = new Position(5,10);
		assertEquals(wolf.nextMove(source, target), new FieldMove(wolf, source, new Position(5,6)));
		target = new Position(0,5);
		assertEquals(wolf.nextMove(source, target), new FieldMove(wolf, source, new Position(4,5)));
		target = new Position(5,0);
		assertEquals(wolf.nextMove(source, target), new FieldMove(wolf, source, new Position(5,4)));
		target = new Position(10,9);
		assertEquals(wolf.nextMove(source, target), new FieldMove(wolf, source, new Position(6,5)));
		target = new Position(9,10);
		assertEquals(wolf.nextMove(source, target), new FieldMove(wolf, source, new Position(5,6)));
		target = new Position(0,1);
		assertEquals(wolf.nextMove(source, target), new FieldMove(wolf, source, new Position(4,5)));
		target = new Position(1,0);
		assertEquals(wolf.nextMove(source, target), new FieldMove(wolf, source, new Position(5,4)));
		Wolf.setDefaultSpeed(2);
		target = new Position(6,6);
		assertEquals(wolf.nextMove(source, target), new FieldMove(wolf, source, new Position(6,6)));
		target = new Position(5,6);
		assertEquals(wolf.nextMove(source, target), new FieldMove(wolf, source, new Position(5,6)));
		target = new Position(6,5);
		assertEquals(wolf.nextMove(source, target), new FieldMove(wolf, source, new Position(6,5)));
		target = new Position(10,5);
		assertEquals(wolf.nextMove(source, target), new FieldMove(wolf, source, new Position(7,5)));
		target = new Position(5,10);
		assertEquals(wolf.nextMove(source, target), new FieldMove(wolf, source, new Position(5,7)));
		target = new Position(0,5);
		assertEquals(wolf.nextMove(source, target), new FieldMove(wolf, source, new Position(3,5)));
		target = new Position(5,0);
		assertEquals(wolf.nextMove(source, target), new FieldMove(wolf, source, new Position(5,3)));
		target = new Position(10,8);
		assertEquals(wolf.nextMove(source, target), new FieldMove(wolf, source, new Position(7,5)));
		target = new Position(8,10);
		assertEquals(wolf.nextMove(source, target), new FieldMove(wolf, source, new Position(5,7)));
		target = new Position(0,2);
		assertEquals(wolf.nextMove(source, target), new FieldMove(wolf, source, new Position(3,5)));
		target = new Position(2,0);
		assertEquals(wolf.nextMove(source, target), new FieldMove(wolf, source, new Position(5,3)));
	}
	
	@Test
	public void testConditionalReproduce() {
		Wolf wolf = new Wolf();
		Wolf.setDefaultReproductionProba(0);
		assertEquals(wolf.conditionnalReproduce(), null);
		Wolf.setDefaultReproductionProba(1);
		Wolf.setDefaultWeakLevel(wolf.getLifespan());
		assertEquals(wolf.conditionnalReproduce(), null);
		Wolf.setDefaultWeakLevel(0);
		assertEquals(wolf.conditionnalReproduce().getClass(), wolf.getClass());
	}
	
	/// END COMMENTEE

}
