package virtualdispatcher.db.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import javax.inject.Inject;
import javax.inject.Singleton;
import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;
import virtualdispatcher.api.Flight;

/**
 * {@link Flight} mapper.
 *
 * @author Grayson Kuhns
 */
@Singleton
public class FlightMapper implements RowMapper<Flight> {

  // Constants
  private static final String KEY_ID = "id";
  private static final String KEY_COMPLETED = "completed";
  private static final String KEY_PILOT_ID = "pilot_id";
  private static final String KEY_AIRCRAFT_ID = "aircraft_id";
  private static final String KEY_ZONE_ID = "zone_id";

  // Dependencies
  private final FlightFactory.FlightFactory flightFactory;

  /**
   * Constructor.
   *
   * @param flightFactory The {@link FlightFactory.FlightFactory}.
   */
  @Inject
  FlightMapper(final FlightFactory.FlightFactory flightFactory) {
    this.flightFactory = flightFactory;
  }

  @Override
  public Flight map(final ResultSet rs, final StatementContext ctx) throws SQLException {
    return flightFactory.create(
        rs.getInt(KEY_ID),
        rs.getBoolean(KEY_COMPLETED),
        rs.getInt(KEY_PILOT_ID),
        rs.getInt(KEY_AIRCRAFT_ID),
        rs.getInt(KEY_ZONE_ID));
  }
}
