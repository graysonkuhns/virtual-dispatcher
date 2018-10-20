package virtualdispatcher.db.dao;

import com.google.inject.Inject;
import java.util.List;
import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.core.mapper.RowMappers;
import virtualdispatcher.api.Flight;
import virtualdispatcher.db.mapper.FlightMapper;

/**
 * {@link Flight}
 */
public class FlightDAO {

  // Dependencies
  private final Jdbi jdbi;
  private final FlightMapper flightMapper;

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
    this.flightMapper = flightMapper;

    // Register the mapper if it has not been already
    if (!jdbi.getConfig().get(RowMappers.class).findFor(Flight.class).isPresent()) {
      jdbi.registerRowMapper(flightMapper);
    }
  }

  /**
   * List flights.
   *
   * @return The flights.
   */
  public List<Flight> list() {
    return null;
  }
}
