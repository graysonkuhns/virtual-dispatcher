package virtualdispatcher.api;

import com.google.inject.assistedinject.Assisted;
import javax.annotation.Nullable;

/**
 * {@link Flight} factory.
 *
 * @author Grayson Kuhns
 */
public interface FlightFactory {

  /**
   * Creates a {@link Flight}.
   *
   * @param id The ID.
   * @param completed True if the flight is complete.
   * @param started True if the flight has been started.
   * @param pilotId The pilot ID.
   * @param aircraftId The aircraft associated with the flight.
   * @param zoneId The zone ID.
   *
   * @return The {@link Flight}.
   */
  Flight create(
      @Assisted("id") @Nullable Integer id,
      @Assisted("completed") boolean completed,
      @Assisted("started") boolean started,
      @Assisted("pilotId") int pilotId,
      @Assisted("aircraftId") int aircraftId,
      @Assisted("zoneId") int zoneId);

}
