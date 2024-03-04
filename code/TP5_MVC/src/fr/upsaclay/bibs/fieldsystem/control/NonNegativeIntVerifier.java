package fr.upsaclay.bibs.fieldsystem.control;

public class NonNegativeIntVerifier implements ParameterVerifier {
    @Override
    public boolean verify(String value) {
        try {
            int a = Integer.parseInt(value);
            return a >= 0;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
