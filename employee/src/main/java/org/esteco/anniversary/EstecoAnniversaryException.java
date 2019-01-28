package org.esteco.anniversary;

public class EstecoAnniversaryException extends Exception {
    public EstecoAnniversaryException(String message, Exception e) {
        super(message,e);
    }

    public EstecoAnniversaryException(String message) {
        super(message);
    }
}
