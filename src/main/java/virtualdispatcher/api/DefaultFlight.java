package virtualdispatcher.api;

/**
 * Default {@link Flight} implementation.
 *
 * @author Grayson Kuhns
 */
public class DefaultFlight implements Flight {

  // Properties
  private final int id;
  private final boolean completed;
  private final int aircraftId;

  /**
   * Constructor.
   *
   * @param id The ID.
   * @param completed True if the flight is complete.
   * @param aircraftId The aircraft associated with the flight.
   */
  DefaultFlight(
      final int id,
      final boolean completed,
      final int aircraftId) {

    this.id = id;
    this.completed = completed;
    this.aircraftId = aircraftId;
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
   * Gets the aircraft ID associated with the flight.
   *
   * @return The aircraft ID.
   */
  @Override
  public int getAircraftId() {
    return aircraftId;
  }
}
