/**
 * A series of constants used by the account test cases
 * for data we expect to be good.
 * @author Dylan Miller
 */

package tests;

import model.Park;

/**
 * The constants for the test cases.
 * @author Dylan Miller
 *
 */
interface TestConstants {
  /**Address that passes the email REGEX validation.*/
  String GOOD_EMAIL = "Jim@hotmail.com";

  /**10 digit phone number that meets our phone REGEX validation.*/
  String GOOD_PHONE = "5551112222";

  /**There is no REGEX for real names, it just needs to not be blank.*/
  String GOOD_NAME = "Jim Bob";
  
  /** int for testing good values in Job. */
  int GOOD_INT = 5;
  
  /** int for testing bad values in Job. */
  int BAD_INT = -5;
  
  /** String null value to test for catching cast errors */
  String BAD_STRING = null;
  
  /** String value to test for sunny day string */
  String GOOD_STRING = "STRING";
  
  /** Park null value to test for catching cast errors */
  Park P_NULL = null;
  
}
