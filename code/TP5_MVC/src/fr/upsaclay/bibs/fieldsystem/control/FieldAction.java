package fr.upsaclay.bibs.fieldsystem.control;

/**
 * List of actions that sent from the view to the controller 
 * 
 * @author Viviane Pons
 *
 */
public enum FieldAction {
	INITIAL_START("", null),
	PAUSE("", null),
	START("", null),
	QUIT("", null),
	EVOLVE("", null),
	INITIAL_SHEEPS("50", FieldParameterType.INT),
	INITIAL_WOLVES("50", FieldParameterType.INT)
	/// BEGIN SOLUTION
	,GRASS_PROBA("0.05", FieldParameterType.PROBA)
	,GRASS_LIFE_SPAN("3", FieldParameterType.INT)
	,SHEEP_LIFE_SPAN("5", FieldParameterType.INT)
	,SHEEP_WEAK_LEV("2", FieldParameterType.INT)
	,SHEEP_INCREASE("3", FieldParameterType.INT)
	,SHEEP_SPEED("1", FieldParameterType.INT)
	,SHEEP_PROBA("0.1", FieldParameterType.PROBA)
	,WOLF_LIFE_SPAN("10", FieldParameterType.INT)
	,WOLF_WEAK_LEV("2", FieldParameterType.INT)
	,WOLF_INCREASE("4", FieldParameterType.INT)
	,WOLF_SPEED("2", FieldParameterType.INT)
	,WOLF_PROBA("0.1", FieldParameterType.PROBA)
	,ADD_SHEEP("0", FieldParameterType.INT)
	,ADD_WOLF("0", FieldParameterType.INT)
	,SPEED_PLUS("", null)
	,SPEED_MINUS("", null)
	,MANAGEMENT_START("",null)
	,MANAGEMENT_END("", null)
	/// END SOLUTION
	;
	
	private final String defaultValue;
	private final FieldParameterType type;
	
	FieldAction(String defaultValue, FieldParameterType type) {
		this.defaultValue = defaultValue;
		this.type = type;
	}
	
	public String getDefaultValue() {
		return defaultValue;
	}
	
	public FieldParameterType getType() {
		return type;
	}
	
	

}
