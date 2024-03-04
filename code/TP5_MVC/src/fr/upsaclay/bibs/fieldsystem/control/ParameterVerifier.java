package fr.upsaclay.bibs.fieldsystem.control;

/**
 * An interface used by FieldParameter to verify the user input
 * 
 * @author Viviane Pons
 *
 */
public interface ParameterVerifier {

	/**
	 * Return whether the given string can be parsed into a certain parameter
	 * @param value, the string to parse
	 * @return true if it is a correct string, false if not
	 */
	boolean verify(String value);
	
}
