
public class Sheep extends AbstractAnimal {

	protected Sheep(Position initialPosition) {
		super(AnimalType.Herbivore, initialPosition, 1);
	}


	@Override
	public boolean canEat(Animal animal) {
		return false;
	}

}
