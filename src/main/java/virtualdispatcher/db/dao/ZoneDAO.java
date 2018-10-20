package virtualdispatcher.db.dao;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import java.util.List;
import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.core.mapper.RowMappers;
import virtualdispatcher.api.Zone;
import virtualdispatcher.db.mapper.ZoneMapper;

/**
 * {@link Zone} Data access object.
 *
 * @author Grayson Kuhns
 */
@Singleton
public class ZoneDAO {

  // Dependencies
  private final Jdbi jdbi;

  @Inject
  ZoneDAO(
      final Jdbi jdbi,
      final ZoneMapper zoneMapper) {

    this.jdbi = jdbi;

    // Register the mapper if it has not been already
    if (!jdbi.getConfig().get(RowMappers.class).findFor(Zone.class).isPresent()) {
      jdbi.registerRowMapper(zoneMapper);
    }
  }

  public List<Zone> list() {
    return jdbi.withHandle(handle -> handle
        .createQuery("SELECT * FROM zones")
        .mapTo(Zone.class)
        .list());
  }
}
