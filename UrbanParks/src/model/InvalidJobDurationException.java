package model;

/**
 * Custom exception for exceeding the maximum number of pending jobs per day.
 * 
 * @author Walter Weeks
 */
public class InvalidJobDurationException extends RuntimeException {

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
