package fr.upsaclay.bibs.fieldsystem.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import fr.upsaclay.bibs.fieldsystem.sheepfield.FieldElementType;
import fr.upsaclay.bibs.fieldsystem.sheepfield.Grass;

class GrassTest {

	@Test
	void testCreation() {
		Grass g = new Grass(true, 1);
		assertTrue(g.isActive());
		assertEquals(g.getGrassProba(), 1);
		assertEquals(g.getLifespan(), Grass.getDefaultLifeSpan());
		g = new Grass(false, 0);
		assertFalse(g.isActive());
		assertEquals(g.getGrassProba(), 0);
		g = new Grass(true);
		assertTrue(g.isActive());
		assertEquals(g.getGrassProba(), Grass.getDefaultProba());
		g = new Grass(false);
		assertFalse(g.isActive());
		assertEquals(g.getGrassProba(), Grass.getDefaultProba());
		Grass.setDefaultProba(1);
		g = new Grass();
		assertTrue(g.isActive());
		assertEquals(g.getGrassProba(), 1);
		Grass.setDefaultProba(0);
		assertEquals(g.getGrassProba(), 1);
		g = new Grass();
		assertFalse(g.isActive());
		assertEquals(g.getGrassProba(), Grass.getDefaultProba());
	}
	
	@Test
	void testGetSetDeFaultProba() {
		Grass.setDefaultProba(0.5);
		assertEquals(Grass.getDefaultProba(), 0.5);
		assertThrows(IllegalArgumentException.class, () -> Grass.setDefaultProba(-1));
		assertThrows(IllegalArgumentException.class, () -> Grass.setDefaultProba(2));
	}
	
	@Test
	void testGetSetGrassProba() {
		Grass g = new Grass();
		g.setGrassProba(0.5);
		assertEquals(g.getGrassProba(), 0.5);
		assertThrows(IllegalArgumentException.class, () -> g.setGrassProba(-1));
		assertThrows(IllegalArgumentException.class, () -> g.setGrassProba(2));
	}
	
	@Test
	void testDefaultLifeSpan() {
		Grass.setDefaultLifeSpan(3);
		assertEquals(Grass.getDefaultLifeSpan(), 3);
		assertEquals(new Grass().getInitialLifeSpan(), 3);
	}
	
	@Test
	void testEvolve() {
		Grass.setDefaultLifeSpan(2);
		Grass g = new Grass(false, 1);
		assertFalse(g.isActive());
		g.evolve();
		assertTrue(g.isActive());
		assertEquals(g.getLifespan(), 2);
		g = new Grass(true, 0);
		assertTrue(g.isActive());
		g.evolve();
		assertTrue(g.isActive());
		g.evolve();
		assertFalse(g.isActive());	
		Grass.setDefaultLifeSpan(-1);
		g = new Grass(true, 0);
		assertTrue(g.isActive());
		g.evolve();
		assertTrue(g.isActive());
		g.evolve();
		assertTrue(g.isActive());	
	}
	
	@Test
	void testEaten() {
		Grass.setDefaultLifeSpan(2);
		Grass g = new Grass(true, 0);
		g.eaten();
		assertFalse(g.isActive());
		g.evolve();
		assertFalse(g.isActive());
		g.activate();
		assertTrue(g.isActive());
		g.evolve();
		assertTrue(g.isActive());
		g.evolve();
		assertFalse(g.isActive());
	}
	
	@Test
	void testGetType() {
		assertEquals(new Grass().getType(),FieldElementType.GRASS);
	}
	
	@Test
	void testInheritedMethods() {
		Grass g = new Grass(true);
		assertFalse(g.isDead());
		assertFalse(g.isWeak());
		assertFalse(g.canEat());
		assertFalse(g.canEat(FieldElementType.SHEEP));
		assertFalse(g.canEat(FieldElementType.WOLF));
		assertThrows(IllegalArgumentException.class, () -> g.eat(new Grass()));
		assertEquals(g.conditionnalReproduce(), null);
		assertFalse(g.canMove());
		assertThrows(UnsupportedOperationException.class, () -> g.nextMove(null, null));
	}

}
