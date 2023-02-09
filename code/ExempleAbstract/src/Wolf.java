
public class Wolf extends AbstractAnimal {

	protected Wolf(Position initialPosition) {
		super(AnimalType.Carnivore, initialPosition, 2);
	}

	@Override
	public boolean canEat(Animal animal) {
		return (animal.getType() == AnimalType.Herbivore);
	}

}
