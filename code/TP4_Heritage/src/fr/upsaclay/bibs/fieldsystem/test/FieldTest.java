package fr.upsaclay.bibs.fieldsystem.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.Test;

import fr.upsaclay.bibs.fieldsystem.sheepfield.Field;
import fr.upsaclay.bibs.fieldsystem.sheepfield.FieldElement;
import fr.upsaclay.bibs.fieldsystem.sheepfield.FieldElementType;
import fr.upsaclay.bibs.fieldsystem.sheepfield.Grass;
import fr.upsaclay.bibs.fieldsystem.sheepfield.Position;
import fr.upsaclay.bibs.fieldsystem.sheepfield.Sheep;
import fr.upsaclay.bibs.fieldsystem.sheepfield.Wolf;

class FieldTest {

	/// BEGIN COMMENTEE
	
	@Test
	void testConstructor() {
		Field field = new Field(15,10);
		assertEquals(field.getWidth(), 15);
		assertEquals(field.getHeight(), 10);
		field = new Field(6, 5, Arrays.asList(new Position(0,0)));
		assertEquals(field.getWidth(), 6);
		assertEquals(field.getHeight(), 5);
		assertEquals(field.numberOfActiveElements(), 1);
		Grass.setDefaultProba(1);
		field = new Field(6,5);
		assertEquals(field.getWidth(), 6);
		assertEquals(field.getHeight(), 5);
		assertEquals(field.numberOfActiveElements(), 30);
		Grass.setDefaultProba(0);
		field = new Field(6,5);
		assertEquals(field.getWidth(), 6);
		assertEquals(field.getHeight(), 5);
		assertEquals(field.numberOfActiveElements(), 0);
		assertThrows(IllegalArgumentException.class, () -> new Field(0,5));
		assertThrows(IllegalArgumentException.class, () -> new Field(5, 0));
	}
	
	@Test
	void testGetPositionElements() {
		Field field = new Field(1,1);
		Iterable<FieldElement> cell = field.getElements(0, 0);
		int c = 0;
		for(FieldElement element : cell) {
			assertEquals(element.getType(), FieldElementType.GRASS);
			c++;
		}
		assertEquals(c,1);
		cell = field.getElements(new Position(0,0));
		c = 0;
		for(FieldElement element : cell) {
			assertEquals(element.getType(), FieldElementType.GRASS);
			c++;
		}
		assertEquals(c,1);
		assertThrows(IllegalArgumentException.class, () -> field.getElements(-1, 0));
		assertThrows(IllegalArgumentException.class, () -> field.getElements(0, -1));
		assertThrows(IllegalArgumentException.class, () -> field.getElements(1, 0));
		assertThrows(IllegalArgumentException.class, () -> field.getElements(0, 1));
	}
	
	@Test
	void testNumberOfElements() {
		Grass.setDefaultProba(0);
		Field field = new Field(10,10);
		assertEquals(field.numberOfElements(), 100);
		assertEquals(field.numberOfActiveElements(), 0);
		assertEquals(field.numberOfElements(FieldElementType.GRASS), 100);
		assertEquals(field.numberOfElements(FieldElementType.SHEEP), 0);
		assertEquals(field.numberOfActiveElements(FieldElementType.GRASS), 0);
		
	}
	
	@Test
	void testClosestFood() {
		Field field = new Field(4,4, Arrays.asList(new Position(0,0), new Position(3,3)));
		assertEquals(field.closestFood(new Position(0,0), new Sheep()), new Position(0,0));
		assertEquals(field.closestFood(new Position(1,0), new Sheep()), new Position(0,0));
		assertEquals(field.closestFood(new Position(2,0), new Sheep()), new Position(0,0));
		assertEquals(field.closestFood(new Position(0,1), new Sheep()), new Position(0,0));
		assertEquals(field.closestFood(new Position(1,1), new Sheep()), new Position(0,0));
		assertEquals(field.closestFood(new Position(0,2), new Sheep()), new Position(0,0));
		assertEquals(field.closestFood(new Position(3,1), new Sheep()), new Position(3,3));
		assertEquals(field.closestFood(new Position(2,2), new Sheep()), new Position(3,3));
		assertEquals(field.closestFood(new Position(3,2), new Sheep()), new Position(3,3));
		assertEquals(field.closestFood(new Position(1,3), new Sheep()), new Position(3,3));
		assertEquals(field.closestFood(new Position(2,3), new Sheep()), new Position(3,3));
		assertEquals(field.closestFood(new Position(3,3), new Sheep()), new Position(3,3));
		field = new Field(4,4, Arrays.asList(new Position(0,0)));
		assertEquals(field.closestFood(new Position(3,3), new Sheep()), new Position(0,0));
		Sheep deadSheep = new Sheep();
		deadSheep.eaten();
		assertEquals(field.closestFood(new Position(3,3), deadSheep), null);
		Grass.setDefaultProba(0);
		field = new Field(4,4);
		assertEquals(field.closestFood(new Position(3,3), new Sheep()), null);
	}
	
	@Test
	void testAddFieldElement() {
		Field field = new Field(4,4);
		Sheep sheep = new Sheep();
		field.addFieldElement(new Position(0,0), sheep);
		boolean found = false;
		for(FieldElement element : field.getElements(new Position(0,0))) {
			if(element == sheep) {
				found = true;
				break;
			}
		}
		assertTrue(found);
		assertThrows(IllegalArgumentException.class, () -> field.addFieldElement(new Position(4,4), new Sheep()));		
	}
	
	@Test
	void testAddRandomElements() {
		Field field = new Field(10,10);
		field.addRandomFieldElements(10, () -> new Sheep());
		assertEquals(field.numberOfElements(FieldElementType.SHEEP), 10);
	}
	
	@Test
	void testEvolve() {
		// test the evolving
		Sheep.setDefaultLifeSpan(10);
		Grass.setDefaultProba(0);
		Field field = new Field(1,1);
		Sheep sheep = new Sheep();
		field.addFieldElement(new Position(0,0), sheep);
		field.evolve();
		assertEquals(sheep.getLifespan(), 9);
		
		
		// test the eating
		Sheep.setDefaultLifeSpan(10);
		Wolf.setDefaultLifeSpan(10);
		Sheep.setDefaultReproductionProba(0);
		Wolf.setDefaultReproductionProba(0);
		Sheep.setDefaultIncreasePerEat(2);
		Wolf.setDefaultIncreasePerEat(2);
		Grass.setDefaultProba(0);
		field = new Field(1,1, Arrays.asList(new Position(0,0)));
		sheep = new Sheep();
		Wolf wolf = new Wolf();
		field.addFieldElement(new Position(0,0), sheep);
		field.addFieldElement(new Position(0,0), wolf);
		field.evolve();
		assertEquals(field.numberOfElements(), 2);
		assertEquals(field.numberOfActiveElements(), 1);
		assertEquals(field.numberOfActiveElements(FieldElementType.GRASS), 0);
		assertEquals(field.numberOfActiveElements(FieldElementType.SHEEP), 0);
		assertEquals(field.numberOfActiveElements(FieldElementType.WOLF), 1);
		assertEquals(wolf.getLifespan(), 11);
		assertEquals(sheep.getLifespan(), 12);

		// test the moving
		Grass.setDefaultLifeSpan(10);
		field = new Field(5,5, Arrays.asList(new Position(0,0)));
		sheep = new Sheep();
		wolf = new Wolf();
		field.addFieldElement(new Position(0,4), sheep);
		field.addFieldElement(new Position(4,4), wolf);
		field.evolve();
		assertEquals(field.numberOfActiveElements(), 3);
		boolean found = false;
		for(FieldElement element : field.getElements(new Position(0,3))) {
			if(element == sheep) {
				found = true;
				break;
			}
		}
		assertTrue(found);
		found = false;
		for(FieldElement element : field.getElements(new Position(3,4))) {
			if(element == wolf) {
				found = true;
				break;
			}
		}
		assertTrue(found);
		
		// test reproducing
		Sheep.setDefaultReproductionProba(1);
		Wolf.setDefaultReproductionProba(1);
		Sheep.setDefaultWeakLevel(0);
		Wolf.setDefaultWeakLevel(0);
		field.evolve();
		assertEquals(field.numberOfActiveElements(FieldElementType.SHEEP), 2);
		assertEquals(field.numberOfActiveElements(FieldElementType.WOLF), 2);
		
	}
	
	@Test
	void testIterator() {
		Field field = new Field(2,2);
		Sheep sheep = new Sheep();
		field.addFieldElement(new Position(0,1), sheep);
		Iterator<FieldElement> it = field.iterator();
		assertEquals(it.next().getType(), FieldElementType.GRASS);
		assertEquals(it.next().getType(), FieldElementType.GRASS);
		assertEquals(it.next().getType(), FieldElementType.GRASS);
		assertEquals(it.next(), sheep);
		assertEquals(it.next().getType(), FieldElementType.GRASS);
		assertThrows(NoSuchElementException.class, () -> it.next());
	}
	
	/// END COMMENTEE
 
}
