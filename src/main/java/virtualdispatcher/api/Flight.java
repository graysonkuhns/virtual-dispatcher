package virtualdispatcher.api;

import org.jdbi.v3.core.argument.Argument;

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
  Integer getId();

  /**
   * Checks if the flight has been completed.
   *
   * @return True if the flight has been completed.
   */
  boolean isCompleted();

  /**
   * Checks if the flight has been started.
   *
   * @return True if the flight has been started.
   */
  boolean isStarted();

  /**
   * Gets the pilot ID.
   *
   * @return The pilot ID.
   */
  int getPilotId();

  /**
   * Gets the aircraft ID associated with the flight.
   *
   * @return The aircraft ID.
   */
  int getAircraftId();

  /**
   * Gets the zone ID.
   *
   * @return The zone ID.
   */
  int getZoneId();
}
