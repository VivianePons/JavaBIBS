package fr.upsaclay.bibs.fieldsystem.sheepfield;

public class FieldMove {
	
	private final FieldElement element_f;
	private final Position from_f;
	private final Position to_f;
	
	public FieldMove(FieldElement element, Position from, Position to) {
		this.element_f = element;
		this.from_f = from;
		this.to_f = to;
	}

	public FieldElement element() {
		return element_f;
	}

	public Position from() {
		return from_f;
	}

	public Position to() {
		return to_f;
	}
	
	public String toString() {
		return element_f.toString() + from_f.toString() + to_f.toString();
	}
	
	public boolean equals(Object o) {
		if(o == null) {
			return false;
		}
		if(!FieldMove.class.isAssignableFrom(o.getClass())) {
			return false;
		}
		FieldMove p = (FieldMove)o;
		return p.element().equals(element_f) && p.from().equals(from_f) && p.to().equals(to_f);
	}
	
	public int hashCode() {
		return toString().hashCode();
	}
	
};
