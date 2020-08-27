package yte.internship.app.util.exceptions;

public class FullQuotaException extends Exception  {
    public FullQuotaException(String errorMessage) {
        super(errorMessage);
    }
}