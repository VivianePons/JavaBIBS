package fr.upsaclay.bibs.fieldsystem.control;

public class ProbaVerifier implements ParameterVerifier {
    @Override
    public boolean verify(String value) {
        /// BEGIN SOLUTION
        try {
            double d = Double.parseDouble(value);
            return 0 <= d && d <= 1;
        } catch (NumberFormatException e) {
            return false;
        }
        /// END SOLUTION
        /* BEGIN UNCOMMENT
		throw new UnsupportedOperationException("Not implemented");
		END UNCOMMENT */
    }
}
