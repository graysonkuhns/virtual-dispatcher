package virtualdispatcher.db.dao;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import java.util.List;
import java.util.stream.Collectors;
import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.core.mapper.RowMappers;
import virtualdispatcher.api.Flight;
import virtualdispatcher.db.mapper.FlightMapper;

/**
 * {@link Flight}
 */
@Singleton
public class FlightDAO {

  // Dependencies
  private final Jdbi jdbi;

  /**
   * Constructor.
   *
   * @param jdbi The database connector.
   * @param flightMapper The {@link FlightMapper}.
   */
  @Inject
  FlightDAO(
      final Jdbi jdbi,
      final FlightMapper flightMapper) {

    this.jdbi = jdbi;

    // Register the mapper if it has not been already
    if (!jdbi.getConfig().get(RowMappers.class).findFor(Flight.class).isPresent()) {
      jdbi.registerRowMapper(flightMapper);
    }
  }

  public List<Flight> list() {
    return list(null, null);
  }

  /**
   * List flights.
   *
   * @return The flights.
   */
  public List<Flight> list(final Boolean completed, final Boolean started) {
    return jdbi.withHandle(handle -> handle
        .createQuery("SELECT * FROM flights")
        .mapTo(Flight.class)
        .list()
        .stream()
        .filter(flight -> completed == null || flight.isCompleted() == completed)
        .filter(flight -> started == null || flight.isStarted() == started)
        .collect(Collectors.toList()));
  }

  public void create(final Flight flight) {
    jdbi.useHandle(handle -> handle
      .createUpdate(
          "INSERT\n" +
              "  INTO flights (completed, started, pilot_id, aircraft_id, zone_id)\n" +
              "  VALUES (:completed, :started, :pilot_id, :aircraft_id, :zone_id)"
      )
      .bind("completed", flight.isCompleted())
      .bind("started", flight.isStarted())
      .bind("pilot_id", flight.getPilotId())
      .bind("aircraft_id", flight.getAircraftId())
      .bind("zone_id", flight.getZoneId())
      .execute());
  }
}
