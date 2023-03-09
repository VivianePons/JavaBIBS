package fr.upsaclay.bibs.fieldsystem.view;

/**
 * An interface used by FieldParameter to verify the user input
 * 
 * @author Viviane Pons
 *
 */
public interface ParameterVerifier {

	public boolean verify(String value);
	
}
