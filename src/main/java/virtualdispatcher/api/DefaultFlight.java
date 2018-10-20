package virtualdispatcher.api;

import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;

/**
 * Default {@link Flight} implementation.
 *
 * @author Grayson Kuhns
 */
public class DefaultFlight implements Flight {

  // Properties
  private final int id;
  private final boolean completed;
  private final boolean started;
  private final int pilotId;
  private final int aircraftId;
  private final int zoneId;

  /**
   * Constructor.
   *
   * @param id The ID.
   * @param completed True if the flight is complete.
   * @param started True if the flight has started.
   * @param pilotId The pilot ID.
   * @param aircraftId The aircraft associated with the flight.
   * @param zoneId The zone ID.
   */
  @Inject
  DefaultFlight(
      @Assisted("id") final int id,
      @Assisted("completed") final boolean completed,
      @Assisted("started") final boolean started,
      @Assisted("pilotId") final int pilotId,
      @Assisted("aircraftId") final int aircraftId,
      @Assisted("zoneId") final int zoneId) {

    this.id = id;
    this.completed = completed;
    this.started = started;
    this.pilotId = pilotId;
    this.aircraftId = aircraftId;
    this.zoneId = zoneId;
  }

  /**
   * Gets the ID.
   *
   * @return The ID.
   */
  @Override
  public int getId() {
    return id;
  }

  /**
   * Checks if the flight has been completed.
   *
   * @return True if the flight has been completed.
   */
  @Override
  public boolean isCompleted() {
    return completed;
  }

  /**
   * Checks if the flight has been started.
   *
   * @return True if the flight has been started.
   */

  @Override
  public boolean isStarted() {
    return started;
  }

  /**
   * Gets the pilot ID.
   *
   * @return The pilot ID.
   */
  @Override
  public int getPilotId() {
    return pilotId;
  }

  /**
   * Gets the aircraft ID associated with the flight.
   *
   * @return The aircraft ID.
   */
  @Override
  public int getAircraftId() {
    return aircraftId;
  }

  /**
   * Gets the zone ID.
   *
   * @return The zone ID.
   */
  @Override
  public int getZoneId() {
    return zoneId;
  }
}
