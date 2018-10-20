package virtualdispatcher.api;

import java.time.Instant;

/**
 * Availability model.
 *
 * @author Grayson Kuhns
 */
public interface Availability {

  /**
   * Gets the pilot ID.
   *
   * @return The pilot ID.
   */
  int getPilotId();

  /**
   * Gets the time when the availability was created.
   *
   * @return The time the availability was created.
   */
  Instant getTimeCreated();
}
