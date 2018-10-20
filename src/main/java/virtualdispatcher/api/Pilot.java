package virtualdispatcher.api;

/**
 * Pilot model.
 *
 * @author Grayson Kuhns
 */
public interface Pilot {

  /**
   * Gets the ID.
   *
   * @return The ID.
   */
  int getId();

  /**
   * Gets the first name.
   *
   * @return The first name.
   */
  String getFirstName();

  /**
   * Gets the last name.
   *
   * @return The last name.
   */
  String getLastName();
}
