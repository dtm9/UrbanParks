package model;

/**
 * Custom exception for exceeding the maximum number of pending jobs per day. The idea
 * for this particular custom exception was directly inspired from a class lecture.
 * 
 * @author Walter Weeks
 */
public class InvalidJobDurationException extends RuntimeException {
    
    private static final long serialVersionUID = 1L;
    
    public InvalidJobDurationException() { }

    public InvalidJobDurationException(String theMessage) {
        super(theMessage);
    }
    
    public InvalidJobDurationException(Throwable theCause) {
        super(theCause);
    }
    
    public InvalidJobDurationException(String theMessage, Throwable theCause) {
        super(theMessage, theCause);
    }
    
    public InvalidJobDurationException(String theMessage, Throwable theCause,
            boolean theEnableSuppression, boolean theWritableStackTrace) {
        super(theMessage, theCause, theEnableSuppression, theWritableStackTrace);
    }
}
