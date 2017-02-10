package backend;

/**
 * Enumeration for the United States including D.C. and Puerto Rico. This enumeration does not include the ISO
 * abbreviations such as "US-WA" representing Washington State since these are unnecessary for the Urban Parks
 * implementation. It is assumed that only US citizens will be participating.
 *
 * Note that is a modified implementation from a public-domain source on github.com.
 *
 * @author AustinC (via github.com)
 * @author Walter Weeks
 * @see {@linktourl https://github.com/AustinC/UnitedStates/blob/master/src/main/java/unitedstates/US.java}
 */
public enum US {

    ALABAMA("Alabama","AL"),
    ALASKA("Alaska","AK"),
    ARIZONA("Arizona","AZ"),
    ARKANSAS("Arkansas","AR"),
    CALIFORNIA("California","CA"),
    COLORADO("Colorado","CO"),
    CONNECTICUT("Connecticut","CT"),
    DELAWARE("Delaware","DE"),
    DISTRICT_OF_COLUMBIA("District of Columbia","DC"),
    FLORIDA("Florida","FL"),
    GEORGIA("Georgia","GA"),
    HAWAII("Hawaii","HI"),
    IDAHO("Idaho","ID"),
    ILLINOIS("Illinois","IL"),
    INDIANA("Indiana","IN"),
    IOWA("Iowa","IA"),
    KANSAS("Kansas","KS"),
    KENTUCKY("Kentucky","KY"),
    LOUISIANA("Louisiana","LA"),
    MAINE("Maine","ME"),
    MARYLAND("Maryland","MD"),
    MASSACHUSETTS("Massachusetts","MA"),
    MICHIGAN("Michigan","MI"),
    MINNESOTA("Minnesota","MN"),
    MISSISSIPPI("Mississippi","MS"),
    MISSOURI("Missouri","MO"),
    MONTANA("Montana","MT"),
    NEBRASKA("Nebraska","NE"),
    NEVADA("Nevada","NV"),
    NEW_HAMPSHIRE("New Hampshire","NH"),
    NEW_JERSEY("New Jersey","NJ"),
    NEW_MEXICO("New Mexico","NM"),
    NEW_YORK("New York","NY"),
    NORTH_CAROLINA("North Carolina","NC"),
    NORTH_DAKOTA("North Dakota","ND"),
    OHIO("Ohio","OH"),
    OKLAHOMA("Oklahoma","OK"),
    OREGON("Oregon","OR"),
    PENNSYLVANIA("Pennsylvania","PA"),
    RHODE_ISLAND("Rhode Island","RI"),
    SOUTH_CAROLINA("South Carolina","SC"),
    SOUTH_DAKOTA("South Dakota","SD"),
    TENNESSEE("Tennessee","TN"),
    TEXAS("Texas","TX"),
    UTAH("Utah","UT"),
    VERMONT("Vermont","VT"),
    VIRGINIA("Virginia","VA"),
    WASHINGTON("Washington","WA"),
    WEST_VIRGINIA("West Virginia","WV"),
    WISCONSIN("Wisconsin","WI"),
    WYOMING("Wyoming","WY"),
    PUERTO_RICO("Puerto Rico","PR");

    /** The full-length state name. */
    String unnabreviated;

    /** The ANSI two-letter abbreviation for the state. */
    String ANSIabbreviation;

    /**
     * State enum constructor.
     *
     * @author AustinC via https://github.com/AustinC/UnitedStates/blob/master/src/main/java/unitedstates/US.java
     * @author Walter Weeks
     * @param unnabreviated Full-length state representation.
     * @param ANSIabbreviation Two character ANSI abbreviation.
     */
    US(String unnabreviated, String ANSIabbreviation) {
        this.unnabreviated = unnabreviated;
        this.ANSIabbreviation = ANSIabbreviation;
    }

    /**
     * Parse string input to enum. Accepts unabbreviated and abbreviated forms.
     * Case insensitive.
     *
     * @author AustinC via https://github.com/AustinC/UnitedStates/blob/master/src/main/java/unitedstates/US.java
     * @param input String to parse.
     * @return The parsed US state, or null on failure.
     */
    public static US parse(final String input) {
        if (null == input) {
            return null;
        }
        String inputTrim = input.trim();
        for (US state : values()) {
            if (state.unnabreviated.equalsIgnoreCase(inputTrim) ||
                    state.ANSIabbreviation.equalsIgnoreCase(inputTrim)) {
                return state;
            }
        }
        return null;
    }

    /**
     * Gets the full-length unabbreviated String of the state. This method was added for completeness but
     * is not necessary for the Urban Parks implementation.
     *
     * @param theState Enumeration type.
     * @return Unabbreviated String of state.
     */
    public static String getUnabbreviated(US theState) {
        return theState.unnabreviated;
    }

    /**
     * The string representation of the two-character ANSI abbreviation of the state.
     *
     * @return ANSIabbreviation String value.
     */
    @Override
    public String toString(){
        return ANSIabbreviation;
    }
}