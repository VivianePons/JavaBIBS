
public abstract class AbstractAnimal implements Animal {

	private final AnimalType type;
	
	private Position position;
	private int speed;
	
	protected AbstractAnimal(AnimalType type, Position initialPosition, int speed) {
		this.type = type;
		setPosition(initialPosition);
		setSpeed(speed);
	}

	@Override
	public AnimalType getType() {
		return type;
	}
	
	protected void setPosition(Position position) {
		this.position = position;
	}
	
	@Override
	public Position getPosition() {
		return position;
	}

	public int getSpeed() {
		return speed;
	}

	protected void setSpeed(int speed) {
		this.speed = speed;
	}
	
	@Override
	public boolean movesToward(Position target) {
		int xdiff = target.getX() - position.getX();
		int ydiff = target.getY() - position.getY();
		if(xdiff == 0 && ydiff == 0) {
			return false;
		}
		if(xdiff > ydiff) {
			position.setX(position.getX() + (int) Math.signum(xdiff) * Math.min(speed, Math.abs(xdiff)));
		} else {
			position.setY(position.getY() + (int) Math.signum(ydiff) * Math.min(speed, Math.abs(ydiff)));
		}
		return true;
		
	}
	
	

}
