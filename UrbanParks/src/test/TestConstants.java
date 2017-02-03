/**
 * A series of constants used by the account test cases
 * for data we expect to be good.
 * @author Dylan Miller
 */

package test;

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
}
