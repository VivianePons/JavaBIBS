package fr.upsaclay.bibs.fieldsystem.sheepfield;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;
import java.util.Set;
import java.util.function.Consumer;
import java.util.HashSet;
import java.util.Iterator;

/**
 * A class representing a field as a dynamic system to follow
 * evolutions of certain elements
 *  
 * @author Viviane Pons
 *
 */
public class Field implements Iterable<FieldElement> {
	
	/**
	 * A functionnal interface to create field elements
	 * 
	 * @author Viviane Pons
	 *
	 */
	public interface FieldElementFactory {
		FieldElement create();
	}
	
	/**
	 * An iterator class for the elements of the field
	 * 
	 * @author Viviane Pons
	 */
	private class FieldIterator implements Iterator<FieldElement> {
		
		private final Field field;
		private int i;
		private int j;
		private Iterator<FieldElement> current = null;
		
		private FieldIterator(Field field) {
			this.field = field;
			i = 0;
			j = 0;
			current = field.cells[i][j].iterator();
		}
		
		private void nextCurrent() {
			do {
				j+=1;
				if(j == field.getWidth()) {
					i+= 1;
					j = 0;
					if(i == field.getHeight()) {
						current = null;
						return;
					}
				}
				current = field.cells[i][j].iterator();
			}while(! current.hasNext());
		}

		@Override
		public boolean hasNext() {
			if(current.hasNext()) {
				return true;
			}
			nextCurrent();
			return current != null;
		}

		@Override
		public FieldElement next() {
			if(current == null) {
				throw new NoSuchElementException();
			}
			if(current.hasNext()) {
				return current.next();
			} else {
				nextCurrent();
				return next();
			}
		}
	}
	

	private final List<FieldElement>[][] cells;
	private final int width;
	private final int height;
	
	/**
	 * Field constructor with given width and height
	 * An optional list of positions can be passed
	 * 
	 * A grass element is added by default on each cell. 
	 * 
	 * If aciveGrass is null then the grass active state is given randomly
	 * 
	 * If activeGrass is not null, only grass elements at the given positions 
	 * are activated 
	 * 
	 * @param width the number of cells horizontally
	 * @param height the number of cells vertically
	 * @param activeGrass a list of positions for active grass
	 */
	public Field(int width, int height, List<Position> activeGrass) {
		this.width = width;
		this.height = height;
		
		if(width <= 0 || height <= 0) {
			throw new IllegalArgumentException();
		}
		
		cells = (List<FieldElement>[][]) new List[height][width];
		
		// adds the grass
		for(int i = 0; i < height; i++) {
			for(int j = 0; j < width; j++) {
				cells[i][j] = new ArrayList<FieldElement>();
				cells[i][j].add(activeGrass == null ? new Grass() : new Grass(false));
			}
		}
		
		// activate grass if given positions
		if(activeGrass != null) {
			for(Position pos : activeGrass) {
				cells[pos.y()][pos.x()].get(0).activate();
			}
		}
		
	}
	
	/**
	 * Field constructor of given width and height with random grass active state at each cell
	 * 
	 * @param width the number of cells horizontally
	 * @param height the number of cells vertically
	 */
	public Field(int width, int height) {
		this(width, height, null);
	}
	
	
	

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}
	
	
	/**
	 * Return the elements (active and not active) of a given position
	 * @param p a position
	 * @return an iterable on the elements of the cell
	 */
	public Iterable<FieldElement> getElements(Position p) {
		if(! isValid(p)) {
			throw new IllegalArgumentException();
		}
		return Collections.unmodifiableList(cells[p.y()][p.x()]);
	}

	/**
	 * Return the elements (active and not active) of a given cell
	 * @param i an int between 0 and height
	 * @param j an int between 0 and width
	 * @return an iterable on the elements of the cell
	 */
	public Iterable<FieldElement> getElements(int i, int j) {
		return getElements(new Position(j,i));
	}
	
	/**
	 * Return ih the position is valid in the field
	 * @param p a position
	 * @return true if it is a valid position
	 */
	public boolean isValid(Position p) {
		return (p.x() >= 0 && p.x() < width && p.y() >= 0 && p.y() < height);
	}
	
	/**
	 * Return an operator on the elements of the field
	 */
	public Iterator<FieldElement> iterator() {
		return new FieldIterator(this);
	}
	
	/**
	 * Return the number of active elements in the field
	 * @return the number of active elements in the field
	 */
	public int numberOfActiveElements() {
		int c = 0;
		for(FieldElement element : this) {
			if(element.isActive()) c+=1;
		}
		return c;
	}
	
	/**
	 * Return the number of active elements in the field with a given type
	 * @param type a FieldElementType
	 * @return the number of active elements in the field of type type
	 */
	public int numberOfActiveElements(FieldElementType type) {
		int c = 0;
		for(FieldElement element : this) {
			if(element.isActive() && element.getType() == type) c+=1;
		}
		return c;
	}
	
	/**
	 * Return the number elements (active or not) in the field
	 * @return the number of elements in the field
	 */
	public int numberOfElements() {
		int c = 0;
		for(FieldElement element : this) {
			c+=1;
		}
		return c;
	}
	
	/**
	 * Return the number elements (active or not) in the field of a given type
	 * @param type a FieldElementType
	 * @return the number of elements in the field of type type
	 */
	public int numberOfElements(FieldElementType type) {
		int c = 0;
		for(FieldElement element : this) {
			if(element.getType() == type) c+=1;
		}
		return c;
	}
	
	/**
	 * Return the position of the closest food for the given element on the given position
	 * @param from position of the eater
	 * @param element the eater as a FieldElement
	 * @return the position of the closest food using a breadth-first search
	 */
	public Position closestFood(Position from, FieldElement element) {
		LinkedList<Position> filo = new LinkedList<Position>();
		Set<Position> visited = new HashSet<Position>();
		filo.add(from);
		while(!filo.isEmpty()) {
			Position pos = filo.removeFirst();
			if(!visited.contains(pos) ) {
				visited.add(pos);
				for(FieldElement food : getElements(pos)) {
					if(food.isActive() && element.canEat(food.getType())) {
						return pos;
					}
				}
				for(Position nei : pos.neighbors()) {
					if(isValid(nei)) {
						filo.addLast(nei);
					}
				}
			}
		}
		return null;
	}
	
	/**
	 * Adds a field element at a given position
	 * @param pos a position
	 * @param element an element
	 */
	public void addFieldElement(Position pos, FieldElement element) {
		if(!isValid(pos)) {
			throw new IllegalArgumentException();
		}
		cells[pos.y()][pos.x()].add(element);
	}
	
	/**
	 * Addns n elements to the field at random positions
	 * @param n the number of elements to add
	 * @param factory a lambda method that returns a new field element
	 */
	public void addRandomFieldElements(int n, FieldElementFactory factory) {
		for(int i = 0; i < n; i++) {
			cells[randY()][randX()].add(factory.create());
		}
	}
	
	
	
	private void eatingInCell(List<FieldElement> cell) {
		for(FieldElement eater : cell) {
			if(eater.canEat()) {
				for(FieldElement food : cell) {
					if(food.isActive() && eater.canEat(food.getType())) {
						eater.eat(food);
					}
				}
			}
		}
	}
	
	
	
	private List<FieldMove> evolveCell(int i, int j) {
		
		// the eating
		eatingInCell(cells[i][j]);
		
		// the evolving
		for(FieldElement element : cells[i][j]) {
			element.evolve();
		}
		
		// the reproducing
		List<FieldElement> newels = new ArrayList<FieldElement>();
		for(FieldElement element : cells[i][j]) {
			FieldElement newel = element.conditionnalReproduce();
			if(newel != null) {
				newels.add(newel);
			}
		}
		
		// the moving
		List<FieldMove> moves = new ArrayList<FieldMove>();
		Position current = new Position(j,i);
		for(FieldElement element : cells[i][j]) {
			if(element.canMove()) {
				Position target = closestFood(current, element);
				if(target != null) {
					moves.add(element.nextMove(current, target));
				}
			}
		}
		
		// cleaning the deads
		cells[i][j] = cells[i][j].stream().filter(element -> ! element.isDead()).collect(Collectors.toList());
		
		// adding the new ones
		cells[i][j].addAll(newels);
		return moves;
		
	}
	
	/**
	 * Evolves the field
	 * The evolution process is as follows for each cells
	 * 1. resolve the eating within cell
	 * 2. evolves each element in the cell
	 * 3. resolve the reproducing in the cell and computes the new elements to be added
	 * 4. resolve the moving a computes a list of moves for cell
	 * 5. clean the dead out of the cell
	 * 6. add the newly created elements
	 * 
	 * Once this has been perfomed on all cells. The computed moves of all cells are applied
	 * to the field
	 */
	public void evolve() {
		List<FieldMove> moves = new ArrayList<FieldMove>();
		for(int i = 0; i < height; i++) {
			for(int j = 0; j < width; j++) {
				moves.addAll(evolveCell(i,j));
			}
		}
		
		for(FieldMove move : moves) {
			cells[move.from().y()][move.from().x()].remove(move.element());
			cells[move.to().y()][move.to().x()].add(move.element());
		}
	}

	
	
	private int randX() {
		return (int)(Math.random() * width);
	}
	
	private int randY() {
		return (int)(Math.random() * height);
	}
	
	
}
