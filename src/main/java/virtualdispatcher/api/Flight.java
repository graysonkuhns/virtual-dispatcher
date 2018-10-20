package virtualdispatcher.api;

/**
 * Flight model.
 *
 * @author Grayson Kuhns
 */
public interface Flight {

  /**
   * Gets the ID.
   *
   * @return The ID.
   */
  int getId();

  /**
   * Checks if the flight has been completed.
   *
   * @return True if the flight has been completed.
   */
  boolean isCompleted();

  /**
   * Gets the aircraft ID associated with the flight.
   *
   * @return The aircraft ID.
   */
  int getAircraftId();
}
