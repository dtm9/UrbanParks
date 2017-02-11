package backend;

/**
 * Enumeration for the month.
 *
 * @author Walter Weeks
 * @version 2017 Feb 10
 */
public enum Month {

    JAN("January", 1),
    FEB("February", 2),
    MAR("March", 3),
    APR("April", 4),
    MAY("May", 5),
    JUN("June", 6),
    JUL("July", 7),
    AUG("August", 8),
    SEP("September", 9),
    OCT("October", 10),
    NOV("November", 11),
    DEC("December", 12);

    /** The full-length month name. */
    String myFullMonthName;

    /** The int value of the month. */
    int myValue;

    /**
     * Month enum constructor.
     *
     * @author Walter Weeks
     * @param theFullMonthName Full-length month representation.
     * @param theValue The numeric representation of the month.
     */
    Month(String theFullMonthName, int theValue) {
        this.myFullMonthName = theFullMonthName;
        this.myValue = theValue;
    }

    /**
     * Get the enum value given a string value. Ignores case.
     *
     * @author Walter Weeks
     * @param theInput String to parse.
     * @return The parsed Month, or null on failure.
     */
    public static Month valueOfString(final String theInput) {
        if (null == theInput) {
            return null;
        }
        String inputTrim = theInput.trim();
        for (Month month : values()) {
            if (month.myFullMonthName.equalsIgnoreCase(inputTrim) ||
                    month.myFullMonthName.substring(0, 3).equalsIgnoreCase(inputTrim)) {
                return month;
            }
        }
        return null;
    }

    /**
     * Get the enum value given an int.
     *
     * @author Walter Weeks
     * @param theInput int to parse.
     * @return The parsed Month, or null otherwise.
     */
    public static Month valueOfInt(final int theInput) {
        for (Month month : values()) {
            if (month.myValue == theInput) {
                return month;
            }
        }
        return null;
    }

    /**
     * The string representation is just the full-length String value, i.e., "January".
     *
     * @return The full-length month representation.
     */
    @Override
    public String toString() {
        return myFullMonthName;
    }
}
