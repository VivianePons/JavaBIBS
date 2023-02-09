
public class Main {

	public static void main(String[] args) {
		Sheep sheep = new Sheep(new Position(5,5));
		Wolf wolf = new Wolf(new Position(0,0));
		
		Position grass = new Position(10,10);
		
		while(!wolf.getPosition().samePosition(sheep.getPosition())) {
			sheep.movesToward(grass);
			wolf.movesToward(sheep.getPosition());
			System.out.print("Wolf at position " + wolf.getPosition());
			System.out.println(" -- Sheep at position " + sheep.getPosition());
		}

	}

}
