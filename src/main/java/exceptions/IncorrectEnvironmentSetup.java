package exceptions;

import javax.naming.NameNotFoundException;

public class IncorrectEnvironmentSetup extends NameNotFoundException {

    private static final long serialVersionUID = 1L;

    public IncorrectEnvironmentSetup(String message) {
        super(message);
    }
    }
