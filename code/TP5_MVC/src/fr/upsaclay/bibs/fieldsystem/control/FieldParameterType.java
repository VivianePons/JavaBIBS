package fr.upsaclay.bibs.fieldsystem.control;

/**
 * The different types of parameters that can be read 
 * 
 * @author Viviane Pons
 *
 */
public enum FieldParameterType {
	PROBA,
	INT;

	public static ParameterVerifier getVerifier(FieldParameterType type) {
		switch (type) {
            case PROBA: return new ProbaVerifier();
            case INT: return new NonNegativeIntVerifier();
        }
		return null;
	}
}
