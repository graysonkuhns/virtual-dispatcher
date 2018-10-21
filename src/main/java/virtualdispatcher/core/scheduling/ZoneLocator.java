package virtualdispatcher.core.scheduling;

import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.core.mapper.RowMappers;
import virtualdispatcher.api.Zone;
import virtualdispatcher.db.mapper.ZoneMapper;
import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.Optional;

@Singleton
public class ZoneLocator {

  // Constants
  private static final String QUERY =
      "SELECT\n" +
      "  id\n" +
      "FROM\n" +
      "  zones\n" +
      "WHERE\n" +
      "  id NOT IN (\n" +
      "    SELECT\n" +
      "      zone_id\n" +
      "    FROM\n" +
      "      flights\n" +
      "    WHERE\n" +
      "      completed = False\n" +
      "      AND started = True\n" +
      "    GROUP BY\n" +
      "       zone_id\n" +
      "     HAVING COUNT(*) > 2\n" +
      "  )\n" +
      "LIMIT\n" +
      "  1";

  // Dependencies
  private final Jdbi jdbi;

  @Inject
  ZoneLocator(
      final Jdbi jdbi,
      final ZoneMapper zoneMapper) {

    this.jdbi = jdbi;

    // Register the mapper if it has not been already
    if (!jdbi.getConfig().get(RowMappers.class).findFor(Zone.class).isPresent()) {
      jdbi.registerRowMapper(zoneMapper);
    }
  }

  public Optional<Zone> getAvailableZone() {
    //return the zone the aircraft is currently in
    return jdbi.withHandle(handle -> handle
        .createQuery(QUERY)
        .mapTo(Zone.class)
        .findFirst());
  }
}
